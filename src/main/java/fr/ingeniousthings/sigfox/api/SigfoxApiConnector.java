/*
 * Copyright 2017 Ingeniousthings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.ingeniousthings.sigfox.api;

import fr.ingeniousthings.sigfox.api.elements.*;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import java.util.*;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;

/**
 * Summary
 *
 * Connector to sigfox API. Implements the minimal elements to retreive or set information
 * on the sigfox API.
 * ----------------------------------------------------------------------------------
 * Requires:
 *   This class requieres SpringBoot framework
 *   This class requieres
 *     compile("org.apache.httpcomponents:httpcore:4.4.6")
 *     compile("commons-codec:commons-codec:1.10")
 * ----------------------------------------------------------------------------------
 * Support :
 *
 *
 * @author Paul Pinault
 */
public class SigfoxApiConnector {

        protected static final Logger log = LoggerFactory.getLogger(SigfoxApiConnector.class);

        private String login;           // Api login given by sigfox
        private String password;        // Api password given by sigfox

        protected static final String API_PROTOCOL = "https://";
        protected static final String API_BACKEND_URL = "backend.sigfox.com/api/";

        // ---------------------------------------------------------------------------
        // Constructor - need a user & password. These one are created from the backend
        // in the group folder / API Access submenu by creating a new account.
        // For this class any access right is suffisiant.
        public SigfoxApiConnector(String login, String password) {
            this.login = login;
            this.password = password;
        }

        // ---------------------------------------------------------------------------
        // Create the connection string (GET) with
        // the given end of the url is added at end of the standard uri ; params string added if not null
        protected String connectionString(String ending, String params) {

            String s =  SigfoxApiConnector.API_PROTOCOL
                + SigfoxApiConnector.API_BACKEND_URL
                + ending;
            if ( params != null ) {
                s += '?' + params;
            }
            return s;
        }

        // ---------------------------------------------------------------------------
        // Create a request including a basic Authentication with the given credentials
        protected HttpEntity<String> generateRequestHeaders() {
            return generateRequestHeaders(false,null);
        }
        protected HttpEntity<String> generateRequestHeaders(boolean postJson, String body) {
            HttpEntity<String> request;

            String plainCreds = this.login+":"+this.password;
            byte[] base64CredsBytes = Base64.encodeBase64(plainCreds.getBytes(Charset.forName("US-ASCII")));
            String base64Creds = new String(base64CredsBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            if (postJson) {
                headers.setContentType(MediaType.APPLICATION_JSON);
                request = new HttpEntity<String>(body, headers);
            } else {
                request = new HttpEntity<String>(headers);
            }

            return request;
        }

        // ---------------------------------------------------------------------------
        // Make a test requesting coverage redundancy on Clermont-Ferrand (truffade powered)
        // to ensure we are getting a response
        public boolean testConnection() {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<SigfoxApiCoverageRedundancy> response =
                    restTemplate.exchange(
                            this.connectionString(
                                    "coverages/redundancy",
                                    "lat=45.78&lng=3.08&mode=OUTDOOR"
                            ),
                            HttpMethod.GET,
                            this.generateRequestHeaders(),
                            SigfoxApiCoverageRedundancy.class);
            SigfoxApiCoverageRedundancy coverage = response.getBody();

            log.info(coverage.toString());
            return ( coverage.getRedundancy() > 0 );
        }

}
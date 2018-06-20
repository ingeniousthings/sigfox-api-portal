/*
 *
 *  Copyright 2017 Ingeniousthings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;

import java.lang.System;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;

/**
 * Summary
 *
 * This class manage the sigfox Coverage Component from the SigfoxApi
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
 * ----------------------------------------------------------------------------------
 *
 * @author Paul Pinault
 */
public class SigfoxApiCoverage extends SigfoxApiConnector {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SigfoxApiCoverage(String login, String password) {
        super(login, password);
    }

    // ========================================================================
    // Get a the number of antenn covering a specific position
    public int getSigfoxCoverageRedundancy(double lat, double lng, boolean indoor) {
        RestTemplate restTemplate = new RestTemplate();

        String loc = "OUTDOOR";
        if ( indoor ) {
            loc = "INDOOR";
        }

        ResponseEntity<SigfoxApiCoverageRedundancy> response =
                restTemplate.exchange(
                        this.connectionString(
                                "coverages/redundancy",
                                "lat="+lat+"&lng="+lng+"&mode="+loc
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiCoverageRedundancy.class);
        SigfoxApiCoverageRedundancy coverage = response.getBody();

        log.info(coverage.toString());
        return coverage.getRedundancy();
    }

    // ========================================================================
    // Get a signal margin on  a specific position
    public SigfoxApiCoverageMargins getSigfoxCoverageMargins(double lat, double lng) {
        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<SigfoxApiCoverageMargins> response =
                restTemplate.exchange(
                        this.connectionString(
                                "coverages/global/predictions",
                                "lat="+lat+"&lng="+lng
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiCoverageMargins.class);
        SigfoxApiCoverageMargins coverage = response.getBody();

        log.info(coverage.toString());
        return coverage;
    }


}

/**
 * -------------------------------------------------------------------------------
 * This file is part of IngeniousThings Sigfox-Api.
 *
 * IngeniousThings Sigfox-Api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IngeniousThings Sigfox-Api is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 * -------------------------------------------------------------------------------
 * Author : Paul Pinault aka disk91
 * See https://www.disk91.com
 * ----
 * More information about IngeniousThings : https://www.ingeniousthings.fr
 * ----
 * Commercial license of this software can be obtained contacting ingeniousthings
 * -------------------------------------------------------------------------------
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

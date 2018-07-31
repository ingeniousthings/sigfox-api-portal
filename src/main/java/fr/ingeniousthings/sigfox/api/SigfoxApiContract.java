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
 * This class manage the sigfox contracts Component from the SigfoxApi
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
public class SigfoxApiContract extends SigfoxApiConnector {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SigfoxApiContract(String login, String password) {
        super(login, password);
    }


    // ========================================================================
    // Get the list of all the available contract on your account. including the
    // active & non activ contracts.
    public List<SigfoxApiContractInformation> getSigfoxAllContract() {

        try {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<SigfoxApiContractList> response =
                    restTemplate.exchange(
                            this.connectionString(
                                    "contracts",
                                    null
                            ),
                            HttpMethod.GET,
                            this.generateRequestHeaders(),
                            SigfoxApiContractList.class);
            SigfoxApiContractList contracts = response.getBody();
            log.info(contracts.toString());
            return Arrays.asList(contracts.getData());

        } catch ( HttpClientErrorException h) {
            log.error("Sigfox return an error accessing contractlist.");
            return new ArrayList<SigfoxApiContractInformation>();
        }

    }

    // ========================================================================
    // Get the list of all the valid contract on your account. only active
    public List<SigfoxApiContractInformation> getSigfoxValidContract() {


        List<SigfoxApiContractInformation> contracts = getSigfoxAllContract();

        ArrayList<SigfoxApiContractInformation> validContract = new ArrayList<SigfoxApiContractInformation>();
        for (SigfoxApiContractInformation contract : contracts ) {
            if ( contract.getState() == 1 ) {
                validContract.add(contract);
            }
        }

        log.info("SigfoxApiContract.getSigfoxValidContract : "+ validContract.toString());
        return validContract;

    }

    // ========================================================================
    // Get the only active contract, choose the one finishing first with free devices
    public SigfoxApiContractInformation getSigfoxValidContractFinishingFirst() {


        List<SigfoxApiContractInformation> contracts = getSigfoxAllContract();

        ArrayList<SigfoxApiContractInformation> validContract = new ArrayList<SigfoxApiContractInformation>();
        for (SigfoxApiContractInformation contract : contracts ) {
            if ( contract.getState() == 1 && ( contract.getMaxTokens() - contract.getTokensInUse() - contract.getTokensUsed() ) > 0 ) {
                validContract.add(contract);
            }
        }
        validContract.sort(Comparator.comparingLong(SigfoxApiContractInformation::getEndTime));

        log.info("SigfoxApiContract.getSigfoxValidContractFinishingFirst : "+ validContract.get(0).toString());
        return validContract.get(0);

    }

    // ========================================================================
    // Get a specific contract id information
    public SigfoxApiContractInformation getSigfoxContract(String cid) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SigfoxApiContractInformation> response =
                restTemplate.exchange(
                        this.connectionString(
                                "contracts/" + cid,
                                null
                        ),
                        HttpMethod.GET,
                        this.generateRequestHeaders(),
                        SigfoxApiContractInformation.class);
        SigfoxApiContractInformation contract = response.getBody();

        log.info("SigfoxApiContract by id ("+cid+") : "+contract.toString());
        return contract;

    }

    // ========================================================================
    // Get the group id attached to a contract
    public String getSigfoxGroupFromContract(String cid) {
        String ret = getSigfoxContract(cid).getGroupId();
        log.info("SigfoxApiContract : groupId : ("+ret+") from contractId ("+cid+")");
        return ret;
    }



}

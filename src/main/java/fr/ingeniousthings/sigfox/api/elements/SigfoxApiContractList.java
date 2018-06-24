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

package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.lang.String;
import java.util.*;


/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a contract List.
 *
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  { data : [
 *    {
 *      id: "contract_id_in_hexa_string",
 *      name: "GIVEN_CONTRACT_NAME",
 *      startTime: 1476223200000,
 *      endTime: 1507759200000,
 *      communicationEndTime: 1539295200000,
 *      timezone: "Europe/Paris",
 *      tokenDuration: 12,                          // in month
 *      activationPeriodDuration: 12,               // in month
 *      maxTokensInUse: 0,                          // supposed to be activ token
 *      bidir: true,
 *      maximumNumberOfDownlinkFrames: 4,
 *      state: 1,                                   // 0 : initial / 1 : valid / 2 : terminate
 *      tokensUsed: 1,
 *      maxTokens: 100,
 *      tokensInUse: 31,
 *      groupId: "group_id_associated_with_this_contract",
 *      type: 2                                     // 0 : free / 1 : integrator / 2 : standard
 *    },{
 *
 *       SigfoxApiContractInformation
 *
 *    }
 *   ]
 *  }
 *
 * @author Paul Pinault
 */
public class SigfoxApiContractList {

    @ApiModelProperty(
            notes = "Array of BSS orders information records",
            required = true
    )
    protected SigfoxApiContractInformation[] data;

    public SigfoxApiContractInformation[] getData() {
        return data;
    }

    public void setData(SigfoxApiContractInformation[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String s = "SigfoxApiContractList{" +
                "data= [";
        List<SigfoxApiContractInformation> l = Arrays.asList(data);
        for (SigfoxApiContractInformation o : l) {
            s += "{";
            s += o.toString();
            s += "},";
        }
        s += "]}";
        return s;
    }
}

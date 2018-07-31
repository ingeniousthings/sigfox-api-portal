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

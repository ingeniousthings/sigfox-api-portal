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


/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a DeviceInit.
 *
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  Response
 *
 *     {
 *       "code" : 1,
 *       "detailMessage" : "Off contract",
 *       "tokenType": "CONTRACT",
 *       "contractId": "7896541254789654aedfba4c",
 *       "tokenEnd": 1418673953200,
 *     }
 *
 *
 *     code: the device’s token state
 *         0: OK
 *         1: KO
 *         2: N/A
 *     detailMessage: Detail message that could be:
 *         "Off contract": Device is off contract
 *         "Not communicated": There’s none token on this device
 *         "Invalid token": Token end time has been exceeded
 *         "Ok": Token is ok
 *     tokenType: the type of token taken by this device. This field is only present if a token exists. There are curently two types of tokens :
 *         CONTRACT: a classical token, with a start date and an end date.
 *         FREE: a token offering test messages With such a token a device is not considered as activated, so not taken into account in billing.
 *     contractId: the id of the contract of the token. This field is only present if a token exists.
 *     freeMessages: the number of free messages left on this token. This field is only present if the token exists and is of type FREE.
 *     tokenEnd: a timestamp in milliseconds expressing the time the token expires. This field is only present if the token exists and is of type CONTRACT.
 *
 * @author Paul Pinault
 */
public class SigfoxApiDeviceTokenState {

    public static final int    TOKEN_STATE_OK            = 0;
    public static final int    TOKEN_STATE_KO            = 1;
    public static final int    TOKEN_STATE_NA            = 2;

    @ApiModelProperty(
            notes = "The device’s token state",
            required = true
    )
    protected int    code;

    @ApiModelProperty(
            notes = "Detail message that could be:" +
                    "<ul>" +
                    "<li>\"Off contract\": Device is off contract</li>" +
                    "<li>\"Not communicated\": There’s none token on this device</li>" +
                    "<li>\"Invalid token\": Token end time has been exceeded</li>" +
                    "<li>\"Ok\": Token is ok</li>" +
                    "</ul>",
            required = true
    )
    protected String    detailMessage;

    @ApiModelProperty(
            notes = "the type of token taken by this device. This field is only present if a token exists. There are curently two types of tokens : " +
                    "<ul>" +
                    "<li>CONTRACT: a classical token, with a start date and an end date.</li>" +
                    "<li>FREE: a token offering test messages With such a token a device is not considered as activated, so not taken into account in billing.</li>" +
                    "</ul>",
            required = true
    )
    protected String    tokenType;

    @ApiModelProperty(
            notes = "The id of the contract of the token. This field is only present if a token exists.",
            required = true
    )
    protected String    contractId;

    @ApiModelProperty(
            notes = " The number of free messages left on this token. This field is only present if the token exists and is of type FREE.",
            required = true
    )
    protected String    freeMessages;

    @ApiModelProperty(
            notes = "A timestamp in milliseconds expressing the time the token expires. This field is only present if the token exists and is of type CONTRACT.",
            required = true
    )
    protected long      tokenEnd;



    // ------------------------------------------------------------------
    // Generated Getter & Setters


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getFreeMessages() {
        return freeMessages;
    }

    public void setFreeMessages(String freeMessages) {
        this.freeMessages = freeMessages;
    }

    public long getTokenEnd() {
        return tokenEnd;
    }

    public void setTokenEnd(long tokenEnd) {
        this.tokenEnd = tokenEnd;
    }
}



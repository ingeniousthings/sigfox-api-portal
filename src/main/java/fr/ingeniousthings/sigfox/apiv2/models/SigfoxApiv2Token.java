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
package fr.ingeniousthings.sigfox.apiv2.models;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(tags = "Token", description = "Contains the token information of the device")
public class SigfoxApiv2Token {

    @ApiModelProperty(
            notes = "Token State.<br/>" +
                    "<ul>" +
                    "<li>0 -> OK</li>" +
                    "<li>1 -> OFF_CONTRACT</li>" +
                    "<li>2 -> TOKEN_NOT_CONSUMED</li>" +
                    "<li>3 -> INVALID_TOKEN</li>" +
                    "</ul>",
            required = false
    )
    protected int state;


    @ApiModelProperty(
            notes = "Token state description Valid Off Contract Not Consumed Invalid",
            required = false
    )
    protected String detailMessage;

    @ApiModelProperty(
            notes = "The device’s communication end time (in milliseconds since the Unix Epoch)",
            required = false
    )
    protected long end;

    @ApiModelProperty(
            notes = "The number of free messages left for this token",
            required = false
    )
    protected int freeMessages;

    @ApiModelProperty(
            notes = "The number of free messages already sent for this token",
            required = false
    )
    protected int freeMessagesSent;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getFreeMessages() {
        return freeMessages;
    }

    public void setFreeMessages(int freeMessages) {
        this.freeMessages = freeMessages;
    }

    public int getFreeMessagesSent() {
        return freeMessagesSent;
    }

    public void setFreeMessagesSent(int freeMessagesSent) {
        this.freeMessagesSent = freeMessagesSent;
    }
}

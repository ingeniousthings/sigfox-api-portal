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

@Api(tags = "commonCbDef", description = "Common Information for callback")
public class SigfoxApiv2CallbackCommonDef {

    @ApiModelProperty(
            notes = "The callback’s channel.<br/>" +
                    "<ul>" +
                    "<li>URL</li>" +
                    "<li>BATCH_URL</li>" +
                    "<li>EMAIL</li>" +
                    "</ul>",
            example = "URL",
            required = true
    )
    protected String channel;

    @ApiModelProperty(
            notes = "The callback’s type.<br/>" +
                    "<ul>" +
                    "<li>0 -> DATA</li>" +
                    "<li>1 -> SERVICE</li>" +
                    "<li>2 -> ERROR</li>" +
                    "</ul>",
            example = "0",
            required = true
    )
    protected int callbackType;

    @ApiModelProperty(
            notes = "The callback’s subtype. The subtype must be valid against its type.<br/>" +
                    "<ul>" +
                    "<li>0 -> STATUS callback sending information about the status of a device (available for SERVICE callbacks)</li>" +
                    "<li>1 -> GEOLOC callback sent on a message that can be geolocated (available for SERVICE callbacks)</li>" +
                    "<li>2 -> UPLINK callback for an uplink message (available for DATA callbacks)</li>" +
                    "<li>3 -> BIDIR callback for a bidirectional message (available for DATA callbacks)</li>" +
                    "<li>4 -> ACKNOWLEDGE callback sent on a downlink acknowledged message (available for SERVICE callbacks)</li>" +
                    "<li>5 -> REPEATER callback triggered when a repeater sends an OOB (available for SERVICE callbacks)</li>" +
                    "</ul>",
            example = "2",
            required = true
    )
    protected int callbackSubtype;

    @ApiModelProperty(
            notes = "The custom payload configuration. Only for DATA callbacks",
            example = "int1::uint:8 int2::uint:8",
            required = false
    )
    protected String payloadConfig;

    @ApiModelProperty(
            notes = "True to enable the callback, false else",
            example = "true",
            required = true
    )
    protected boolean enabled;

    @ApiModelProperty(
            notes = "True to duplicates callback, false else",
            example = "false",
            required = true
    )
    protected boolean sendDuplicate;

    @ApiModelProperty(
            notes = "True if last use of the callback fails, false else",
            example = "false",
            required = false
    )
    protected boolean dead;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(int callbackType) {
        this.callbackType = callbackType;
    }

    public int getCallbackSubtype() {
        return callbackSubtype;
    }

    public void setCallbackSubtype(int callbackSubtype) {
        this.callbackSubtype = callbackSubtype;
    }

    public String getPayloadConfig() {
        return payloadConfig;
    }

    public void setPayloadConfig(String payloadConfig) {
        this.payloadConfig = payloadConfig;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSendDuplicate() {
        return sendDuplicate;
    }

    public void setSendDuplicate(boolean sendDuplicate) {
        this.sendDuplicate = sendDuplicate;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}

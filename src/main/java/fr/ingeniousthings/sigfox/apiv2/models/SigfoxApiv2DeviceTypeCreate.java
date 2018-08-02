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

@Api(tags = "DeviceTypeCreate", description = "Defines the device type’s properties")
public class SigfoxApiv2DeviceTypeCreate {

    @ApiModelProperty(
            notes = "The device type’s name",
            required = false
    )
    protected String name;

    @ApiModelProperty(
            notes = "Keep alive period in seconds (0 to not keep alive else 1800 second minimum)",
            required = false
    )
    protected long keepAlive;

    @ApiModelProperty(
            notes = "Email address to contact in case of problems occurring while executing a callback",
            required = false
    )
    protected String alertEmail;

    @ApiModelProperty(
            notes = "The payload’s type<br/>" +
                    "<ul>" +
                    "<li>1 -> Regular (raw payload)</li>" +
                    "<li>2 -> Custom grammar</li>" +
                    "<li>3 -> Geolocation</li>" +
                    "<li>4 -> Display in ASCII</li>" +
                    "<li>5 -> Radio planning frame</li>" +
                    "<li>6 -> Sensitv2</li>" +
                    "</ul>",
            required = false
    )
    protected int payloadType;

    @ApiModelProperty(
            notes = "The payload configuration. Required if the payload type is Custom, else ignored.",
            required = false
    )
    protected String payloadConfig;


    @ApiModelProperty(
            notes = "The downlink mode to use for the devices of this device type.<br/>" +
                    "<ul>" +
                    "<li>0 -> DIRECT</li>" +
                    "<li>1 -> CALLBACK</li>" +
                    "<li>2 -> NONE</li>" +
                    "</ul>",
            required = true
    )
    protected int downlinkMode;

    @ApiModelProperty(
            notes = "Downlink data to be sent to the devices of this device type if downlinkMode is equal to 0. " +
                    "It must be an 8 byte length message given in hexadecimal string format.",
            required = false
    )
    protected String downlinkDataString;


    @ApiModelProperty(
            notes = "The device type’s description",
            required = true
    )
    protected String description;

    @ApiModelProperty(
            notes = "The device type’s contract identifier (must be on the same group than the device type)",
            required = true
    )
    protected String groupId;

    @ApiModelProperty(
            notes = "The device type’s contract identifier (must be on the same group than the device type)",
            required = true
    )
    protected String contractId;


    @ApiModelProperty(
            notes = "The geoloc payload configuration identifier. " +
                    "Required if the payload type is Geolocation, else ignored.",
            required = false
    )
    protected String geolocPayloadConfigId;


    @ApiModelProperty(
            notes = "Allows the automatic renewal of devices attached to this device type",
            required = false
    )
    protected boolean automaticRenewal;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(long keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getAlertEmail() {
        return alertEmail;
    }

    public void setAlertEmail(String alertEmail) {
        this.alertEmail = alertEmail;
    }

    public int getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(int payloadType) {
        this.payloadType = payloadType;
    }

    public String getPayloadConfig() {
        return payloadConfig;
    }

    public void setPayloadConfig(String payloadConfig) {
        this.payloadConfig = payloadConfig;
    }

    public int getDownlinkMode() {
        return downlinkMode;
    }

    public void setDownlinkMode(int downlinkMode) {
        this.downlinkMode = downlinkMode;
    }

    public String getDownlinkDataString() {
        return downlinkDataString;
    }

    public void setDownlinkDataString(String downlinkDataString) {
        this.downlinkDataString = downlinkDataString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getGeolocPayloadConfigId() {
        return geolocPayloadConfigId;
    }

    public void setGeolocPayloadConfigId(String geolocPayloadConfigId) {
        this.geolocPayloadConfigId = geolocPayloadConfigId;
    }

    public boolean isAutomaticRenewal() {
        return automaticRenewal;
    }

    public void setAutomaticRenewal(boolean automaticRenewal) {
        this.automaticRenewal = automaticRenewal;
    }
}

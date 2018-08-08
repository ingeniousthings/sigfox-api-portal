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

@Api(tags = "Device type", description = "Defines the device type’s properties")
public class SigfoxApiv2DeviceType extends SigfoxApiv2DeviceTypeMinimal {

//    @ApiModelProperty(
//            notes = "The device type’s name",
//            required = false
//    )
//    protected String name;

    @ApiModelProperty(
            notes = "Keep alive period in seconds (0 to not keep alive else 1800 second minimum)",
            example = "6000",
            required = false
    )
    protected long keepAlive;

    @ApiModelProperty(
            notes = "Email address to contact in case of problems occurring while executing a callback",
            example = "alert@foo.bar",
            required = false
    )
    protected String alertEmail;

//    @ApiModelProperty(
//            notes = "The device type’s identifier",
//            required = false
//    )
//    protected String id;


    @ApiModelProperty(
            notes = "The device type’s description",
            example = "My demo device type",
            required = false
    )
    protected String description;

    @ApiModelProperty(
            notes = "The downlink mode to use for the devices of this device type.<br/>" +
                    "<ul>" +
                    "<li>0 -> DIRECT</li>" +
                    "<li>1 -> CALLBACK</li>" +
                    "</ul>",
            example = "1",
            required = false
    )
    protected int downlinkMode;

    @ApiModelProperty(
            notes = "Downlink data to be sent to the devices of this device type if downlinkMode is equal to 0. " +
                    "It must be an 8 byte length message given in hexadecimal string format.",
            example = "'{tapId}0000{rssi}'",
            required = false
    )
    protected String downlinkDataString;

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
            example = "1",
            required = false
    )
    protected int payloadType;

    @ApiModelProperty(
            notes = "The payload configuration. Required if the payload type is Custom, else ignored.",
            example = "Firmware_Version::uint:8 Voltage_Value::uint:16:little-endian",
            required = false
    )
    protected String payloadConfig;


    @ApiModelProperty(
            notes = "The group entity owner of this device type",
            required = false
    )
    protected SigfoxApiv2GroupMinimal group;

    @ApiModelProperty(
            notes = "The contract entity attached of this device type",
            required = false
    )
    protected SigfoxApiv2ContractInfoMin contract;

    @ApiModelProperty(
            notes = "The geoloc payload attached to the device type",
            required = false
    )
    protected SigfoxApiv2GeolocPayloadConfig geolocPayloadConfig;

    @ApiModelProperty(
            notes = "Date of the creation of this device type (in milliseconds)",
            example = "1462801032158",
            required = false
    )
    protected long creationTime;

    @ApiModelProperty(
            notes = "Identifier of the user who created this device type.",
            example = "57309674171c857460043087",
            required = false
    )
    protected String createdBy;

    @ApiModelProperty(
            notes = "Date of the last edition of this device type (in milliseconds)",
            example = "1462801032158",
            required = false
    )
    protected long lastEditedTime;

    @ApiModelProperty(
            notes = "Identifier of the user who last edited this device type.",
            example = "57309674171c857460043087",
            required = false
    )
    protected String lastEditedBy;

    @ApiModelProperty(
            notes = "Allows the automatic renewal of devices attached to this device type",
            example = "true",
            required = false
    )
    protected boolean automaticRenewal;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

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

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public SigfoxApiv2GroupMinimal getGroup() {
        return group;
    }

    public void setGroup(SigfoxApiv2GroupMinimal group) {
        this.group = group;
    }

    public SigfoxApiv2ContractInfoMin getContract() {
        return contract;
    }

    public void setContract(SigfoxApiv2ContractInfoMin contract) {
        this.contract = contract;
    }

    public SigfoxApiv2GeolocPayloadConfig getGeolocPayloadConfig() {
        return geolocPayloadConfig;
    }

    public void setGeolocPayloadConfig(SigfoxApiv2GeolocPayloadConfig geolocPayloadConfig) {
        this.geolocPayloadConfig = geolocPayloadConfig;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getLastEditedTime() {
        return lastEditedTime;
    }

    public void setLastEditedTime(long lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
    }

    public String getLastEditedBy() {
        return lastEditedBy;
    }

    public void setLastEditedBy(String lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    public boolean isAutomaticRenewal() {
        return automaticRenewal;
    }

    public void setAutomaticRenewal(boolean automaticRenewal) {
        this.automaticRenewal = automaticRenewal;
    }
}

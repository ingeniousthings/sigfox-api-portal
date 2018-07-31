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

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;

import java.io.IOException;

/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a device type. this object can handle a
 * link to associated callbacks.
 *
 * This class is also used to compose a DeviceInit Type from a Json external data with the objective
 * to be deployed automatically in the backend. Json data have same format but includes the callbackentries
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *      "id" : "4d3091a05ee16b3cc86699ab",
 *      "name" : "Sigfox test device",
 *      "group" : "4d39a4c9e03e6b3c430e2188",
 *      "description" : "Little things in the black boxes",
 *      "keepAlive" : 7200,
 *      "payloadType" : "None",
 *      "contract" : "523b1d10d777d3f5ae038a02"
 *  }
 *
 * @author Paul Pinault
 */
public class SigfoxApiDeviceTypeEdition {

    // -----------------------------------------
    // Internals
    // -----------------------------------------
    @ApiModelProperty(
            notes = "The device type’s identifier",
            required = true
    )
    private String id;


    @ApiModelProperty(
            notes = "The device type’s name",
            required = true
    )
    private String name;

    @ApiModelProperty(
            notes = "The description of this device type",
            required = true
    )
    private String description;

    @ApiModelProperty(
            notes = "the keep alive in minutes (0 mean inactive)",
            required = false
    )
    private int    keepAlive;

    @ApiModelProperty(
            notes = "the payload type. Here are the values that can be set : " +
                    "<ul>" +
                    "<li>\"None\" if no specific type of payload must be set</li>" +
                    "<li>\"String\" to display the data as an ascci string </li>" +
                    "<li>\"Custom\" to display the frame as a custom format. The format must be defined with a field payloadConfig containing the line config.</li>" +
                    "<li>\"Geolocation\" to display the data as a location with a certain format. It must be associated with a geolocPayloadConfigId field containing the id of the geolocation configuration. " +
                    "The id can be obtained with the /api/geoloc-config/list API</li>" +
                    "</ul>",
            required = true
    )
    private String payloadType;


    @ApiModelProperty(
            notes = "Downlink Mode 0 for direct downlink, 1 for callback downlink mode. (creation only)",
            required = false
    )
    private int    downlinkMode;        // 0 : direct 1 : through callback

    @ApiModelProperty(
            notes = "in mode 0 (direct), set the data that will be sent to the devices members of this device type. " +
                    "It must be an 8 hexadecimal bytes string. (creation only)",
            required = false
    )
    private String downlinkDataString;

    @ApiModelProperty(
            notes = "email to send alert - not documented",
            required = false
    )
    private String alertEmail;


    // -----------------------------------------
    // Object initialization from Json data
    // -----------------------------------------

    public static SigfoxApiDeviceTypeEdition createFromJson(String _json ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        SigfoxApiDeviceTypeEdition devicetype = mapper.readValue(_json, SigfoxApiDeviceTypeEdition.class);

        return devicetype;

    }


    // -----------------------------------------
    // Custom Getters & Setters
    // -----------------------------------------

    // -----------------------------------------
    // Generated Getters & Setters
    // -----------------------------------------


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(String payloadType) {
        this.payloadType = payloadType;
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

    public String getAlertEmail() {
        return alertEmail;
    }

    public void setAlertEmail(String alertEmail) {
        this.alertEmail = alertEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
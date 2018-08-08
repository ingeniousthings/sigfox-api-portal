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

@Api(tags = "errorMessages", description = "Callback execution errors report")
public class SigfoxApiv2CallbackErrorMessages {

    @ApiModelProperty(
            notes = "Device identifier",
            example = "1BC03",
            required = false
    )
    protected String device;

    @ApiModelProperty(
            notes = "Url to the device",
            example = "not seen in response",
            required = false
    )
    protected String deviceUrl;

    @ApiModelProperty(
            notes = "Device type identifier",
            example = "5948715d3a878972c8af8e63",
            required = false
    )
    protected String deviceType;

    @ApiModelProperty(
            notes = "Timestamp of the message in ms (posix format)",
            example = "1533725328000",
            required = false
    )
    protected long time;

    @ApiModelProperty(
            notes = "Date of the message in String format",
            example = "2018-08-08 12:48:48",
            required = false
    )
    protected String date;

    @ApiModelProperty(
            notes = "Raw data message",
            example = "not seen in response",
            required = false
    )
    protected String raw;

    @ApiModelProperty(
            notes = "Data message",
            example = "12d8464646460021000f0000",
            required = false
    )
    protected String data;

    @ApiModelProperty(
            notes = "The SNR of the messages received by the network so far",
            example = "not seen in response",
            required = false
    )
    protected String snr;

    @ApiModelProperty(
            notes = "Contains the callback response status.",
            example = "600",
            required = false
    )
    protected String status;

    @ApiModelProperty(
            notes = "Contains additional information on the response.",
            example = "Unresolved network address",
            required = false
    )
    protected String message;

    @ApiModelProperty(
            notes = "Callback of type HTTP",
            required = false
    )
    protected SigfoxApiv2CallbackExecutionReport callback;

    @ApiModelProperty(
            notes = "All the parameters which have served to build the callback, see callback doc for an exhaustive list.",
            required = false
    )
    protected KeyValue parameters;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDeviceUrl() {
        return deviceUrl;
    }

    public void setDeviceUrl(String deviceUrl) {
        this.deviceUrl = deviceUrl;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSnr() {
        return snr;
    }

    public void setSnr(String snr) {
        this.snr = snr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SigfoxApiv2CallbackExecutionReport getCallback() {
        return callback;
    }

    public void setCallback(SigfoxApiv2CallbackExecutionReport callback) {
        this.callback = callback;
    }

    public KeyValue getParameters() {
        return parameters;
    }

    public void setParameters(KeyValue parameters) {
        this.parameters = parameters;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

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

import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;


/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a Message sent by a device.
 *
 * ----------------------------------------------------------------------------------
 * Response Format when coming from the history:
 *  {
 *          "device" : "002C",
 *          "time" : 1343321977,
 *          "data" : "3235353843fc",
 *          "snr" : "38.2",
 *          "computedLocation": {
 *              "lat" : 43.45,
 *              "lng" : 6.54,
 *              "radius": 500
 *          },
 *          "linkQuality" : "GOOD",
 *          "downlinkAnswerStatus" : {
 *              "data" : "1511000a00007894"
 *          }
 *  }
 * Response Format when coming from the error list:
 * {
 *          device: "7B40E",
 *          deviceUrl: "/device/504846",
 *          deviceType: "device_type_id",
 *          time: 1501941440000,
 *          date: "2017-08-05 15:57:20",
 *          raw: "10cd37383838290500000000",
 *          data: "10cd37383838290500000000",
 *          snr: 42.43,
 *          status: 403,
 *          message: "Forbidden",
 *          callback: {
 *              url: "https://api.staging.ingeniousthings.fr/capture/api/sigfox",
 *              headers: { },
 *              method: "POST",
 *              contentType: "application/json",
 *              body: {
 *                  time: 1501941440,
 *                  your body content
 *              }
 *          },
 *          parameters: {
 *              time :  154241673,
 *              Your_data_list
 *          }
 },
 * @author Paul Pinault
 */
public class SigfoxApiMessageInformation {

    // ------------------------------------------------------------------
    // This part is common to any source of messages
    public static final int MESSAGESOURCE_HISTORY   = 0;
    public static final int MESSAGESOURCE_ERRORS    = 1;

    @ApiModelProperty(
            notes = "Device identifier",
            required = false
    )
    protected String    device;

    @ApiModelProperty(
            notes = "Time the message was sent, in seconds since the Unix Epoch",
            required = false
    )
    protected long      time;

    @ApiModelProperty(
            notes = "Message content, hex encoded",
            required = false
    )
    protected String    data;

    @ApiModelProperty(
            notes = "The link quality indicator value (LIMIT, AVERAGE, GOOD or EXCELLENT)",
            required = false
    )
    protected String    linkQuality;
    protected double    snr;    // deprecated


    @ApiModelProperty(
            notes = "The sequence number for this message, may not be present when device uses V0 protocol ",
            required = false
    )
    protected int       seqNumber;

    @ApiModelProperty(
            notes = "The number of frames expected for the message ",
            required = false
    )
    protected int       nbFrames;


    @ApiModelProperty(
            notes = "Internal use for the type of message",
            required = false
    )
    protected int       source;

    // ------------------------------------------------------------------
    // This part is related to message from message history

    public static final String LINKQUALITY_LIMIT = "LIMIT";
    public static final String LINKQUALITY_AVERAGE = "AVERAGE";
    public static final String LINKQUALITY_GOOD = "GOOD";
    public static final String LINKQUALITY_EXCELLENT = "EXCELLENT";

    public class MessageLocation {

        @ApiModelProperty(
                notes = "The estimated latitude",
                required = false
        )
        protected double lat;

        @ApiModelProperty(
                notes = "The estimated longitude",
                required = false
        )
        protected double lng;

        @ApiModelProperty(
                notes = "The radius of the circle",
                required = false
        )
        protected int radius;

        @ApiModelProperty(
                notes = "Define from which source the geolocation has been computed :" +
                        "<ul>" +
                        "<li>0 Location computed by legacy mode using RSSI and position of station</li>" +
                        "<li>1 Location computed using the GPS data inside the payload</li>" +
                        "<li>2 Location computed by Atlas Network </li>" +
                        "<li>3 Location computed by Atlas POI</li>" +
                        "<li>4 Location computed by Atlas HD</li>" +
                        "</ul>",
                required = false
        )
        protected int source;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        @Override
        public String toString() {
            return "MessageLocation{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    ", radius=" + radius +
                    ", source=" + source +
                    '}';
        }
    }
    public class DownlinkMessage {
        @ApiModelProperty(
                notes = "Response content, hex encoded",
                required = false
        )
        protected String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "DownlinkMessage{" +
                    "data='" + data + '\'' +
                    '}';
        }
    }
    static class KeyValue {
        @JsonIgnore
        private Map<String,Object> entry = new HashMap<String, Object>();

        @JsonAnyGetter
        public Map<String, Object> getEntry() {
            return entry;
        }

        @JsonAnySetter
        public void setEntry(String name, Object value) {
            this.entry.put(name,value);
        }

        @Override
        public String toString() {
            return entry.toString();
        }
    }

    public class CbStatusInfos {
        @ApiModelProperty(
                notes = "Http response status",
                required = false
        )
        public int status;

        @ApiModelProperty(
                notes = "Http response message",
                required = false
        )
        public String info;

        @ApiModelProperty(
                notes = "Callback definition triggered",
                required = false
        )
        public String cbDef;

        @ApiModelProperty(
                notes = "Time the callback was called, in milliseconds since the Unix Epoch",
                required = false
        )
        public long time;
    }

    public class ReceptionInfos {
        @ApiModelProperty(
                notes = "Base station identifier (in hexadecimal, 4 digits)",
                required = false
        )
        public String tap;
        @ApiModelProperty(
                notes = "The current latitude of the base station which received the message if available",
                required = false
        )
        public double lat;
        @ApiModelProperty(
                notes = "The current longitude of the base station which received the message if available",
                required = false
        )
        public double lng;

        @ApiModelProperty(
                notes = "List of callback status for this reception (only visible if the cbStatus parameter is set to true) ",
                required = false
        )
        public List<CbStatusInfos> cbStatus;

    }

    @ApiModelProperty(
            notes = "List all receptions for each base stations",
            required = false
    )
    protected List<ReceptionInfos> rinfos;

    @ApiModelProperty(
            notes = "Contains the estimated position of the device within a circle based on the GPS data or the Sigfox Geolocation" +
                    "service. GPS data is used if the device has a device type with payload type \"Geolocation\", while Sigfox" +
                    "Geolocation service is used if the device is attached to a contract with the Sigfox Geolocation service option" +
                    "enabled.",
            required = false
    )
    protected MessageLocation   computedLocation;

    @ApiModelProperty(
            notes = "The last callback status for this reception (only visible if the dlkAnswerStatus parameter is set to true) ",
            required = false
    )
    protected DownlinkMessage   downlinkAnswerStatus;

    // -------------------------------------------------------------------
    // This part is related to message from message error
    protected String deviceUrl;
    protected String deviceType;
    protected String date;
    protected String raw;
    protected int    status;
    protected String message;
    protected SigfoxApiCallbackInformation callback;
    protected KeyValue parameters;


    // ------------------------------------------------------------------
    // Advanced function




    // ------------------------------------------------------------------
    // Generated Getter & Setters

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getSnr() {
        return snr;
    }

    public void setSnr(double snr) {
        this.snr = snr;
    }

    public SigfoxApiMessageInformation.MessageLocation getComputedLocation() {
        return computedLocation;
    }

    public void setComputedLocation(SigfoxApiMessageInformation.MessageLocation computedLocation) {
        this.computedLocation = computedLocation;
    }

    public String getLinkQuality() {
        return linkQuality;
    }

    public void setLinkQuality(String linkQuality) {
        this.linkQuality = linkQuality;
        this.source = MESSAGESOURCE_HISTORY;
    }

    public SigfoxApiMessageInformation.DownlinkMessage getDownlinkAnswerStatus() {
        return downlinkAnswerStatus;
    }

    public void setDownlinkAnswerStatus(SigfoxApiMessageInformation.DownlinkMessage downlinkAnswerStatus) {
        this.downlinkAnswerStatus = downlinkAnswerStatus;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        this.source = MESSAGESOURCE_ERRORS;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SigfoxApiCallbackInformation getCallback() {
        return callback;
    }

    public void setCallback(SigfoxApiCallbackInformation callback) {
        this.callback = callback;
    }

    public SigfoxApiMessageInformation.KeyValue getParameters() {
        return parameters;
    }

    public void setParameters(SigfoxApiMessageInformation.KeyValue parameters) {
        this.parameters = parameters;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public int getNbFrames() {
        return nbFrames;
    }

    public void setNbFrames(int nbFrames) {
        this.nbFrames = nbFrames;
    }

    public List<ReceptionInfos> getRinfos() {
        return rinfos;
    }

    public void setRinfos(List<ReceptionInfos> rinfos) {
        this.rinfos = rinfos;
    }

    // ------------------------------------------------------
    // Serialization

    @Override
    public String toString() {
        String str = "SigfoxApiMessageInformation{" +
                "source=" + this.source +
                ", device='" + device + '\'' +
                ", time=" + time +
                ", data='" + data + '\'' +
                ", snr=" + snr;
        switch ( this.source ) {
            case MESSAGESOURCE_HISTORY :
                str += ", linkQuality='" + linkQuality + '\'';
                if ( computedLocation != null ) {
                    str += ", computedLocation=" + computedLocation;
                }
                if ( downlinkAnswerStatus != null ) {
                    str += ", downlinkAnswerStatus=" + downlinkAnswerStatus;
                }
                break;
            case MESSAGESOURCE_ERRORS :
                str +=  ", deviceUrl='" + deviceUrl + '\'' +
                        ", deviceType='" + deviceType + '\'' +
                        ", date='" + date + '\'' +
                        ", raw='" + raw + '\'' +
                        ", data='" + data + '\'' +
                        ", status=" + status +
                        ", message='" + message + '\'' +
                        ", callback=" + callback +
                        ", parameters='" + parameters + '\'';

                break;
        }
        str += '}';
        return str;
    }

}



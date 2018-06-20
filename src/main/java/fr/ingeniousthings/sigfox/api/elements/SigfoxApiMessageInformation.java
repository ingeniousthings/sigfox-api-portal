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

import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;


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

    protected String    device;
    protected long      time;
    protected String    data;
    protected double    snr;
    protected int       source;

    // ------------------------------------------------------------------
    // This part is related to message from message history

    public static final String LINKQUALITY_LIMIT = "LIMIT";
    public static final String LINKQUALITY_AVERAGE = "AVERAGE";
    public static final String LINKQUALITY_GOOD = "GOOD";
    public static final String LINKQUALITY_EXCELLENT = "EXCELLENT";

    public class MessageLocation {
        double lat;
        double lng;
        int radius;

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

        @Override
        public String toString() {
            return "MessageLocation{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    ", radius=" + radius +
                    '}';
        }
    }
    public class DownlinkMessage {
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


    protected MessageLocation   computedLocation;
    protected String            linkQuality;
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



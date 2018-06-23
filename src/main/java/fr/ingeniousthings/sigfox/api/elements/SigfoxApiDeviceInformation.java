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

import io.swagger.annotations.ApiModelProperty;

import java.io.IOException;
import java.lang.Override;
import java.lang.String;


/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a DeviceInit.
 *
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *      "id" : "0B59",
 *      "name" : "Test 0B59",
 *      "type" : "4d3091a05ee16b3cc86699ab",        // Link to the device Type id
 *      "last" : 0,                                 // Last message sent ( EPOC in S)
 *      "averageSignal": 8.457,                     // based on last 25 messages
 *      "averageSnr": 8.457,                        //   ...
 *      "averageRssi": -125.967,                    //   ...
 *      "state": 0,
 *      "lat" : 43.45,
 *      "lng" : 1.54,
 *      "computedLocation": {                       // null when the device is not positionned by network
 *          "lat" : 43.45,
 *          "lng" : 6.54,
 *          "radius": 500
 *      },
 *      "activationTime": 1404096340556,
 *      "pac": "545CB3B17AC98BA4",
 *      "tokenType": "CONTRACT",
 *      "contractId": "7896541254789654aedfba4c",
 *      "tokenEnd": 1449010800000,
 *      "preventRenewal": false
 *  }
 *
 * @author Paul Pinault
 */
public class SigfoxApiDeviceInformation {

    public static final int    STATE_OK            = 0;
    public static final int    STATE_DEAD          = 1;        // communication timeout ??
    public static final int    STATE_OFF_CONTRACT  = 2;
    public static final int    STATE_DISABLE       = 3;        // in tranfert
    public static final int    STATE_WARN          = 4;        // network issue ?
    public static final int    STATE_DELETED       = 5;

    public static final String TOKENTYPE_CONTRACT  = "CONTRACT";
    public static final String TOKENTYPE_FREE  = "FREE";


    public class DeviceLocation {
        @ApiModelProperty(
                notes = "The estimated latitude",
                required = true
        )
        double lat;

        @ApiModelProperty(
                notes = "The estimated longitude",
                required = true
        )
        double lng;

        @ApiModelProperty(
                notes = "The radius of the circle",
                required = true
        )
        int radius;

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
            return "DeviceLocation{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    ", radius=" + radius +
                    ", source=" + source +
                    '}';
        }
    }

    @ApiModelProperty(
            notes = "The device’s identifier",
            required = true
    )
    protected String    id;

    @ApiModelProperty(
            notes = "The device’s friendly name",
            required = false
    )
    protected String    name;

    @ApiModelProperty(
            notes = "The device type’s identifier",
            required = true
    )
    protected String    type;

    @ApiModelProperty(
            notes = "Last time when this device has sent a message, in seconds since the Unix Epoch. A value of zero means the device has never sent a message",
            required = false
    )
    protected long      last;

//    @ApiModelProperty(
//            notes = "[DEPRECATED] The average signal computed from the last 25 received messages (in dB)",
//            required = true
//    )
//    protected double    averageSignal;

    @ApiModelProperty(
            notes = "The average signal computed from the last 25 received messages (in dB)",
            required = false
    )
    protected double    averageSnr;


    @ApiModelProperty(
            notes = "The average RSSI computed from the last 25 received messages (in dBm)",
            required = true
    )
    protected double    averageRssi;


    @ApiModelProperty(
            notes = "The device state:" +
                    "<ul>" +
                    "<li>0: OK</li>" +
                    "<li>1: Dead – Communication timeout</li>" +
                    "<li>2: Off contract – Communication forbidden</li>" +
                    "<li>3: Disabled – The device is about to be transferred</li>" +
                    "<li>4: Warn – Network issues</li>" +
                    "<li>5: Deleted – The device is about to be deleted</li>" +
                    "</ul> ",
            required = true
    )
    protected int       state;

    @ApiModelProperty(
            notes = "The provided latitude of the device",
            required = true
    )
    protected double    lat;

    @ApiModelProperty(
            notes = "The provided longitude of the device",
            required = true
    )
    protected double    lng;

    @ApiModelProperty(
            notes = "Contains the estimated position of the device within a circle based on the GPS data or the Sigfox Geolocation service" +
                    "GPS data is used if the device has a device type with payload type \"Geolocation\",while Sigfox Geolocation service is " +
                    "used if the device is attached to a contract with the Sigfox Geolocation service option enabled.",
            required = true
    )
    protected DeviceLocation    computedLocation;

    @ApiModelProperty(
            notes = "Time of the contract token taking, in milliseconds since the Unix Epoch. no value is returned if the device does not have a token",
            required = true
    )
    protected long      activationTime;

    @ApiModelProperty(
            notes = "The device's PAC",
            required = true
    )
    protected String    pac;

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

    @ApiModelProperty(
            notes = "«true» if token renewal is deactivated.",
            required = true
    )
    protected boolean   preventRenewal;

    @ApiModelProperty(
            notes = "Metric information about a device [NOT FILLED]",
            required = false
    )
    protected SigfoxApiMessageMetric  metric = null;


    // ------------------------------------------------------------------
    // Advanced function

    // daysleft - return the number of day before token end.
    // 0 when already ended
    public int daysleft() {
        long now = System.currentTimeMillis();
        long days = (this.tokenEnd-(now))/(3600*24*1000);
        return (int)((days>0)?days:0);
    }




    // ------------------------------------------------------------------
    // Generated Getter & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getLast() {
        return last;
    }

    public void setLast(long last) {
        this.last = last;
    }

    public double getAverageSnr() {
        return averageSnr;
    }

    public void setAverageSnr(double averageSnr) {
        this.averageSnr = averageSnr;
    }

    public double getAverageRssi() {
        return averageRssi;
    }

    public void setAverageRssi(double averageRssi) {
        this.averageRssi = averageRssi;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

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

    public SigfoxApiDeviceInformation.DeviceLocation getComputedLocation() {
        return computedLocation;
    }

    public void setComputedLocation(SigfoxApiDeviceInformation.DeviceLocation computedLocation) {
        this.computedLocation = computedLocation;
    }

    public long getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(long activationTime) {
        this.activationTime = activationTime;
    }

    public String getPac() {
        return pac;
    }

    public void setPac(String pac) {
        this.pac = pac;
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

    public long getTokenEnd() {
        return tokenEnd;
    }

    public void setTokenEnd(long tokenEnd) {
        this.tokenEnd = tokenEnd;
    }

    public boolean isPreventRenewal() {
        return preventRenewal;
    }

    public void setPreventRenewal(boolean preventRenewal) {
        this.preventRenewal = preventRenewal;
    }

    public SigfoxApiMessageMetric getMetric() {
        return metric;
    }

    public void setMetric(SigfoxApiMessageMetric metric) {
        this.metric = metric;
    }

    public String getFreeMessages() {
        return freeMessages;
    }

    public void setFreeMessages(String freeMessages) {
        this.freeMessages = freeMessages;
    }

    // ------------------------------------------------------
    // Serialization


    @Override
    public String toString() {
        String str = "SigfoxApiDeviceInformation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", last=" + last +
                ", averageSnr=" + averageSnr +
                ", averageRssi=" + averageRssi +
                ", state=" + state +
                ", lat=" + lat +
                ", lng=" + lng +
                ", activationTime=" + activationTime +
                ", pac='" + pac + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", contractId='" + contractId + '\'' +
                ", freeMessages='" + freeMessages + '\''+
                ", tokenEnd=" + tokenEnd +
                ", preventRenewal=" + preventRenewal;
        if ( computedLocation != null ) {
            str += ", computedLocation=" + computedLocation;
        }
        if ( metric != null ) {
            str += ", metric=" + metric;
        }
        str += '}';
        return str;
    }
}



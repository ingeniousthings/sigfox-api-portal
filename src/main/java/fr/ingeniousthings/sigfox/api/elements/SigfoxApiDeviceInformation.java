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
            return "DeviceLocation{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    ", radius=" + radius +
                    '}';
        }
    }

    protected String    id;
    protected String    name;
    protected String    type;
    protected long      last;
    protected double    averageSignal;
    protected double    averageSnr;
    protected double    averageRssi;
    protected int       state;
    protected double    lat;
    protected double    lng;
    protected DeviceLocation    computedLocation;
    protected long      activationTime;
    protected String    pac;
    protected String    tokenType;
    protected String    contractId;
    protected long      tokenEnd;
    protected boolean   preventRenewal;

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

    public double getAverageSignal() {
        return averageSignal;
    }

    public void setAverageSignal(double averageSignal) {
        this.averageSignal = averageSignal;
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

    // ------------------------------------------------------
    // Serialization


    @Override
    public String toString() {
        String str = "SigfoxApiDeviceInformation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", last=" + last +
                ", averageSignal=" + averageSignal +
                ", averageSnr=" + averageSnr +
                ", averageRssi=" + averageRssi +
                ", state=" + state +
                ", lat=" + lat +
                ", lng=" + lng +
                ", activationTime=" + activationTime +
                ", pac='" + pac + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", contractId='" + contractId + '\'' +
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



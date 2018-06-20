/*
 * Copyright 2017 Ingeniousthings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.ingeniousthings.sigfox;

import java.lang.Override;
import java.lang.String;

/**
 * A representation of a <a href="https://www.sigfox.com">Sigfox</a> message.
 * Sigfox message can have different type, this is describing a Data Message
 * The bakend supported configuration is DATA / BIDIR / URL with duplicate with content type application/json
 * Body is :
 * {
 *  "time": {time},
 *  "device": "{device}",
 *  "type" : "data",
 *  "station": "{station}",
 *  "seq": {seqNumber},
 *  "ack": {ack},
 *  "duplicate": {duplicate},
 *  "signal": {snr},
 *  "avgSignal": "{avgSnr}",
 *  "rssi": {rssi},
 *  "lat": {lat},
 *  "lng": {lng},
 *  "data": "{data}"
 * }
 *
 * @author Paul Pinault
 */
public class SigfoxDataMessage extends SigfoxMessage {

    private String device;
    private long time;
    private String type;
    private boolean duplicate;
    private double signal;
    private String station;
    private String data;
    private int lat;
    private int lng;
    private int seq;
    private double avgSignal;
    private boolean ack;
    private double rssi;

    public String getDevice() {
        return device;
    }
    public void setDevice(String device) {
        this.device = device;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }

    public boolean isDuplicate() {
        return duplicate;
    }
    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    public String getStation() {
        return station;
    }
    public void setStation(String station) {
        this.station = station;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public int getLat() {
        return lat;
    }
    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }
    public void setLng(int lng) {
        this.lng = lng;
    }

    public boolean isAck() {
        return ack;
    }
    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public double getRssi() {
        return rssi;
    }
    public void setRssi(double rssi) {
        this.rssi = rssi;
    }

    public double getSignal() {
        return signal;
    }
    public void setSignal(double signal) {
        this.signal = signal;
    }

    public int getSeq() {
        return seq;
    }
    public void setSeq(int seq) {
        this.seq = seq;
    }

    public double getAvgSignal() {
        return avgSignal;
    }
    public void setAvgSignal(double avgSignal) {
        this.avgSignal = avgSignal;
    }

    @Override
    public String toString() {
        return "SigfoxDataMessage{" +
                "device=" + device +
                ", time=" + time +
                ", type=" + type +
                ", duplicate=" + duplicate +
                ", signal=" + signal +
                ", station=" + station +
                ", data=" + data +
                ", lat=" + lat +
                ", lng=" + lng +
                ", seq=" + seq +
                ", avgSignal=" + avgSignal +
                ", ack=" + ack +
                ", rssi=" + rssi +
                '}';
    }
}

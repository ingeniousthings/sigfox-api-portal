/*
 * Copyright 2016 Ingeniousthings
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

/**
 * A representation of a <a href="https://www.sigfox.com">Sigfox</a> message.
 *
 * @author Daniel Petisme
 */
public class SigfoxMessage {

    private String device;
    private long time;
    private boolean duplicate;
    private double snr;
    private String station;
    private String data;
    private int lat;
    private int lng;
    private int seqNumber;
    private double avgSnr;
    private boolean ack;
    private double rssi;

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

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    public double getSnr() {
        return snr;
    }

    public void setSnr(double snr) {
        this.snr = snr;
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

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public double getAvgSnr() {
        return avgSnr;
    }

    public void setAvgSnr(double avgSnr) {
        this.avgSnr = avgSnr;
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

    @Override
    public String toString() {
        return "SigfoxMessage{" +
            "device='" + device + '\'' +
            ", time=" + time +
            ", duplicate=" + duplicate +
            ", snr=" + snr +
            ", station='" + station + '\'' +
            ", data='" + data + '\'' +
            ", lat=" + lat +
            ", lng=" + lng +
            ", seqNumber=" + seqNumber +
            ", avgSnr=" + avgSnr +
            ", ack=" + ack +
            ", rssi=" + rssi +
            '}';
    }
}

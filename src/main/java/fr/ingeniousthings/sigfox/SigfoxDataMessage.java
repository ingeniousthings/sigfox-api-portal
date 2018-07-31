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

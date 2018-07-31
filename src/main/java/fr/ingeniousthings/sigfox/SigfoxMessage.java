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

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.System;
import java.util.regex.*;

/**
 * Summary
 *
 * Parse a sigfox incoming callback structure to extract and qualify the data
 * Later this object will be used to be map to a specific object. This allows to have
 * a common capture point whatever is the message type source.
 * The objective is to simplify the way we implement the callback in sigfox.
 * ----------------------------------------------------------------------------------
 * Support :
 *  - DATA POST
 *  - SERVICE POST
 *  - ERROR POST
 *
 * @author Paul Pinault
 */
public class SigfoxCommonMessage {

    protected static final Logger log = LoggerFactory.getLogger(SigfoxCommonMessage.class);

    public static final int INFOCODE_SUCCESS            =   0;
    public static final int INFOCODE_NOANSWER           =   1;
    public static final int INFOCODE_INVALIDPAYLOAD     =   2;
    public static final int INFOCODE_OVERRUN_ERROR      =   3;
    public static final int INFOCODE_NETWORK_ERROR      =   4;
    public static final int INFOCODE_PENDING            =   5;
    public static final int INFOCODE_NO_DWN_CONTRACT    =   6;
    public static final int INFOCODE_TOO_MANY_REQ       =   7;
    public static final int INFOCODE_INVALID_CONFIG     =   8;
    public static final int INFOCODE_ROAMING_PENDING    =   10;

    public static final String TYPE_DATA = "data";
    public static final String TYPE_SERVICE_STATUS = "srv_status";
    public static final String TYPE_SERVICE_ACK = "srv_ack";
    public static final String TYPE_SERVICE_GEOLOC = "srv_geoloc";
    public static final String TYPE_ERROR = "error";

    public enum MessageType {
        DATA,SERVICE_STATUS,SERVICE_ACK,SERVICE_GEOLOC, ERROR, UNKNOWN
    };

    // --------------------------------------------------------
    // interal elements
    @JsonIgnore private long messageUid = 0L;
    @JsonIgnore private boolean _messageUid = false;

    // --------------------------------------------------------
    // Common mandatory elements

    @JsonIgnore private boolean _device = false;
    private String device;

    @JsonIgnore private boolean _time = false;
    private long time;

    @JsonIgnore private boolean _type = false;
    private String type;
    @JsonIgnore private MessageType messagetype = MessageType.UNKNOWN;

    // -------------------------------------------------------
    // Other common but not especially mandatory

    @JsonIgnore private boolean _seq = false;
    private int seq;

    @JsonIgnore private boolean _lat = false;
    private double lat;

    @JsonIgnore private boolean _lng = false;
    private double lng;

    @JsonIgnore private boolean _radius = false;
    private int radius;

    @JsonIgnore private boolean _user = false;
    private String user;

    @JsonIgnore private boolean _version = false;
    private int version;

    // -------------------------------------------------------
    // Radio & network elements

    @JsonIgnore private boolean _duplicate = false;
    private boolean duplicate;

    @JsonIgnore private boolean _signal = false;
    private double signal;

    @JsonIgnore private boolean _station = false;
    private String station;

    @JsonIgnore private boolean _avgSignal = false;
    private double avgSignal;

    @JsonIgnore private boolean _rssi = false;
    private double rssi;

    // -------------------------------------------------------
    // Data specific messages
    @JsonIgnore private boolean _data = false;
    private String data;

    @JsonIgnore private boolean _ack = false;
    private boolean ack;

    // -------------------------------------------------------
    // Service specific messages
    @JsonIgnore private boolean _temp = false;
    private double  temp;

    @JsonIgnore private boolean _batt = false;
    private double batt;

    @JsonIgnore private boolean _infoCode = false;
    private int infoCode;

    @JsonIgnore private boolean _infoMessage = false;
    private String infoMessage;

    @JsonIgnore private boolean _downlinkAck = false;
    private String downlinkAck;

    @JsonIgnore private boolean _downlinkOverusage = false;
    private boolean downlinkOverusage;

    // -------------------------------------------------------
    // Error specific messages
    @JsonIgnore private boolean _info = false;
    private String info;

    @JsonIgnore private boolean _severity = false;
    private String severity;

    // ==================================================================================
    // Advanced functions
    // ----------------------------------------------------------------------------------
    //
    // ==================================================================================

    // -------------------------------------------------------------------------------
    // Verify the data received to be compliant with the expectation
    public boolean isValidMessage() {

        if ( !_device || !_time || !_type ) return false;

        switch ( this.messagetype ) {
            case DATA:
                if ( !_seq || !_data ) return false;
                break;
            case SERVICE_STATUS:
                if ( !_seq || !_temp || !_batt ) return false;
                break;
            case SERVICE_ACK:
                if ( !_infoCode ) return false;
                break;
            case SERVICE_GEOLOC:
                if ( !_radius || !_lat || !_lng ) return false;
                break;
            case ERROR:
                if ( !_info ) return false;
                break;
            default:
                return false;
        }
        return true;
    }



    // -------------------------------------------------------------------------------
    // Compose a message UID specific to a message (but common to message duplicates
    // Format is 8 bytes (long)
    // | DeviceId ( 4byte reverted ) 31 bits | 2 b MessageType | time (4 bytes) 31 bits
    public void composeMessageUid() {

        long messageType = 0;
        switch ( this.messagetype ) {
            case DATA:
                messageType = 3;
                break;
            case SERVICE_STATUS:
                messageType = 1;
                break;
            case SERVICE_ACK:
                messageType = 2;
                break;
            case SERVICE_GEOLOC:
                messageType = 4;
                break;
            case ERROR:
                messageType = 0;
                break;
        }
        long intDevice = Long.parseLong(this.device,16);
        long intRevertDevice = 0;
        intRevertDevice |= (intDevice & 1);
        for ( int i = 1 ; i < 32 ; i++) {
            intRevertDevice = intRevertDevice << 1;
            intDevice = intDevice >> 1;
            intRevertDevice |= (intDevice & 1);
        }

        this.messageUid  = ( this.time >> 1 ) & 0x7FFFFFFF;
        this.messageUid |= ( intRevertDevice << 33 );
        this.messageUid |= ( messageType & 3 ) << 31;
        this._messageUid = true;

    }

    // ==================================================================================
    // Convert Data
    // ----------------------------------------------------------------------------------
    //
    // ==================================================================================

    protected int hexCharToInt(char c) {
        if ( c >= '0' && c <= '9') return c-'0';
        if ( c >= 'a' && c <= 'f') return 10 + c -'a';
        if ( c >= 'A' && c <= 'F') return 10 + c -'A';
        return 0;
    }
    protected int hexStrToInt(String s) {
        return 16*hexCharToInt(s.charAt(0))+hexCharToInt(s.charAt(1));
    }

    public int[] getDataArray() {
        int sz = this.data.length()/2;
        int ret[] = new int[sz];

        for ( int i = 0 ; i < this.data.length() ; i+=2 ) {
            ret[i/2] = hexStrToInt(this.data.substring(i,i+2));
        }

        return ret;
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    // ==================================================================================
    // Getter & Setters
    // ----------------------------------------------------------------------------------
    //
    // ==================================================================================


    public long getMessageUid() {
        return messageUid;
    }

    public void setMessageUid(long messageUid) {
        this.messageUid = messageUid;
    }

    public String getDevice() {
        return device;
    }
    public void setDevice(String device) {
        if ( device.matches("^[0-9A-F]+$") ) {
            this.device = device;
            this._device = true;
        } else {
            log.error("Received invalid device id ("+device+")");
        }
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
        this._type = true;
        if ( this.type.compareToIgnoreCase(TYPE_DATA) == 0 ) {
            this.messagetype = MessageType.DATA;
        } else if ( this.type.compareToIgnoreCase(TYPE_SERVICE_STATUS) == 0) {
            this.messagetype = MessageType.SERVICE_STATUS;
        } else if ( this.type.compareToIgnoreCase(TYPE_SERVICE_ACK) == 0) {
            this.messagetype = MessageType.SERVICE_ACK;
        } else if ( this.type.compareToIgnoreCase(TYPE_SERVICE_GEOLOC) == 0 ) {
            this.messagetype = MessageType.SERVICE_GEOLOC;
        } else if ( this.type.compareToIgnoreCase(TYPE_ERROR) == 0) {
            this.messagetype = MessageType.ERROR;
        } else  this.messagetype = MessageType.UNKNOWN;
    }

    public MessageType getMessagetype() {
        return messagetype;
    }

    public long getTime() {
        return time;
    }
    public long getTimeMs() { return time*1000; }
    public void setTime(long time) {
        this.time = time;
        this._time = true;
    }

    public boolean isDuplicate() {
        return duplicate;
    }
    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
        this._duplicate = true;
    }

    public double getSignal() {
        return signal;
    }
    public void setSignal(double signal) {
        this.signal = signal;
        this._signal = true;
    }

    public String getStation() {
        return station;
    }
    public void setStation(String station) {
        this.station = station;
        this._station = true;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
        this._data=true;
    }

    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
        this._lat = true;
    }

    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
        this._lng = true;
    }

    public int getSeq() {
        return seq;
    }
    public void setSeq(int seq) {
        this.seq = seq;
        this._seq = true;
    }

    public double getAvgSignal() {
        return avgSignal;
    }
    public void setAvgSignal(double avgSignal) {
        this.avgSignal = avgSignal;
        this._avgSignal = true;
    }

    public boolean isAck() {
        return ack;
    }
    public void setAck(boolean ack) {
        this.ack = ack;
        this._ack = true;
    }

    public double getRssi() {
        return rssi;
    }
    public void setRssi(double rssi) {
        this.rssi = rssi;
        this._rssi = true;
    }

    public double getTemp() {
        return temp;
    }
    public void setTemp(double temp) {
        this.temp = temp;
        this._temp = true;
    }

    public double getBatt() {
        return batt;
    }
    public void setBatt(double batt) {
        this.batt = batt;
        this._batt = true;
    }

    public int getInfoCode() {
        return infoCode;
    }
    public void setInfoCode(int infoCode) {
        this.infoCode = infoCode;
        this._infoCode = true;
    }

    public String getInfoMessage() {
        return infoMessage;
    }
    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
        this._infoMessage = true;
    }

    public String getDownlinkAck() {
        return downlinkAck;
    }
    public void setDownlinkAck(String downlinkAck) {
        this.downlinkAck = downlinkAck;
        this._downlinkAck = true;
    }

    public boolean isDownlinkOverusage() {
        return downlinkOverusage;
    }
    public void setDownlinkOverusage(boolean downlinkOverusage) {
        this.downlinkOverusage = downlinkOverusage;
        this._downlinkOverusage = true;
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
        this._info = true;
    }

    public String getSeverity() {
        return severity;
    }
    public void setSeverity(String severity) {
        this.severity = severity;
        this._severity = true;
    }

    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
        this._radius = true;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
        this._user = true;
    }

    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
        this._version = true;
    }

    // ==================================================================================
    // Serialize
    // ----------------------------------------------------------------------------------
    //
    // ==================================================================================

    @Override
    public String toString() {
        String ret = "SigfoxCommonMessage{";
        ret += (_device)?("device='" + device + '\''):"";
        ret += (_time)?(", time=" + time):"";
        ret += (_messageUid)?(", messageUid=0x" + Long.toHexString(messageUid).toUpperCase()):"";
        ret += (_type)?(", type='" + type + '\''):"";
        ret += (_seq)?(", seq=" + seq):"";
        ret += (_data)?(", data='" + data + '\''):"";
        ret += (_duplicate)?(", duplicate=" + duplicate):"";
        ret += (_station)?(", station='" + station + '\''):"";
        ret += (_signal)?(", signal=" + signal):"";
        ret += (_avgSignal)?(", avgSignal=" + avgSignal):"";
        ret += (_rssi)?(", rssi=" + rssi):"";
        ret += (_lat)?(", lat=" + lat):"";
        ret += (_lng)?(", lng=" + lng):"";
        ret += (_radius)?(", radius=" + radius):"";
        ret += (_ack)?(", ack=" + ack):"";
        ret += (_temp)?(", temp=" + temp):"";
        ret += (_batt)?(", batt=" + batt):"";
        ret += (_infoCode)?(", infoCode=" + infoCode):"";
        ret += (_infoMessage)?(", infoMessage=" + infoMessage):"";
        ret += (_downlinkAck)?(", downlinkAck=" + downlinkAck):"";
        ret += (_downlinkOverusage)?(", downlinkOverusage=" + downlinkOverusage):"";
        ret += (_info)?(", info=" +info):"";
        ret += (_severity)?(", severity=" + severity):"";
        ret += (_user)?(", user=" + user):"";
        ret += (_version)?(", version=" + version):"";
        ret += '}';
        return ret;

    }
}

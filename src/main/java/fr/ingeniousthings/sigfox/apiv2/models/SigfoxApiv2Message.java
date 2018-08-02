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

@Api(tags = "deviceMessage", description = "Message received from a device")
public class SigfoxApiv2Message {

    @ApiModelProperty(
            notes = "The recipient of the email.",
            required = false
    )
    protected SigfoxApiv2DeviceMinimal device;

    @ApiModelProperty(
            notes = "Timestamp of the message (in milliseconds since the Unix Epoch).",
            required = false
    )
    protected long time;

    @ApiModelProperty(
            notes = "Message content, hex encoded.",
            required = false
    )
    protected String data;

    @ApiModelProperty(
            notes = "True if an acknowledge is required.",
            required = false
    )
    protected boolean ackRequired;

    @ApiModelProperty(
            notes = "Link quality indicator<br/>" +
                    "<ul>" +
                    "<li>0 -> LIMIT</li>" +
                    "<li>1 -> AVERAGE</li>" +
                    "<li>2 -> GOOD</li>" +
                    "<li>3 -> EXCELLENT</li>" +
                    "<li>4 -> NA</li>" +
                    "</ul>",
            required = false
    )
    protected int linkQuality;

    @ApiModelProperty(
            notes = "Link quality indicator for repeated message<br/>" +
            "<ul>" +
            "<li>0 -> LIMIT</li>" +
            "<li>1 -> AVERAGE</li>" +
            "<li>2 -> GOOD</li>" +
            "<li>3 -> EXCELLENT</li>" +
            "<li>4 -> NA</li>" +
            "</ul>",
            required = false
    )
    protected int linkQualityRepeaters;

    @ApiModelProperty(
            notes = "The sequence number for this message, may not be " +
                    "present when device uses VO protocol.",
            required = false
    )
    protected int seqNumber;

    @ApiModelProperty(
            notes = "NbFrames can be 1 or 3. This value represents an expected " +
                    "number of frames sent by the device.",
            required = false
    )
    protected int nbFrames;

    @ApiModelProperty(
            notes = "Message computed location",
            required = false
    )
    protected SigfoxApiv2ComputedLocation computedLocation;

    @ApiModelProperty(
            notes = "Message Reception detailed information.",
            required = false
    )
    protected SigfoxApiv2MessageRInfo rinfos;

    @ApiModelProperty(
            notes = "Status of the downlink communication.",
            required = false
    )
    protected SigfoxApiv2MessageDownlinkAnswerStatus downlinkAnswerStatus;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public SigfoxApiv2DeviceMinimal getDevice() {
        return device;
    }

    public void setDevice(SigfoxApiv2DeviceMinimal device) {
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

    public boolean isAckRequired() {
        return ackRequired;
    }

    public void setAckRequired(boolean ackRequired) {
        this.ackRequired = ackRequired;
    }

    public int getLinkQuality() {
        return linkQuality;
    }

    public void setLinkQuality(int linkQuality) {
        this.linkQuality = linkQuality;
    }

    public int getLinkQualityRepeaters() {
        return linkQualityRepeaters;
    }

    public void setLinkQualityRepeaters(int linkQualityRepeaters) {
        this.linkQualityRepeaters = linkQualityRepeaters;
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

    public SigfoxApiv2ComputedLocation getComputedLocation() {
        return computedLocation;
    }

    public void setComputedLocation(SigfoxApiv2ComputedLocation computedLocation) {
        this.computedLocation = computedLocation;
    }

    public SigfoxApiv2MessageRInfo getRinfos() {
        return rinfos;
    }

    public void setRinfos(SigfoxApiv2MessageRInfo rinfos) {
        this.rinfos = rinfos;
    }

    public SigfoxApiv2MessageDownlinkAnswerStatus getDownlinkAnswerStatus() {
        return downlinkAnswerStatus;
    }

    public void setDownlinkAnswerStatus(SigfoxApiv2MessageDownlinkAnswerStatus downlinkAnswerStatus) {
        this.downlinkAnswerStatus = downlinkAnswerStatus;
    }
}

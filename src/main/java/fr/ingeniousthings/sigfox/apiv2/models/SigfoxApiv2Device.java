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

@Api(tags = "Device", description = "Device entity")
public class SigfoxApiv2Device {

    @ApiModelProperty(
            notes = "The device’s identifier (hexadecimal format)",
            required = true
    )
    protected String id;

    @ApiModelProperty(
            notes = "The device’s name",
            required = true
    )
    protected String name;

    @ApiModelProperty(
            notes = "Defines a device type entity",
            required = false
    )
    protected SigfoxApiv2DeviceTypeMinimal deviceType;

    @ApiModelProperty(
            notes = "Defines a minimum contract info entity",
            required = false
    )
    protected SigfoxApiv2ContractInfoMin contract;

    @ApiModelProperty(
            notes = "Defines a group entity",
            required = false
    )
    protected SigfoxApiv2GroupMinimal group;

    @ApiModelProperty(
            notes = "Modem certificate",
            required = false
    )
    protected SigfoxApiv2Certificate modemCertificate;

    @ApiModelProperty(
            notes = "Product certificate",
            required = false
    )
    protected SigfoxApiv2Certificate productCertificate;

    @ApiModelProperty(
            notes = "Contains the position of the device",
            required = false
    )
    protected SigfoxApiv2LatLng location;

    @ApiModelProperty(
            notes = "Contains the estimated position of the device within a circle " +
                    "based on the GPS data or the Sigfox Geolocation service",
            required = false
    )
    protected SigfoxApiv2ComputedLocation lastComputedLocation;

    @ApiModelProperty(
            notes = "The device’s PAC (Porting Access Code)",
            required = true
    )
    protected String pac;

    @ApiModelProperty(
            notes = "The last device’s sequence number. " +
                    "Absent if the device has never communicated or if the SIGFOX message protocol is V0",
            required = false
    )
    protected int sequenceNumber;

    @ApiModelProperty(
            notes = "The last trashed device’s sequence number." +
                    "Absent if there is no message trashed or if the SIGFOX message protocol is V0",
            required = false
    )
    protected int trashSequenceNumber;

    @ApiModelProperty(
            notes = "The last time (in milliseconds since the Unix Epoch) the device has communicated",
            required = false
    )
    protected long lastCom;

    @ApiModelProperty(
            notes = "The average device’s SNR (Signal Noise Ratio) in dB. " +
                    "This average is done from the last 25 received messages.",
            required = false
    )
    protected double averageSnr;

    @ApiModelProperty(
            notes = "The average device’s RSSI (Received Signal Strength Indicator) in dBm. " +
                    "This average is done from the last 25 received messages.",
            required = false
    )
    protected double averageRssi;


    @ApiModelProperty(
            notes = "The device’s activation time (in milliseconds since the Unix Epoch)",
            required = false
    )
    protected long activationTime;

    @ApiModelProperty(
            notes = "The device’s provisionning time (in milliseconds since the Unix Epoch)",
            required = true
    )
    protected long creationTime;

    @ApiModelProperty(
            notes = "State of this device.<br/>" +
                    "<ul>" +
                    "<li>0 -> OK</li>" +
                    "<li>1 -> DEAD</li>" +
                    "<li>2 -> OFF_CONTRACT</li>" +
                    "<li>3 -> DISABLED</li>" +
                    "<li>4 -> WARN</li>" +
                    "<li>5 -> DELETED</li>" +
                    "<li>6 -> SUSPENDED</li>" +
                    "<li>7 -> NOT_ACTIVABLE</li>" +
                    "</ul>",
            required = true
    )
    protected int state;

    @ApiModelProperty(
            notes = "Communication state of this device.<br/>" +
                    "<ul>" +
                    "<li>0 -> NO</li>" +
                    "<li>1 -> OK</li>" +
                    "<li>2 -> WARN</li>" +
                    "<li>3 -> KO</li>" +
                    "<li>4 -> (na)</li>" +
                    "<li>5 -> NOT_SEEN</li>" +
                    "</ul>",
            required = true
    )
    protected int comState;

    @ApiModelProperty(
            notes = "The token information of the device",
            required = false
    )
    protected SigfoxApiv2Token token;

    @ApiModelProperty(
            notes = "The device’s unsubscription time (in milliseconds since the Unix Epoch)",
            required = false
    )
    protected long unsubscriptionTime;


    @ApiModelProperty(
            notes = "The id of device’s creator user",
            required = false
    )
    protected String createdBy;

    @ApiModelProperty(
            notes = "Date of the last modification of this device (in milliseconds since the Unix Epoch)",
            required = false
    )
    protected long lastEditionTime;

    @ApiModelProperty(
            notes = "The id of device’s last editor user",
            required = false
    )
    protected String lastEditedBy;

    @ApiModelProperty(
            notes = "Allow token renewal ?",
            required = true
    )
    protected boolean automaticRenewal;

    @ApiModelProperty(
            notes = "Computed automatic renewal status.<br/>" +
                    "<ul>" +
                    "<li>0 -> ALLOWED</li>" +
                    "<li>1 -> NOT_ALLOWED</li>" +
                    "<li>2 -> RENEWED</li>" +
                    "<li>3 -> ENDED</li>" +
                    "</ul>",
            required = false
    )
    protected int automaticRenewalStatus;

    @ApiModelProperty(
            notes = "True if the device is activable and can take a token",
            required = false
    )
    protected boolean activable;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public boolean isActivable() {
        return activable;
    }

    public void setActivable(boolean activable) {
        this.activable = activable;
    }

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

    public SigfoxApiv2DeviceTypeMinimal getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(SigfoxApiv2DeviceTypeMinimal deviceType) {
        this.deviceType = deviceType;
    }

    public SigfoxApiv2ContractInfoMin getContract() {
        return contract;
    }

    public void setContract(SigfoxApiv2ContractInfoMin contract) {
        this.contract = contract;
    }

    public SigfoxApiv2GroupMinimal getGroup() {
        return group;
    }

    public void setGroup(SigfoxApiv2GroupMinimal group) {
        this.group = group;
    }

    public SigfoxApiv2Certificate getModemCertificate() {
        return modemCertificate;
    }

    public void setModemCertificate(SigfoxApiv2Certificate modemCertificate) {
        this.modemCertificate = modemCertificate;
    }

    public SigfoxApiv2Certificate getProductCertificate() {
        return productCertificate;
    }

    public void setProductCertificate(SigfoxApiv2Certificate productCertificate) {
        this.productCertificate = productCertificate;
    }

    public SigfoxApiv2LatLng getLocation() {
        return location;
    }

    public void setLocation(SigfoxApiv2LatLng location) {
        this.location = location;
    }

    public SigfoxApiv2ComputedLocation getLastComputedLocation() {
        return lastComputedLocation;
    }

    public void setLastComputedLocation(SigfoxApiv2ComputedLocation lastComputedLocation) {
        this.lastComputedLocation = lastComputedLocation;
    }

    public String getPac() {
        return pac;
    }

    public void setPac(String pac) {
        this.pac = pac;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getTrashSequenceNumber() {
        return trashSequenceNumber;
    }

    public void setTrashSequenceNumber(int trashSequenceNumber) {
        this.trashSequenceNumber = trashSequenceNumber;
    }

    public long getLastCom() {
        return lastCom;
    }

    public void setLastCom(long lastCom) {
        this.lastCom = lastCom;
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

    public long getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(long activationTime) {
        this.activationTime = activationTime;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getComState() {
        return comState;
    }

    public void setComState(int comState) {
        this.comState = comState;
    }

    public SigfoxApiv2Token getToken() {
        return token;
    }

    public void setToken(SigfoxApiv2Token token) {
        this.token = token;
    }

    public long getUnsubscriptionTime() {
        return unsubscriptionTime;
    }

    public void setUnsubscriptionTime(long unsubscriptionTime) {
        this.unsubscriptionTime = unsubscriptionTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getLastEditionTime() {
        return lastEditionTime;
    }

    public void setLastEditionTime(long lastEditionTime) {
        this.lastEditionTime = lastEditionTime;
    }

    public String getLastEditedBy() {
        return lastEditedBy;
    }

    public void setLastEditedBy(String lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    public boolean isAutomaticRenewal() {
        return automaticRenewal;
    }

    public void setAutomaticRenewal(boolean automaticRenewal) {
        this.automaticRenewal = automaticRenewal;
    }

    public int getAutomaticRenewalStatus() {
        return automaticRenewalStatus;
    }

    public void setAutomaticRenewalStatus(int automaticRenewalStatus) {
        this.automaticRenewalStatus = automaticRenewalStatus;
    }
}

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

import java.util.List;

@Api(tags = "commonContractInfo", description = "Defines a contract info’s common properties")
public class SigfoxApiv2ContractInfoCommon {


    @ApiModelProperty(
            notes = "The contract info name",
            example = "03662_SUBS PLATINUM",
            required = true
    )
    protected String name;

    @ApiModelProperty(
            notes = "The activation end time (in milliseconds) of the contract info.",
            example = "1551264713282",
            required = true
    )
    protected long activationEndTime;

    @ApiModelProperty(
            notes = "The end time (in milliseconds) of the communication.",
            example = "1582801013282",
            required = true
    )
    protected long communicationEndTime;

    @ApiModelProperty(
            notes = "True if the contract info is bidirectional.",
            example = "true",
            required = false
    )
    protected boolean bidir;

    @ApiModelProperty(
            notes = "True if all downlinks are high priority.",
            example = "false",
            required = false
    )
    protected boolean highPriorityDownlink;

    @ApiModelProperty(
            notes = "The maximum number of uplink frames. Must be >=0",
            example = "140",
            required = true
    )
    protected int maxUplinkFrames;

    @ApiModelProperty(
            notes = "The maximum number of downlink frames. Must be >=0",
            example = "4",
            required = true
    )
    protected int maxDownlinkFrames;

    @ApiModelProperty(
            notes = "The maximum number of tokens for this contract info. Either 0 (unlimited) or a positive number.",
            example = "10",
            required = false
    )
    protected int maxTokens;

    @ApiModelProperty(
            notes = "The number of test messages. Must be >= 0 and <= 25.",
            example = "0",
            required = true
    )
    protected int testMessages;

    @ApiModelProperty(
            notes = "The test message duration in months. Must be >= 0.",
            example = "0",
            required = true
    )
    protected int testMessagesDuration;

    @ApiModelProperty(
            notes = "The geolocation mode. To be defined, for instance:" +
                    "<ul>" +
                    "<li>0 -> no geoloc</li>" +
                    "<li>1 -> high service level (1km precision)</li>" +
                    "<li>2 -> low service level (10km precision)</li>" +
                    "</uL>",
            example = "1",
            required = false
    )
    protected int geolocationMode;

    @ApiModelProperty(
            notes = "The maximum number of renewals.",
            example = "0",
            required = false
    )
    protected int renewalLimit;

    @ApiModelProperty(
            notes = "True if automatic renewal is allowed.",
            example = "false",
            required = false
    )
    protected boolean automaticRenewal;

    @ApiModelProperty(
            notes = "The renewal duration in months. Must be >= 0",
            example = "0",
            required = false
    )
    protected int renewalDuration;

    @ApiModelProperty(
            notes = "True to allow new territories.",
            example = "false",
            required = false
    )
    protected boolean allowNewTerritories;


    @Api(tags = "commonContractInfo.OptionParameter", description = "The parameters of the premium option")
    public static class OptionParameter {
        @ApiModelProperty(
                notes = "payloadEncryption:<br/>" +
                        "level: 0 (DEVICE_TO_SIGFOX_CLOUD, default), 1 (END_TO_END)<br/>" +
                        "geolocation:</br>" +
                        "level: 1 (ATLAS, default), 2 (ATLAS_WIFI)<br/>" +
                        "cognition:\n" +
                        "level: 0 (MONARCH, default)",
                example = "1",
                required = false
        )
        public int level;

        @ApiModelProperty(
                notes = "testFrames:<br/>" +
                        "nb: 1 - 25 (default=1)",
                example = "1",
                required = false
        )
        public int nb;

        @ApiModelProperty(
                notes = "testFrames:<br/>" +
                        "duration: 0+ (default=0)",
                example = "0",
                required = false
        )
        public int duration;

    }


    @Api(tags = "commonContractInfo.Option", description = "The activated premium options")
    public static class Option {
        @ApiModelProperty(
                notes = "The premium option id (payloadEncryption, geolocation, cognition, testFrames, …)",
                example = "geolocation",
                required = false
        )
        public String id;

        @ApiModelProperty(
                notes = "The parameters of the premium option",
                required = false
        )
        public OptionParameter parameters;

    }

    @ApiModelProperty(
            notes = "The activated premium options.",
            required = false
    )
    protected List<Option> options;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getActivationEndTime() {
        return activationEndTime;
    }

    public void setActivationEndTime(long activationEndTime) {
        this.activationEndTime = activationEndTime;
    }

    public long getCommunicationEndTime() {
        return communicationEndTime;
    }

    public void setCommunicationEndTime(long communicationEndTime) {
        this.communicationEndTime = communicationEndTime;
    }

    public boolean isBidir() {
        return bidir;
    }

    public void setBidir(boolean bidir) {
        this.bidir = bidir;
    }

    public boolean isHighPriorityDownlink() {
        return highPriorityDownlink;
    }

    public void setHighPriorityDownlink(boolean highPriorityDownlink) {
        this.highPriorityDownlink = highPriorityDownlink;
    }

    public int getMaxUplinkFrames() {
        return maxUplinkFrames;
    }

    public void setMaxUplinkFrames(int maxUplinkFrames) {
        this.maxUplinkFrames = maxUplinkFrames;
    }

    public int getMaxDownlinkFrames() {
        return maxDownlinkFrames;
    }

    public void setMaxDownlinkFrames(int maxDownlinkFrames) {
        this.maxDownlinkFrames = maxDownlinkFrames;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public int getTestMessages() {
        return testMessages;
    }

    public void setTestMessages(int testMessages) {
        this.testMessages = testMessages;
    }

    public int getTestMessagesDuration() {
        return testMessagesDuration;
    }

    public void setTestMessagesDuration(int testMessagesDuration) {
        this.testMessagesDuration = testMessagesDuration;
    }

    public int getGeolocationMode() {
        return geolocationMode;
    }

    public void setGeolocationMode(int geolocationMode) {
        this.geolocationMode = geolocationMode;
    }

    public int getRenewalLimit() {
        return renewalLimit;
    }

    public void setRenewalLimit(int renewalLimit) {
        this.renewalLimit = renewalLimit;
    }

    public boolean isAutomaticRenewal() {
        return automaticRenewal;
    }

    public void setAutomaticRenewal(boolean automaticRenewal) {
        this.automaticRenewal = automaticRenewal;
    }

    public int getRenewalDuration() {
        return renewalDuration;
    }

    public void setRenewalDuration(int renewalDuration) {
        this.renewalDuration = renewalDuration;
    }

    public boolean isAllowNewTerritories() {
        return allowNewTerritories;
    }

    public void setAllowNewTerritories(boolean allowNewTerritories) {
        this.allowNewTerritories = allowNewTerritories;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}

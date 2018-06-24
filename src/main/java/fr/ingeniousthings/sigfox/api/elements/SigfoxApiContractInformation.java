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
 * Object retruned by sigfox API corresponding to a contract.
 *
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  2018-06-24 (TokensInUse replace TokensUsed)
 * {
 *      "id":"529c50f6e4b0f3ec6b3bc4d9",
 *      "groupId":"5409cb539336c0db1b720da6",
 *      "name":"BSS Order sample",
 *      "startTime":1385852400000,
 *      "endTime":1417388400000,
 *      "communicationEndTime":1417388400000,
 *      "timezone":"Europe/Paris",
 *      "tokenDuration":12,
 *      "activationPeriodDuration":0,
 *      "maxTokens":100000,
 *      "maxTokensInUse":50000,
 *      "tokensInUse":6659,
 *      "state":1,
 *      "type":2,
 *      "bidir":true,
 *      "maximumNumberOfDownlinkFrames":4
 * }
 *
 *  2017-08-01
 *  {
 *      id: "contract_id_in_hexa_string",
 *      name: "GIVEN_CONTRACT_NAME",
 *      startTime: 1476223200000,
 *      endTime: 1507759200000,
 *      communicationEndTime: 1539295200000,
 *      timezone: "Europe/Paris",
 *      tokenDuration: 12,                          // in month
 *      activationPeriodDuration: 12,               // in month
 *      maxTokensInUse: 0,                          // supposed to be activ token
 *      bidir: true,
 *      maximumNumberOfDownlinkFrames: 4,
 *      state: 1,                                   // 0 : initial / 1 : valid / 2 : terminate
 *      tokensUsed: 1,
 *      maxTokens: 100,
 *      tokensInUse: 31,
 *      groupId: "group_id_associated_with_this_contract",
 *      type: 2                                     // 0 : free / 1 : integrator / 2 : standard
 *  }
 *
 * @author Paul Pinault
 */
public class SigfoxApiContractInformation {

    @ApiModelProperty(
            notes = "The BSS order’s identifier",
            required = true
    )
    protected String id;

    @ApiModelProperty(
            notes = "The associated Group id",
            required = true
    )
    protected String groupId;

    @ApiModelProperty(
            notes = "The BSS order’s name",
            required = true
    )
    protected String name;

    @ApiModelProperty(
            notes = "the BSS order’s start date",
            required = true
    )
    protected long startTime;

    @ApiModelProperty(
            notes = "The BSS order’s end date",
            required = true
    )
    protected long endTime;

    @ApiModelProperty(
            notes = "The BSS order’s communication end date",
            required = true
    )
    protected long communicationEndTime;

    @ApiModelProperty(
            notes = "The time zone used by the BSS order",
            required = true
    )
    protected String timezone;

    @ApiModelProperty(
            notes = "The BSS order’s duration (month)",
            required = true
    )
    protected int tokenDuration;

    @ApiModelProperty(
            notes = "The BSS order’s activation period (month)",
            required = true
    )
    protected int activationPeriodDuration;

    @ApiModelProperty(
            notes = "The total number of tokens",
            required = true
    )
    protected int maxTokens;

    @ApiModelProperty(
            notes = "The maximum number of simultaneously usable tokens",
            required = true
    )
    protected int maxTokensInUse;

    @ApiModelProperty(
            notes = "The number of tokens currently used",
            required = true
    )
    protected int tokensInUse;

    @ApiModelProperty(
            notes = "The BSS order’s state (0: initial, 1: validate, 2: terminate)",
            required = true
    )
    protected int state;

    @ApiModelProperty(
            notes = "The BSS order’s type (0: Free, 1: Integrator, 2: Standard)",
            required = true
    )
    protected int type;


    @ApiModelProperty(
            notes = "True if the BSS order allows bidirectional messages",
            required = true
    )
    protected boolean bidir;

    @ApiModelProperty(
            notes = "Number of downlink frames",
            required = true
    )
    protected int maximumNumberOfDownlinkFrames;


    // ----------------------------------------------------------
    // Generated Getters & Setters
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getCommunicationEndTime() {
        return communicationEndTime;
    }

    public void setCommunicationEndTime(long communicationEndTime) {
        this.communicationEndTime = communicationEndTime;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getTokenDuration() {
        return tokenDuration;
    }

    public void setTokenDuration(int tokenDuration) {
        this.tokenDuration = tokenDuration;
    }

    public int getActivationPeriodDuration() {
        return activationPeriodDuration;
    }

    public void setActivationPeriodDuration(int activationPeriodDuration) {
        this.activationPeriodDuration = activationPeriodDuration;
    }

    public int getMaxTokensInUse() {
        return maxTokensInUse;
    }

    public void setMaxTokensInUse(int maxTokensInUse) {
        this.maxTokensInUse = maxTokensInUse;
    }

    public boolean isBidir() {
        return bidir;
    }

    public void setBidir(boolean bidir) {
        this.bidir = bidir;
    }

    public int getMaximumNumberOfDownlinkFrames() {
        return maximumNumberOfDownlinkFrames;
    }

    public void setMaximumNumberOfDownlinkFrames(int maximumNumberOfDownlinkFrames) {
        this.maximumNumberOfDownlinkFrames = maximumNumberOfDownlinkFrames;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTokensUsed() {
        return tokensInUse;
    }

    public void setTokensUsed(int tokensUsed) {
        this.tokensInUse = tokensUsed;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public int getTokensInUse() {
        return tokensInUse;
    }

    public void setTokensInUse(int tokensInUse) {
        this.tokensInUse = tokensInUse;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    // --------------------------------------------------
    // Serialization


    @Override
    public String toString() {
        return "SigfoxApiContractInformation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", communicationEndTime=" + communicationEndTime +
                ", timezone='" + timezone + '\'' +
                ", tokenDuration=" + tokenDuration +
                ", activationPeriodDuration=" + activationPeriodDuration +
                ", maxTokensInUse=" + maxTokensInUse +
                ", bidir=" + bidir +
                ", maximumNumberOfDownlinkFrames=" + maximumNumberOfDownlinkFrames +
                ", state=" + state +
                ", tokensUsed=" + tokensInUse +
                ", maxTokens=" + maxTokens +
                ", tokensInUse=" + tokensInUse +
                ", groupId='" + groupId + '\'' +
                ", type=" + type +
                '}';
    }
}
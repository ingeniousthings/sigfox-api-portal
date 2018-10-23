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

@Api(tags = "downlinkAnswerStatus", description = "The last callback status for this reception")
public class SigfoxApiv2MessageDownlinkAnswerStatus {

    @ApiModelProperty(
            notes = "Base station to send downlink message",
            required = false
    )
    protected SigfoxApiv2BaseStationMinimal baseStation;

    @ApiModelProperty(
            notes = "Planned downlink power as it was computed by the backend",
            example = "67.89",
            required = false
    )
    protected double plannedPower;

    @ApiModelProperty(
            notes = "Response content, hex encoded",
            example = "0000437f00000050",
            required = false
    )
    protected String data;

    @ApiModelProperty(
            notes = "Name of the first operator which received the message as roaming",
            example = "Operator#1",
            required = false
    )
    protected String operator;

    @ApiModelProperty(
            notes = "Country of the operator",
            example = "FRANCE",
            required = false
    )
    protected String country;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public SigfoxApiv2BaseStationMinimal getBaseStation() {
        return baseStation;
    }

    public void setBaseStation(SigfoxApiv2BaseStationMinimal baseStation) {
        this.baseStation = baseStation;
    }

    public double getPlannedPower() {
        return plannedPower;
    }

    public void setPlannedPower(double plannedPower) {
        this.plannedPower = plannedPower;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

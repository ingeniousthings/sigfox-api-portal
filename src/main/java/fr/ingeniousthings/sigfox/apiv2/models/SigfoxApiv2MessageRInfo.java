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

@Api(tags = "rInfo", description = "Data Message Reception information")
public class SigfoxApiv2MessageRInfo {

    @ApiModelProperty(
            notes = "Base station to send downlink message",
            required = false
    )
    protected SigfoxApiv2BaseStationMinimal baseStation;

    @ApiModelProperty(
            notes = "The device’s estimated latitude",
            required = false
    )
    protected double lat;

    @ApiModelProperty(
            notes = "The device’s estimated longitude",
            required = false
    )
    protected double lng;

    @ApiModelProperty(
            notes = "Number of repetitions sent by the base station",
            required = false
    )
    protected int rep;

    @ApiModelProperty(
            notes = "List of callback status for this reception",
            required = false
    )
    protected List<SigfoxApiv2CallbackExecutionStatus> cbStatus;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public SigfoxApiv2BaseStationMinimal getBaseStation() {
        return baseStation;
    }

    public void setBaseStation(SigfoxApiv2BaseStationMinimal baseStation) {
        this.baseStation = baseStation;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public List<SigfoxApiv2CallbackExecutionStatus> getCbStatus() {
        return cbStatus;
    }

    public void setCbStatus(List<SigfoxApiv2CallbackExecutionStatus> cbStatus) {
        this.cbStatus = cbStatus;
    }
}

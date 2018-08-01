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

@Api(tags = "baseStationCommandBulk", description = "A command to submit to a set of base station")
public class SigfoxApiv2BaseStationCommandBulk {

    @ApiModelProperty(
            notes = "The list of identifier of the base stations (hexadecimal)",
            required = true
    )
    protected List<String> baseStationIds;

    @ApiModelProperty(
            notes = "The delay (in ms) to apply between sets of stations that are close together.<br/>" +
                    "Note that for the OSUPD command, this attribute is ignored and the system configuration applies.",
            required = false
    )
    protected int delayBetweenZones;

    @ApiModelProperty(
            notes = "The delay (in ms) to apply between batches.<br/>" +
                    "Note that for the OSUPD command, this attribute is ignored and the system configuration applies.",
            required = false
    )
    protected int delayBetweenBatches;

    @ApiModelProperty(
            notes = "The number of base stations to simultaneously send the command to.<br/>" +
                    "Note that for the OSUPD command, this attribute is ignored and the system configuration applies.",
            required = false
    )
    protected int batchSize;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public List<String> getBaseStationIds() {
        return baseStationIds;
    }

    public void setBaseStationIds(List<String> baseStationIds) {
        this.baseStationIds = baseStationIds;
    }

    public int getDelayBetweenZones() {
        return delayBetweenZones;
    }

    public void setDelayBetweenZones(int delayBetweenZones) {
        this.delayBetweenZones = delayBetweenZones;
    }

    public int getDelayBetweenBatches() {
        return delayBetweenBatches;
    }

    public void setDelayBetweenBatches(int delayBetweenBatches) {
        this.delayBetweenBatches = delayBetweenBatches;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }
}

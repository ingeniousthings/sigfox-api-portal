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

@Api(tags = "consumption", description = "Consumption report detail")
public class SigfoxApiv2Consumption {

    @ApiModelProperty(
            notes = "Number of uplink messages this day.",
            example = "19",
            required = false
    )
    protected int frameCount;

    @ApiModelProperty(
            notes = "Number of downlink messages this day.",
            example = "4",
            required = false
    )
    protected int downlinkFrameCount;

    @ApiModelProperty(
            notes = "Number of uplink roaming messages this day.",
            example = "3",
            required = false
    )
    protected int roamingFrameCount;

    @ApiModelProperty(
            notes = "Number of downlink roaming messages this day.",
            example = "1",
            required = false
    )
    protected int roamingDownlinkFrameCount;

    @Api(tags = "roamingDetails", description = "Roaming Consumption report detail")
    public class RoamingDetails {

        @ApiModelProperty(
                notes = "Country of the Operator (3 letters).",
                example = "FRA",
                required = false
        )
        protected String territory;

        @ApiModelProperty(
                notes = "Name of the operator.",
                example = "SIGFOX_France",
                required = false
        )
        protected String operator;

        @ApiModelProperty(
                notes = "Number of uplink roaming messages this day for this operator.",
                example = "3",
                required = false
        )
        protected int territoryRoamingFrameCount;

        @ApiModelProperty(
                notes = "Number of downlink roaming messages this day for this operator.",
                example = "1",
                required = false
        )
        protected int territoryRoamingDownlinkFrameCount;

        // =======================


        public String getTerritory() {
            return territory;
        }

        public void setTerritory(String territory) {
            this.territory = territory;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public int getTerritoryRoamingFrameCount() {
            return territoryRoamingFrameCount;
        }

        public void setTerritoryRoamingFrameCount(int territoryRoamingFrameCount) {
            this.territoryRoamingFrameCount = territoryRoamingFrameCount;
        }

        public int getTerritoryRoamingDownlinkFrameCount() {
            return territoryRoamingDownlinkFrameCount;
        }

        public void setTerritoryRoamingDownlinkFrameCount(int territoryRoamingDownlinkFrameCount) {
            this.territoryRoamingDownlinkFrameCount = territoryRoamingDownlinkFrameCount;
        }
    }

    @ApiModelProperty(
            notes = "Roaming details for each of the operator.",
            required = false
    )
    protected List<RoamingDetails> roamingDetails;



    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public int getDownlinkFrameCount() {
        return downlinkFrameCount;
    }

    public void setDownlinkFrameCount(int downlinkFrameCount) {
        this.downlinkFrameCount = downlinkFrameCount;
    }

    public int getRoamingFrameCount() {
        return roamingFrameCount;
    }

    public void setRoamingFrameCount(int roamingFrameCount) {
        this.roamingFrameCount = roamingFrameCount;
    }

    public int getRoamingDownlinkFrameCount() {
        return roamingDownlinkFrameCount;
    }

    public void setRoamingDownlinkFrameCount(int roamingDownlinkFrameCount) {
        this.roamingDownlinkFrameCount = roamingDownlinkFrameCount;
    }

    public List<RoamingDetails> getRoamingDetails() {
        return roamingDetails;
    }

    public void setRoamingDetails(List<RoamingDetails> roamingDetails) {
        this.roamingDetails = roamingDetails;
    }
}

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
package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

public class SigfoxApiDeviceYearlyConsumption {

    public class RoamingDetails {

        @ApiModelProperty(
                notes = "Country of the operator (3 letters)",
                required = true
        )
        public String  territory;

        @ApiModelProperty(
                notes = "Name of the operator",
                required = true
        )
        public String  operator;

        @ApiModelProperty(
                notes = "Number of uplink messages roaming by that operator",
                required = true
        )
        public int territoryRoamingFrameCount;

        @ApiModelProperty(
                notes = "Number of downlink messages roaming by that operator",
                required = true
        )
        public int territoryRoamingDownlinkFrameCount;

    }

    public class ConsumptionDetails {

        @ApiModelProperty(
                notes = "Number of uplink messages received",
                required = true
        )
        public int  frameCount;

        @ApiModelProperty(
                notes = "Number of downlink messages received",
                required = true
        )
        public int  downlinkFrameCount;

        @ApiModelProperty(
                notes = "Number of uplink roaming messages received",
                required = true
        )
        public int  roamingFrameCount;

        @ApiModelProperty(
                notes = "Number of downlink roaming messages received",
                required = true
        )
        public int  roamingDownlinkFrameCount;

        @ApiModelProperty(
                notes = "Number of downlink roaming messages received",
                required = true
        )
        public RoamingDetails [] roamingDetails;

    }

    public class Consumption {

        @ApiModelProperty(
                notes = "The consumption’s identifier (format : {device-id}_{year})",
                required = true
        )
        public String id;


        @ApiModelProperty(
                notes = "Array of consumption per day (index in this array is number of the day in year, eg : 9 = 10th january)",
                required = true
        )
        public ConsumptionDetails [] consumptions;


    }


    @ApiModelProperty(
            notes = "The Device’s consumptions for a year.",
            required = true
    )
    protected Consumption consumption;


    public Consumption getConsumption() {
        return consumption;
    }

    public void setConsumption(Consumption consumption) {
        this.consumption = consumption;
    }
}

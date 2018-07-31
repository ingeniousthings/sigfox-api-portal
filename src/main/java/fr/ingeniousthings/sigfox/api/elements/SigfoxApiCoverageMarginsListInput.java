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

/**
 * Summary
 *
 *
 * @author Paul Pinault
 */
public class SigfoxApiCoverageMarginsListInput {

    public class Location {
        @ApiModelProperty(
                notes = "A latitude in degrees. Must be between -90° and 90°.",
                required = true
        )
        public double lat;

        @ApiModelProperty(
                notes = "A longitude in degrees. Must be between -180° and 180°.",
                required = true
        )
        public double lng;
    }


    @ApiModelProperty(
            notes = "An array containing the response for each point.",
            required = true
    )
    protected Location locations;


    @ApiModelProperty(
            notes = "(optional) the distance around the lat/lng locations in meters, default is 300.",
            required = true
    )
    protected int radius;

    // ====================================================
    // Getters & Setters
    // ====================================================


    public Location getLocations() {
        return locations;
    }

    public void setLocations(Location locations) {
        this.locations = locations;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
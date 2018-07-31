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

import java.lang.Override;
import java.lang.String;
import java.util.Arrays;

/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a coverage margins.
 * ----------------------------------------------------------------------------------
 * Response Format :
 * {
 *     "locationCovered": true,
 *     "margins":[
 *         42.12,
 *         27.49,
 *         5.87
 *     ]
 * }
 *
 *
 * @author Paul Pinault
 */
public class SigfoxApiCoverageMargins {

    @ApiModelProperty(
            notes = "The latitude in degrees. [only filled by Multiple point API]",
            required = false
    )
    protected double lat;

    @ApiModelProperty(
            notes = "The longitude in degrees. [only filled by Multiple point API]",
            required = false
    )
    protected double lng;


    @ApiModelProperty(
            notes = "True, if the requested location is considered covered.",
            required = false            // non sense but according to doc it could be
    )
    protected boolean locationCovered;

    @ApiModelProperty(
            notes = "The margins values (dB) for redundancy level 1, 2 and 3",
            required = true
    )
    protected double [] margins;


    // ====================================================
    // Getters & Setters
    // ====================================================

    public double[] getMargins() {
        return margins;
    }

    public void setMargins(double[] margins) {
        this.margins = margins;
    }

    public boolean isLocationCovered() {
        return locationCovered;
    }

    public void setLocationCovered(boolean locationCovered) {
        this.locationCovered = locationCovered;
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

    @Override
    public String toString() {
        return "SigfoxApiCoverageMargins{" +
                "margins=" + Arrays.toString(margins) +
                '}';
    }
}
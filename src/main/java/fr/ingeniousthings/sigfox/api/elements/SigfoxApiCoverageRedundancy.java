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

/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a coverage redundancy. (number of antennas covereing the area)
 * ----------------------------------------------------------------------------------
 * Response Format :
 * {
 * "redundancy":3
 * }
 *
 * @author Paul Pinault
 */
public class SigfoxApiCoverageRedundancy {

    @ApiModelProperty(
            notes = "The base station redundancy : 0 = none, 1 = 1 base station, 2 = 2 base stations, 3 = 3 base stations or more",
            required = true
    )
    private int redundancy = 0;


    public int getRedundancy() {
        return redundancy;
    }

    public void setRedundancy(int redundancy) {
        this.redundancy = redundancy;
    }

    @Override
    public String toString() {
        return "SigfoxApiCoverageRedundancy{" +
                "redundancy=" + redundancy +
                '}';
    }
}
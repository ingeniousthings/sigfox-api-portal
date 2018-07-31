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

import java.util.Arrays;

/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a coverage margins.
 * ----------------------------------------------------------------------------------
 * Response Format :
 * {
 *     "data": [
 *         {
 *             "lat": 43.5,
 *             "lng": 1.5,
 *             "margins": [
 *                 42,
 *                 35,
 *                 33
 *             ]
 *         },
 *         {
 *             "lat": 43.4,
 *             "lng": 1.5,
 *             "locationCovered": true,
 *             "margins": [
 *                 30,
 *                 27,
 *                 24
 *             ]
 *         }
 *     ]
 * }
 *
 *
 * @author Paul Pinault
 */
public class SigfoxApiCoverageMarginsList {

    @ApiModelProperty(
            notes = "An array containing the response for each point.",
            required = true
    )
    protected SigfoxApiCoverageMargins data;


    // ====================================================
    // Getters & Setters
    // ====================================================


    public SigfoxApiCoverageMargins getData() {
        return data;
    }

    public void setData(SigfoxApiCoverageMargins data) {
        this.data = data;
    }
}
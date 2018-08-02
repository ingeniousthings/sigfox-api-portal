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

@Api(tags = "globalCoverageResponse", description = "Returned data for global Coverage API")
public class SigfoxApiv2CoverageGlobalResponse {

    @Api(tags = "globalCoverageResponseElement", description = "Unitary coverage response element")
    public class ResponseElement {

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
                notes = "True, if the requested location is considered covered.",
                required = false
        )
        protected boolean locationCovered;

        @ApiModelProperty(
                notes = "The margins values (dB) for redundancy level 1, 2 and 3.",
                required = false
        )
        protected List<Integer> margins;

        // ========================================


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

        public boolean isLocationCovered() {
            return locationCovered;
        }

        public void setLocationCovered(boolean locationCovered) {
            this.locationCovered = locationCovered;
        }

        public List<Integer> getMargins() {
            return margins;
        }

        public void setMargins(List<Integer> margins) {
            this.margins = margins;
        }
    }


    @ApiModelProperty(
            notes = "An array containing the response for each point.",
            required = false
    )
    protected List<ResponseElement> data;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public List<ResponseElement> getData() {
        return data;
    }

    public void setData(List<ResponseElement> data) {
        this.data = data;
    }
}

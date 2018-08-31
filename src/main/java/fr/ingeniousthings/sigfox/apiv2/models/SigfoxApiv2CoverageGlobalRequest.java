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

@Api(tags = "globalCoverageRequest", description = "Defines the request to post to get global coverage")
public class SigfoxApiv2CoverageGlobalRequest {

    @ApiModelProperty(
            notes = "An array of positions. Valid locations have two properties, lat and lng.",
            required = true
    )
    protected List<SigfoxApiv2LatLng> locations;

    @ApiModelProperty(
            notes = "The radius of the area in which the coverage results are averaged " +
                    "and returned for a selected location, in meters.",
            example = "100",
            required = false
    )
    protected int radius;

    @ApiModelProperty(
            notes = "The id of a group to include its operator in the global coverage, " +
                    "in case it is not a public operator.",
            example = "51fe2e399e93a326dd80861f",
            required = false
    )
    protected String groupId;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public List<SigfoxApiv2LatLng> getLocations() {
        return locations;
    }

    public void setLocations(List<SigfoxApiv2LatLng> locations) {
        this.locations = locations;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}

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

@Api(
        tags = "computedLocation",
        description = "Contains the estimated position of the device within a " +
        "circle based on the GPS data or the Sigfox Geolocation service"
)
public class SigfoxApiv2ComputedLocation {

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
            notes = "The radius of the precision circle (meters)",
            required = false
    )
    protected long radius;

    @ApiModelProperty(
            notes = "Define from which source the geolocation has been computed <br/>" +
                    "<ul>" +
                    "<li>0 -> Location computed by legacy mode using RSSI and position of station</li>" +
                    "<li>1 -> Location computed using the GPS data inside the payload</li>" +
                    "<li>2 -> Location computed by Atlas Network</li>" +
                    "<li>3 -> Location computed by Atlas POI</li>" +
                    "<li>4 -> Location computed by Atlas HD<li>" +
                    "</ul>",
            required = false
    )
    protected int source;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


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

    public long getRadius() {
        return radius;
    }

    public void setRadius(long radius) {
        this.radius = radius;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }
}

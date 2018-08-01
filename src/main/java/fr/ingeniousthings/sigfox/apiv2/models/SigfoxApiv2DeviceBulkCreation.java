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

@Api(tags = "deviceCreationBulk", description = "Device entity for bulk registration")
public class SigfoxApiv2DeviceBulkCreation {

    @ApiModelProperty(
            notes = "The device’s identifier (hexadecimal format)",
            required = true
    )
    protected String id;

    @ApiModelProperty(
            notes = "The device’s PAC (Porting Access Code)",
            required = true
    )
    protected String pac;

    @ApiModelProperty(
            notes = "The device’s name",
            required = false
    )
    protected String name;

    @ApiModelProperty(
            notes = "The device’s provided latitude",
            value = "0",
            required = false
    )
    protected double lat;

    @ApiModelProperty(
            notes = "The device’s provided longitude",
            value = "0",
            required = false
    )
    protected double lng;

    @ApiModelProperty(
            notes = "Allow token renewal ?",
            value = "true",
            required = false
    )
    protected boolean automaticRenewal;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPac() {
        return pac;
    }

    public void setPac(String pac) {
        this.pac = pac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isAutomaticRenewal() {
        return automaticRenewal;
    }

    public void setAutomaticRenewal(boolean automaticRenewal) {
        this.automaticRenewal = automaticRenewal;
    }
}

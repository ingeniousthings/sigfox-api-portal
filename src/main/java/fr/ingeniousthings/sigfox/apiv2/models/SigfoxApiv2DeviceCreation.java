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

@Api(tags = "deviceCreation", description = "Defines the device’s common properties for reading or creation (not update)")
public class SigfoxApiv2DeviceCreation {

    @ApiModelProperty(
            notes = "The device’s identifier (hexadecimal format)",
            required = true
    )
    protected String id;

    @ApiModelProperty(
            notes = "The device’s name",
            required = true
    )
    protected String name;

    @ApiModelProperty(
            notes = "Defines a device type entity",
            required = true
    )
    protected String deviceTypeId;

    @ApiModelProperty(
            notes = "The device’s PAC (Porting Access Code)",
            required = true
    )
    protected String pac;

    @ApiModelProperty(
            notes = "The device’s provided latitude",
            value = "0",
            required = true
    )
    protected double lat;

    @ApiModelProperty(
            notes = "The device’s provided longitude",
            value = "0",
            required = true
    )
    protected double lng;

    @ApiModelProperty(
            notes = "Product certificate",
            required = false
    )
    protected String productCertificate;

    @ApiModelProperty(
            notes = "If the device is a prototype or not",
            value = "false",
            required = false
    )
    protected boolean prototype;

    @ApiModelProperty(
            notes = "Allow token renewal ?",
            value = "true",
            required = false
    )
    protected boolean automaticRenewal;


    @ApiModelProperty(
            notes = "True if the device is activable and can take a token",
            value = "true",
            required = false
    )
    protected boolean activable;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getPac() {
        return pac;
    }

    public void setPac(String pac) {
        this.pac = pac;
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

    public String getProductCertificate() {
        return productCertificate;
    }

    public void setProductCertificate(String productCertificate) {
        this.productCertificate = productCertificate;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public void setPrototype(boolean prototype) {
        this.prototype = prototype;
    }

    public boolean isAutomaticRenewal() {
        return automaticRenewal;
    }

    public void setAutomaticRenewal(boolean automaticRenewal) {
        this.automaticRenewal = automaticRenewal;
    }

    public boolean isActivable() {
        return activable;
    }

    public void setActivable(boolean activable) {
        this.activable = activable;
    }
}

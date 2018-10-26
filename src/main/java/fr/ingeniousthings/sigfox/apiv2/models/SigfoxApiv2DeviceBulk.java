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

@Api(tags = "deviceBulk", description = "Bulk Device registration")
public class SigfoxApiv2DeviceBulk {

    @ApiModelProperty(
            notes = "The device type where new devices will be created",
            example = "5a5896f93007ab520cb54108",
            required = true
    )
    protected String deviceTypeId;

    @ApiModelProperty(
            notes = "The devices names prefix",
            example = "PREFIX_",
            required = false,
            value = ""
    )
    protected String prefix;

    @ApiModelProperty(
            notes = "The product’s certificate name if any (mandatory if not a prototype)",
            example = "P_0003_71CF_01",
            required = false
    )
    protected String productCertificate;

    @ApiModelProperty(
            notes = "If the devices are a prototype or not",
            value = "false",
            example = "false",
            required = false
    )
    protected boolean prototype;

    @ApiModelProperty(
            notes = "True if the devices are activable and can take a token",
            value = "true",
            example = "true",
            required = false
    )
    protected boolean activable;

    @ApiModelProperty(
            notes = "List of devices to be registered in Sigfox backend",
            required = true
    )
    protected List<SigfoxApiv2DeviceBulkCreation> devices;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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

    public boolean isActivable() {
        return activable;
    }

    public void setActivable(boolean activable) {
        this.activable = activable;
    }

    public List<SigfoxApiv2DeviceBulkCreation> getDevices() {
        return devices;
    }

    public void setDevices(List<SigfoxApiv2DeviceBulkCreation> devices) {
        this.devices = devices;
    }
}

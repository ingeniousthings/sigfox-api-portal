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

@Api(tags = "baseDeviceType", description = "Defines the device type’s basic properties")
public class SigfoxApiv2DeviceTypeBase {

    @ApiModelProperty(
            notes = "The device type’s name",
            required = false
    )
    protected String name;

    @ApiModelProperty(
            notes = "Keep alive period in seconds (0 to not keep alive else 1800 second minimum)",
            required = false
    )
    protected long keepAlive;

    @ApiModelProperty(
            notes = "Email address to contact in case of problems occurring while executing a callback",
            required = false
    )
    protected String alertEmail;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(long keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getAlertEmail() {
        return alertEmail;
    }

    public void setAlertEmail(String alertEmail) {
        this.alertEmail = alertEmail;
    }
}

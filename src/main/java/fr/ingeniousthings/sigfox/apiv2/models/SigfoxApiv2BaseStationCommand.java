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

@Api(tags = "baseStationCommand", description = "A command to submit to a base station")
public class SigfoxApiv2BaseStationCommand {

    @ApiModelProperty(
            notes = "The identifier of the command to send. </br>" +
                    "<ul>" +
                    "<li>CAPABILITIES</li>" +
                    "<li>GETCONF</li>" +
                    "<li>HWCFG</li>" +
                    "<li>HWVER</li>" +
                    "<li>OSUPD</li>" +
                    "<li>OSVER</li>" +
                    "<li>RESTART</li>" +
                    "<li>SETCONF</li>" +
                    "</ul>",
            required = true
    )
    protected String commandKey;

    @ApiModelProperty(
            notes = "Reason of the restart (required only when command is RESTART)",
            required = false
    )
    protected String reason;

    @ApiModelProperty(
            notes = "Version to update to (required only when command is OSUPD)",
            required = false
    )
    protected String version;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getCommandKey() {
        return commandKey;
    }

    public void setCommandKey(String commandKey) {
        this.commandKey = commandKey;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

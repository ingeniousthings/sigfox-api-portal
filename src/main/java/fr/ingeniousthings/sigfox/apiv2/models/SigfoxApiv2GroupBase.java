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

@Api(tags = "baseGroup", description = "Group information returned after and update")
public class SigfoxApiv2GroupBase {

    @ApiModelProperty(
            notes = "The group’s name",
            required = false
    )
    protected String name;

    @ApiModelProperty(
            notes = "The group’s description",
            required = false
    )
    protected String description;

    @ApiModelProperty(
            notes = "Group’s type." +
                    "<ul>" +
                    "<li>0 -> SO</li>" +
                    "<li>2 -> Basic</li>" +
                    "<li>5 -> SVNO</li>" +
                    "<li>6 -> Partners</li>" +
                    "<li>7 -> NIP</li>" +
                    "<li>8 -> DIST</li>" +
                    "<li>9 -> Channel</li>" +
                    "<li>10 -> Starter</li>" +
                    "<li>11 -> Partner</li>" +
                    "</ul>",
            required = false
    )
    protected int type;

    @ApiModelProperty(
            notes = "SNO or NIP group id for a DIST & SVNO group. " +
                    "This field is mandatory for DIST & SVNO group creation.",
            required = false
    )
    protected String networkOperatorId;

    @ApiModelProperty(
            notes = "True if the group is billable.",
            required = false
    )
    protected boolean billable;

    @ApiModelProperty(
            notes = "The technical contact email.",
            required = false
    )
    protected String technicalEmail;

    @ApiModelProperty(
            notes = "The timezone.",
            required = false
    )
    protected String timezone;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNetworkOperatorId() {
        return networkOperatorId;
    }

    public void setNetworkOperatorId(String networkOperatorId) {
        this.networkOperatorId = networkOperatorId;
    }

    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    public String getTechnicalEmail() {
        return technicalEmail;
    }

    public void setTechnicalEmail(String technicalEmail) {
        this.technicalEmail = technicalEmail;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}

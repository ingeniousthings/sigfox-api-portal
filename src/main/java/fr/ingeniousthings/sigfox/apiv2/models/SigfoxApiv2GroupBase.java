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
            example = "Group 1",
            required = false
    )
    protected String name;

    @ApiModelProperty(
            notes = "The group’s description",
            example = "Description of my group 1",
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
            example = "8",
            required = false
    )
    protected int type;

    @ApiModelProperty(
            notes = "SNO or NIP group id for a DIST & SVNO group. " +
                    "This field is mandatory for DIST & SVNO group creation.",
            example = "572f1204017975032d8ec1dd",
//            hidden = true,
            required = false
    )
    protected String networkOperatorId;

    @ApiModelProperty(
            notes = "True if the group is billable.",
            example = "true",
            required = false
    )
    protected boolean billable;

    @ApiModelProperty(
            notes = "The technical contact email.",
            example = "john.doe@foo.bar",
            required = false
    )
    protected String technicalEmail;

    @ApiModelProperty(
            notes = "The timezone.",
            example = "Europe/Paris",
            required = false
    )
    protected String timezone;

    @ApiModelProperty(
            notes = "This is the country ISO code " +
                    "(https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3#Officially_assigned_code_elements) " +
                    "where the operator manages its network. Only available for SNO and NIP.",
            example = "FRA",
//            hidden = true,
            required = false
    )
    protected String countryISOAlpha3;


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

    public String getCountryISOAlpha3() {
        return countryISOAlpha3;
    }

    public void setCountryISOAlpha3(String countryISOAlpha3) {
        this.countryISOAlpha3 = countryISOAlpha3;
    }
}

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

@Api(tags = "User", description = "User Details")
public class SigfoxApiv2User {

    @ApiModelProperty(
            notes = "The user’s first name",
            required = false
    )
    protected String firstName;

    @ApiModelProperty(
            notes = "The user’s last name",
            required = false
    )
    protected String lastName;

    @ApiModelProperty(
            notes = "The user’s timezone",
            required = false
    )
    protected String timezone;

    @ApiModelProperty(
            notes = "The user’s first name",
            required = false
    )
    protected List<SigfoxApiv2UserRole> userRoles;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public List<SigfoxApiv2UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<SigfoxApiv2UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}

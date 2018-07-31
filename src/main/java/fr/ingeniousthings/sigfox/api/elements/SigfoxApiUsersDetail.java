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
package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Format:
 *
 * {
 *  "firstName" : "Michel",
 *  "lastName" : "Dupont",
 *   "email" : "michel.dupont@sigfox.com",
 *   "timezone" : "Europe/Paris",
 *   "creationTime" : 1392812363644,
 *   "creationDate" : "Wed Feb 19 13:19:23 CET 2014",
 *   "lastLogin" : 1448351837467,
 *   "lastLoginDate" : "Tue Nov 24 08:57:17 CET 2015",
 *   "userRoles" : [ {
 *   "group" : {
 *   "id" : "babecafebabecafebabecafe",
 *   "name" : "Root",
 *   "nameCI" : "root",
 *   "description" : "Master Group",
 *   "path" : [ ],
 *   "billable" : false
 *   },
 *   "customRole" : {
 *   "id" : "51d19e7ce4b067e859e4c2c1",
 *   "name" : "SUPPORT_CORP"
 *   }
 *   } ]
 *   }
 *
 *
 *
 */


public class SigfoxApiUsersDetail {

    public class SigfoxApiUsersGroup {
        @ApiModelProperty(
                notes = "The group’s identifier",
                required = true
        )
        public String id;

        @ApiModelProperty(
                notes = "The group’s name",
                required = true
        )
        public String name;

        @ApiModelProperty(
                notes = "The group’s name case insensitive",
                required = true
        )
        public String nameCI;

        @ApiModelProperty(
                notes = "Description of this group",
                required = true
        )
        public String description;

        @ApiModelProperty(
                notes = "Array with the group’s parents’ identifier",
                required = true
        )
        public String [] path;

        @ApiModelProperty(
                notes = "True if the group is billable",
                required = true
        )
        public boolean billable;

    }

    public class SigfoxApiUsersCustomRole {

        @ApiModelProperty(
                notes = "The profile’s identifier",
                required = true
        )
        public String id;

        @ApiModelProperty(
                notes = "The profile’s name",
                required = true
        )
        public String name;

    }

    public class SigfoxApiUsersUserRoles {

        @ApiModelProperty(
                notes = "The group’s information",
                required = true
        )
        public SigfoxApiUsersGroup group;

        @ApiModelProperty(
                notes = "The profile’s information",
                required = true
        )
        public SigfoxApiUsersCustomRole customRole;

    }

    @ApiModelProperty(
            notes = "The user’s first name",
            required = true
    )
    protected String firstName;

    @ApiModelProperty(
            notes = "The user’s last name",
            required = true
    )
    protected String lastName;

    @ApiModelProperty(
            notes = "the user’s email",
            required = true
    )
    protected String email;

    @ApiModelProperty(
            notes = "The user’s timezone",
            required = true
    )
    protected String timezone;

    @ApiModelProperty(
            notes = "The user’s creation date in time",
            required = true
    )
    protected long creationTime;

    @ApiModelProperty(
            notes = "The user’s creation date",
            required = true
    )
    protected String creationDate;

    @ApiModelProperty(
            notes = "The user’s last connection date in time",
            required = true
    )
    protected long lastLogin;

    @ApiModelProperty(
            notes = "The user’s last connection date",
            required = true
    )
    protected String lastLoginDate;

    @ApiModelProperty(
            notes = "Array of profile associated to this user through groups",
            required = true
    )
    protected List<SigfoxApiUsersUserRoles> userRoles;

    // ==================================================================
    // Getters & Setters
    // ==================================================================


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public List<SigfoxApiUsersUserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<SigfoxApiUsersUserRoles> userRoles) {
        this.userRoles = userRoles;
    }
}
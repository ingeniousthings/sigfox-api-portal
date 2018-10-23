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
public class SigfoxApiv2UserRead extends SigfoxApiv2UserCommon {

    @ApiModelProperty(
            notes = "The user’s identifier.",
            example = "5730510c7f071f9afd2bb2b7",
            required = false
    )
    protected String id;

    @ApiModelProperty(
            notes = "The user’s email.",
            example = "peter.jersy@example.com",
            required = false
    )
    protected String email;

    @ApiModelProperty(
            notes = "The encoded user’s password (only available with a specific right).",
            example = "Afddjslk0465sdfssqdf",
            required = false
    )
    protected String password;

    @ApiModelProperty(
            notes = "If the user account is locked or not.",
            example = "false",
            required = false
    )
    protected boolean locked;

    @ApiModelProperty(
            notes = "The user’s creation time since epoch.",
            example = "1370437718308",
            required = false
    )
    protected long creationTime;

    @ApiModelProperty(
            notes = "The user’s last login time.",
            example = "1461764132948",
            required = false
    )
    protected long lastLoginTime;


    @ApiModelProperty(
            notes = "Defines the rights the user has on a group",
            required = false
    )
    protected List<SigfoxApiv2UserRoleRead> userRoles;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<SigfoxApiv2UserRoleRead> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<SigfoxApiv2UserRoleRead> userRoles) {
        this.userRoles = userRoles;
    }
}

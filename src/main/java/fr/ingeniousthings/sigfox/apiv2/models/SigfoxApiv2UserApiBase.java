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

@Api(tags = "baseApiUser", description = "Defines the base API user’s properties")
public class SigfoxApiv2UserApiBase {

    @ApiModelProperty(
            notes = "The API user’s name",
            required = false
    )
    protected String name;

    @ApiModelProperty(
            notes = "The API user’s timezone",
            required = false
    )
    protected String timezone;

    @ApiModelProperty(
            notes = "The API user’s custom roles",
            required = false
    )
    protected List<String> customRoleIds;

    @ApiModelProperty(
            notes = "The API user’s profiles",
            required = false
    )
    protected List<String> profileIds;

    @ApiModelProperty(
            notes = "The group’s identifer",
            required = false
    )
    protected String groupId;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public List<String> getCustomRoleIds() {
        return customRoleIds;
    }

    public void setCustomRoleIds(List<String> customRoleIds) {
        this.customRoleIds = customRoleIds;
    }

    public List<String> getProfileIds() {
        return profileIds;
    }

    public void setProfileIds(List<String> profileIds) {
        this.profileIds = profileIds;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}

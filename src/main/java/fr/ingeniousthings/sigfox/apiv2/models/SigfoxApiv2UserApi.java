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

@Api(tags = "apiUser", description = "Defines the base API user’s properties")
public class SigfoxApiv2UserApi {

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
            notes = "The associated group information",
            required = false
    )
    protected SigfoxApiv2GroupMinimal group;

    @ApiModelProperty(
            notes = "The API user’s creation time since epoch",
            required = false
    )
    protected long creationTime;

    @ApiModelProperty(
            notes = "The API user’s identifier",
            required = false
    )
    protected String id;

    @ApiModelProperty(
            notes = "The API user’s acces token(password)",
            required = false
    )
    protected String accessToken;

    @ApiModelProperty(
            notes = "The API user’s access rights",
            required = false
    )
    protected SigfoxApiv2Profile profiles;

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

    public SigfoxApiv2GroupMinimal getGroup() {
        return group;
    }

    public void setGroup(SigfoxApiv2GroupMinimal group) {
        this.group = group;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public SigfoxApiv2Profile getProfiles() {
        return profiles;
    }

    public void setProfiles(SigfoxApiv2Profile profiles) {
        this.profiles = profiles;
    }
}

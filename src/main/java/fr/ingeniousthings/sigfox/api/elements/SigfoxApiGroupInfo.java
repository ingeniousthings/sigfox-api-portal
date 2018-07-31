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

public class SigfoxApiGroupInfo {

    @ApiModelProperty(
            notes = "The group’s identifier",
            required = true
    )
    protected String id;

    @ApiModelProperty(
            notes = "The group’s name",
            required = false
    )
    protected String name;

    @ApiModelProperty(
            notes = "The group’s name case insensitive",
            required = false
    )
    protected String nameCI;

    @ApiModelProperty(
            notes = "Description of this group",
            required = false
    )
    protected String description;

    @ApiModelProperty(
            notes = "Array with the group’s parents’ ids",
            required = false
    )
    protected List<String> path;

    @ApiModelProperty(
            notes = "true if the group is billable",
            required = false
    )
    protected boolean billable;

    @ApiModelProperty(
            notes = "the group BSS id",
            required = false
    )
    protected String bssId;

    @ApiModelProperty(
            notes = "this is the country ISO code (ISO 3166-1 alpha-3) where the operator manages its network. Only available for SNO and NIP.",
            required = false
    )
    protected String countryISOAlpha3;


    /**
     * Getters & Setters
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCI() {
        return nameCI;
    }

    public void setNameCI(String nameCI) {
        this.nameCI = nameCI;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    public String getBssId() {
        return bssId;
    }

    public void setBssId(String bssId) {
        this.bssId = bssId;
    }

    public String getCountryISOAlpha3() {
        return countryISOAlpha3;
    }

    public void setCountryISOAlpha3(String countryISOAlpha3) {
        this.countryISOAlpha3 = countryISOAlpha3;
    }
}

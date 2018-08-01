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

@Api(tags = "GroupCreation", description = "Defines the group entity for creation ")
public class SigfoxApiv2GroupCreation extends SigfoxApiv2GroupBase {

    @ApiModelProperty(
            notes = "The parent id.",
            required = false
    )
    protected String parentId;


    @ApiModelProperty(
            notes = "This is the country ISO code " +
                    "(https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3#Officially_assigned_code_elements) " +
                    "where the operator manages its network. Only available for SNO and NIP.",
            required = false
    )
    protected String countryISOAlpha3;



    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCountryISOAlpha3() {
        return countryISOAlpha3;
    }

    public void setCountryISOAlpha3(String countryISOAlpha3) {
        this.countryISOAlpha3 = countryISOAlpha3;
    }
}

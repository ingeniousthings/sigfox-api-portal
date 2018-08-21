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

@Api(tags = "globalCoveragePublicMap", description = "Defines tiles reference to display on web map")
public class SigfoxApiv2CoveragePublicMapResponse {

    @ApiModelProperty(
            notes = "The tiles base image url.",
            example = "https://tiles.sigfox.com/2d16bae056f4cce6aae7cce5e624cfd4",
            required = false
    )
    protected String baseImgUrl;

    @ApiModelProperty(
            notes = "The TMS template url.",
            example = "https://tiles.sigfox.com/2d16bae056f4cce6aae7cce5e624cfd4/{z}/{x}/{y}.png",
            required = false
    )
    protected String tmsTemplateUrl;


    @ApiModelProperty(
            notes = "Geographics bounds.",
            required = true
    )
    protected SigfoxApiv2Bounds bounds;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public String getBaseImgUrl() {
        return baseImgUrl;
    }

    public void setBaseImgUrl(String baseImgUrl) {
        this.baseImgUrl = baseImgUrl;
    }

    public String getTmsTemplateUrl() {
        return tmsTemplateUrl;
    }

    public void setTmsTemplateUrl(String tmsTemplateUrl) {
        this.tmsTemplateUrl = tmsTemplateUrl;
    }

    public SigfoxApiv2Bounds getBounds() {
        return bounds;
    }

    public void setBounds(SigfoxApiv2Bounds bounds) {
        this.bounds = bounds;
    }
}

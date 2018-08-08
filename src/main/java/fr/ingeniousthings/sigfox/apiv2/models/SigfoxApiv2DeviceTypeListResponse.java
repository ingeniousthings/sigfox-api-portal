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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "callbackErrorMessageList", description = "List of callback not delivered for a given group")
public class SigfoxApiv2DeviceTypeListResponse {


    @ApiModelProperty(
            notes = "List of the callback error",
            required = false
    )
    protected List<SigfoxApiv2CallbackErrorMessages> data;

    @ApiModelProperty(
            notes = "Next page information",
            required = false
    )
    protected SigfoxApiv2Pagination paging;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public List<SigfoxApiv2CallbackErrorMessages> getData() {
        return data;
    }

    public void setData(List<SigfoxApiv2CallbackErrorMessages> data) {
        this.data = data;
    }

    public SigfoxApiv2Pagination getPaging() {
        return paging;
    }

    public void setPaging(SigfoxApiv2Pagination paging) {
        this.paging = paging;
    }
}

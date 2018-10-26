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

@Api(tags = "deviceBulkCreationResponse ", description = "Bulk device creation response")
public class SigfoxApiv2JobDevCreateResponse extends SigfoxApiv2JobDevEditionResponse {

    @ApiModelProperty(
            notes = "List of device creation failed",
            example = "[ 'DE25', '1ABCD']",
            required = true
    )
    protected List<String> registrationFailed;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public List<String> getRegistrationFailed() {
        return registrationFailed;
    }

    public void setRegistrationFailed(List<String> registrationFailed) {
        this.registrationFailed = registrationFailed;
    }
}

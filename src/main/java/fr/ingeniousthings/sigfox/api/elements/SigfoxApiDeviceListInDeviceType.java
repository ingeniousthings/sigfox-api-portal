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
 * Summary
 *
 * Object returned by sigfox API corresponding to a All Devices ID/PAC for a device type.
 *
 * ----------------------------------------------------------------------------------
 * Response Format :
 *  {
 *       "data" : [
 *         { "id" : "0B59", "pac" : "6A757C859B23471B" },
 *         { "id" : "C032", "pac" : "374FC61194FC4DA8" },
 *         ...
 *       ]
 *     }
 *
 * @author Paul Pinault
 */
public class SigfoxApiDeviceListInDeviceType {

    protected class SigfoxApiDeviceListLine {
        @ApiModelProperty(
                notes = "Identifier of the device",
                required = true
        )
        public String  id;
        @ApiModelProperty(
                notes = "Porting Authorization Code for this device",
                required = true
        )
        public String  pac;
    }


    @ApiModelProperty(
            notes = "The array of device ids and pacs records",
            required = true
    )
    protected List<SigfoxApiDeviceListLine> data;


    public List<SigfoxApiDeviceListLine> getData() {
        return data;
    }

    public void setData(List<SigfoxApiDeviceListLine> data) {
        this.data = data;
    }
}



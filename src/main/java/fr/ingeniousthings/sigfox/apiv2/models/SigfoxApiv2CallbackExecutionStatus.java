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

@Api(tags = "cbStatus", description = "Callback Execution Status")
public class SigfoxApiv2CallbackExecutionStatus {
    @ApiModelProperty(
            notes = "Http response status",
            example = "200",
            required = false
    )
    protected int status;

    @ApiModelProperty(
            notes = "Http response message",
            example = "success",
            required = false
    )
    protected String info;

    @ApiModelProperty(
            notes = "Callback definition triggered",
            example = "var=2DC7C1%3B1500452712%3Bdeadbeaf%3B",
            required = false
    )
    protected String cbDef;

    @ApiModelProperty(
            notes = "Time the callback was called (in milliseconds since the Unix Epoch)",
            example = "1487065942000",
            required = false
    )
    protected long time;

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCbDef() {
        return cbDef;
    }

    public void setCbDef(String cbDef) {
        this.cbDef = cbDef;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

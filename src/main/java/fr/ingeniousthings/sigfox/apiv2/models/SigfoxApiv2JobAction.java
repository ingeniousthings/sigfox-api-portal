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

@Api(tags = "actionJob ", description = "Async job running status")
public class SigfoxApiv2JobAction {

    @ApiModelProperty(
            notes = "If the job is finished or not",
            example = "true",
            required = false
    )
    protected boolean jobDone;

    @ApiModelProperty(
            notes = "The informations about the devices already treated",
            required = false
    )
    protected SigfoxApiv2JobStatus status;



    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public boolean isJobDone() {
        return jobDone;
    }

    public void setJobDone(boolean jobDone) {
        this.jobDone = jobDone;
    }

    public SigfoxApiv2JobStatus getStatus() {
        return status;
    }

    public void setStatus(SigfoxApiv2JobStatus status) {
        this.status = status;
    }
}

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

@Api(tags = "registrationJob", description = "Device registration job status report")
public class SigfoxApiv2RegistrationJob {

    @ApiModelProperty(
            notes = "If the job is finished or not",
            required = false
    )
    protected boolean jobDone;

    @ApiModelProperty(
            notes = "The total number of devices given to be created",
            required = false
    )
    protected int total;


    @Api(tags = "registrationJob.Status", description = "The informations about the devices already treated")
    public class Status {

        @ApiModelProperty(
                notes = "Info about each error",
                required = false
        )
        public List<SigfoxApiv2JobError> errors;

        @ApiModelProperty(
                notes = "The number of devices successfully created, edited or transferred",
                required = false
        )
        public int success;

    }

    @ApiModelProperty(
            notes = "The informations about the devices already treated.",
            required = false
    )
    protected Status status;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public boolean isJobDone() {
        return jobDone;
    }

    public void setJobDone(boolean jobDone) {
        this.jobDone = jobDone;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

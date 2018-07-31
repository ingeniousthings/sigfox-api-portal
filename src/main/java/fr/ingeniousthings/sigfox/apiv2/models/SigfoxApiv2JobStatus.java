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

@Api(tags = "Job Status", description = "The informations about the devices already treated")
public class SigfoxApiv2JobStatus {

    @ApiModelProperty(
            notes = "The total number of devices given",
            required = false
    )
    protected int total;

    @ApiModelProperty(
            notes = "The number of devices successfully changed",
            required = false
    )
    protected int nbSuccess;

    @ApiModelProperty(
            notes = "The number of devices unsuccessfully changed",
            required = false
    )
    protected int nbErrors;


    @ApiModelProperty(
            notes = "reasons of each errors",
            required = false
    )
    protected List<SigfoxApiv2JobError> errors;


    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNbSuccess() {
        return nbSuccess;
    }

    public void setNbSuccess(int nbSuccess) {
        this.nbSuccess = nbSuccess;
    }

    public int getNbErrors() {
        return nbErrors;
    }

    public void setNbErrors(int nbErrors) {
        this.nbErrors = nbErrors;
    }

    public List<SigfoxApiv2JobError> getErrors() {
        return errors;
    }

    public void setErrors(List<SigfoxApiv2JobError> errors) {
        this.errors = errors;
    }
}

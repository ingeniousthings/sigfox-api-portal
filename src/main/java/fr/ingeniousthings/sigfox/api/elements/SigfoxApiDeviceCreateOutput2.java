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

/*
{
  "total": 25
  "jobId" : "5256c4d6c9a871b80f5a2e50",
  "transferFailed" : ["AC10", "00C1",...]
}
 */

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SigfoxApiDeviceCreateOutput2 {

    @ApiModelProperty(
            notes = "Total number of devices pushed in the request.",
            required = true
    )
    protected int total;
    @ApiModelProperty(
            notes = "The number of devices that have been actually created.",
            required = true
    )
    protected int createdCount;

    @ApiModelProperty(
            notes = "The number of devices that have been actually transfered.",
            required = true
    )
    protected int transferedCount;


    @ApiModelProperty(
            notes = "List of device identifiers for which the PAC was invalid, or whose ownership transfer was rejected.",
            required = true
    )
    protected List<String> transferFailed;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCreatedCount() {
        return createdCount;
    }

    public void setCreatedCount(int createdCount) {
        this.createdCount = createdCount;
    }

    public int getTransferedCount() {
        return transferedCount;
    }

    public void setTransferedCount(int transferedCount) {
        this.transferedCount = transferedCount;
    }

    public List<String> getTransferFailed() {
        return transferFailed;
    }

    public void setTransferFailed(List<String> transferFailed) {
        this.transferFailed = transferFailed;
    }
}

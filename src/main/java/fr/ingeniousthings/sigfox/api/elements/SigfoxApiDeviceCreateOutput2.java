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

package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

public class SigfoxApiDeviceTokenRenewalResponse {

    @ApiModelProperty(
            notes = "The total number of devices that should be updated.",
            required = true
    )
    protected int total;

    @ApiModelProperty(
            notes = "The number of errors.",
            required = true
    )
    protected int error;

    @ApiModelProperty(
            notes = "The id of the devices that failed to update.",
            required = true
    )
    protected String [] failed;

    // ================================================
    // Getters & Setters
    // ================================================


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String[] getFailed() {
        return failed;
    }

    public void setFailed(String[] failed) {
        this.failed = failed;
    }
}

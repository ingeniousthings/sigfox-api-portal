package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

public class SigfoxApiDeviceReplaceResponse {

    @ApiModelProperty(
            notes = "The number of devices that have been actually replaced.",
            required = true
    )
    protected int total;

    @ApiModelProperty(
            notes = "The number of errors encountered during changes.",
            required = false
    )
    protected int error;

    @ApiModelProperty(
            notes = "List of error messages. Each message is of the form: «Object INDEX : ERROR MESSAGE»." +
                    "ex : \"log\" : [\"Object 1 : Not the same device type\"]",
            required = false
    )
    protected String [] log;

    // =================================================
    // Getters & Setters
    // =================================================


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

    public String[] getLog() {
        return log;
    }

    public void setLog(String[] log) {
        this.log = log;
    }
}

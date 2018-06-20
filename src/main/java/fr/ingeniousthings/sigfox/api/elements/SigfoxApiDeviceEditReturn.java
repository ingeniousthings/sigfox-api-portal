package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@Api(tags = "SigfoxApiDeviceEditReturn Entity")
public class SigfoxApiDeviceEditReturn {

    @ApiModelProperty(notes = "The number of devices that have been actually modified", required = false)
    private int total;
    @ApiModelProperty(notes = "The number of errors encountered during changes", required = false)
    private int error;
    @ApiModelProperty(notes = "List of error messages. Each message is of the form: Object INDEX - Field FIELD : ERROR MESSAGE", required = false)
    private List<String> log;


    // -------------------------------------------
    // Generated Get/Set


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

    public List<String> getLog() {
        return log;
    }

    public void setLog(List<String> log) {
        this.log = log;
    }
}

package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SigfoxApiDeviceCreateJobStatusStatus {

    @ApiModelProperty(
            notes = "An array containing all error messages. ",
            required = true
    )
    public List<String> errors;         // liste of error messages

    @ApiModelProperty(
            notes = "The number of created + transferred devices.",
            required = true
    )
    public int success;                 // total created + transfered

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}

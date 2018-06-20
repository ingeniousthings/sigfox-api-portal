package fr.ingeniousthings.sigfox.api.elements;

import java.util.List;

public class SigfoxApiDeviceCreateJobStatusStatus {
    public List<String> errors;         // liste of error messages
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

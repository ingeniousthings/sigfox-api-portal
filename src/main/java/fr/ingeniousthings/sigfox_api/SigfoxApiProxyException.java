package fr.ingeniousthings.sigfox_api;

import org.springframework.http.HttpStatus;

public class SigfoxApiProxyException extends Exception {
    public HttpStatus  status;
    public String      errorMessage;

    public SigfoxApiProxyException(HttpStatus _status, String _errorMessage ) {
        super();
        this.status = _status;
        this.errorMessage = _errorMessage;
    }

}

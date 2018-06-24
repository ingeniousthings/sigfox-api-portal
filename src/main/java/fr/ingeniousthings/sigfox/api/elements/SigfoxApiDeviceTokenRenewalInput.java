package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

public class SigfoxApiDeviceTokenRenewalInput {

    @ApiModelProperty(
            notes = "List of devicesId you want to prevent token renewal, empty if none",
            required = true
    )
    protected String [] forbidden;

    @ApiModelProperty(
            notes = "List of devicesId you want token renewal, empty if none",
            required = true
    )
    protected String [] allowed;

    // ================================================
    // Getters & Setters
    // ================================================


    public String[] getForbidden() {
        return forbidden;
    }

    public void setForbidden(String[] forbidden) {
        this.forbidden = forbidden;
    }

    public String[] getAllowed() {
        return allowed;
    }

    public void setAllowed(String[] allowed) {
        this.allowed = allowed;
    }
}

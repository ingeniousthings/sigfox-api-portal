package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

public class SigfoxApiDeviceReplaceInput {

    @ApiModelProperty(
            notes = "This device will be replaced by the target device.",
            required = true
    )
    protected String deviceId;

    @ApiModelProperty(
            notes = "The target device id.",
            required = false
    )
    protected String targetDeviceId;


    // =================================================
    // Getters & Setters
    // =================================================


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTargetDeviceId() {
        return targetDeviceId;
    }

    public void setTargetDeviceId(String targetDeviceId) {
        this.targetDeviceId = targetDeviceId;
    }
}

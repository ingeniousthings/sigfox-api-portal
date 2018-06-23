package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

public class SigfoxApiDeviceNetworkStatus {

    @ApiModelProperty(
            notes = "The network status for the device. OK, the network works, NOK otherwise.",
            required = true
    )
    protected String networkStatus;

    public String getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(String networkStatus) {
        this.networkStatus = networkStatus;
    }
}

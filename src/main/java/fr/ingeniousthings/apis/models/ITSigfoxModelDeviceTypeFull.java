package fr.ingeniousthings.apis.models;

import fr.ingeniousthings.sigfox.apiv2.models.SigfoxApiv2Callback;
import fr.ingeniousthings.sigfox.apiv2.models.SigfoxApiv2DeviceType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "deviceTypeFull", description = "device type including callback definition")
public class ITSigfoxModelDeviceTypeFull {

    @ApiModelProperty(
            notes = "The device type definition",
            required = true
    )
    protected SigfoxApiv2DeviceType deviceTypeDefinition;

    @ApiModelProperty(
            notes = "The associated callbacks. Is an empty list when no callback are defined",
            required = true
    )
    protected List<SigfoxApiv2Callback> callbackList;

    // ============================================================
    // Custom part
    // ============================================================

    public ITSigfoxModelDeviceTypeFull() {
        this.callbackList = new ArrayList<SigfoxApiv2Callback>();
    }

    public void addCallback(SigfoxApiv2Callback c) {
        this.callbackList.add(c);
    }

    // ============================================================
    // Generated Getters & Setters
    // ============================================================


    public SigfoxApiv2DeviceType getDeviceTypeDefinition() {
        return deviceTypeDefinition;
    }

    public void setDeviceTypeDefinition(SigfoxApiv2DeviceType deviceTypeDefinition) {
        this.deviceTypeDefinition = deviceTypeDefinition;
    }

    public List<SigfoxApiv2Callback> getCallbackList() {
        return callbackList;
    }

    public void setCallbackList(List<SigfoxApiv2Callback> callbackList) {
        this.callbackList = callbackList;
    }
}

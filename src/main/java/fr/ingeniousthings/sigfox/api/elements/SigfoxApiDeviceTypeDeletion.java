package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

public class SigfoxApiDeviceTypeDeletion {

    @ApiModelProperty(
            notes = "The id of the device type to delete. This id must correspond to an existing device type.",
            required = true
    )
    protected String id;




    /**
     * Getters & Setters
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SigfoxApiDeviceTypeCreationResponse {

    @ApiModelProperty(
            notes = "Identifier of the created device type.",
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

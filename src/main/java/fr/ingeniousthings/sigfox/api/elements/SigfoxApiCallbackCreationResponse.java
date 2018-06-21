package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SigfoxApiCallbackCreationResponse {

    @ApiModelProperty(
            notes = "List of Identifier of the created callback.",
            required = true
    )
    protected List<String> ids;


    /**
     * Getters & Setters
     */

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}

package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

public class SigfoxApiGroupDeletion {

    @ApiModelProperty(
            notes = "The id of the group to delete. This id must correspond to an existing group.",
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

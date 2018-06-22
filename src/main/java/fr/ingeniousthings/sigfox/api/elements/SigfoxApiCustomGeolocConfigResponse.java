package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SigfoxApiCustomGeolocConfigResponse {


    @ApiModelProperty(
            notes = "The config id",
            required = true
    )
    protected String id;

    @ApiModelProperty(
            notes = "The name of the config",
            required = true
    )
    protected String name;

    @ApiModelProperty(
            notes = "The name of the group the config is member of",
            required = true
    )
    protected String groupName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

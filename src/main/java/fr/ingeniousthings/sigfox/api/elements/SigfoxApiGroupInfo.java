package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SigfoxApiGroupInfo {

    @ApiModelProperty(
            notes = "The group’s identifier",
            required = true
    )
    protected String id;

    @ApiModelProperty(
            notes = "The group’s name",
            required = false
    )
    protected String name;

    @ApiModelProperty(
            notes = "The group’s name case insensitive",
            required = false
    )
    protected String nameCI;

    @ApiModelProperty(
            notes = "Description of this group",
            required = false
    )
    protected String description;

    @ApiModelProperty(
            notes = "Array with the group’s parents’ ids",
            required = false
    )
    protected List<String> path;

    @ApiModelProperty(
            notes = "true if the group is billable",
            required = false
    )
    protected boolean billable;

    @ApiModelProperty(
            notes = "the group BSS id",
            required = false
    )
    protected String bssId;

    @ApiModelProperty(
            notes = "this is the country ISO code (ISO 3166-1 alpha-3) where the operator manages its network. Only available for SNO and NIP.",
            required = false
    )
    protected String countryISOAlpha3;


    /**
     * Getters & Setters
     */
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

    public String getNameCI() {
        return nameCI;
    }

    public void setNameCI(String nameCI) {
        this.nameCI = nameCI;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    public String getBssId() {
        return bssId;
    }

    public void setBssId(String bssId) {
        this.bssId = bssId;
    }

    public String getCountryISOAlpha3() {
        return countryISOAlpha3;
    }

    public void setCountryISOAlpha3(String countryISOAlpha3) {
        this.countryISOAlpha3 = countryISOAlpha3;
    }
}

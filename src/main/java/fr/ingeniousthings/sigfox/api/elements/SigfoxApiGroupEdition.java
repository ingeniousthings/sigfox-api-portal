package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

public class SigfoxApiGroupEdition {

    @ApiModelProperty(
            notes = "The id of the group to edit. This id must correspond to an existing group.",
            required = true
    )
    protected String id;

    @ApiModelProperty(
            notes = "The group’s name (must be unique at its level)",
            required = true
    )
    protected String name;

    @ApiModelProperty(
            notes = "Description of this group",
            required = true
    )
    protected String description;

    @ApiModelProperty(
            notes = "true if the group is billable",
            required = false
    )
    protected boolean billable;

    @ApiModelProperty(
            notes = "The client name.",
            required = false
    )
    protected String clientName;

    @ApiModelProperty(
            notes = "The client email.",
            required = false
    )
    protected String clientEmail;

    @ApiModelProperty(
            notes = "The client address.",
            required = false
    )
    protected String clientAddress;



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }
}

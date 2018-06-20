package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SigfoxApiGroupCreation {

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
            notes = "The parent’s id of the group",
            required = false
    )
    protected String parent;

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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
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

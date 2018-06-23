package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

public class SigfoxApiPagingModel {

    @ApiModelProperty(
            notes = "URL of the next «page»",
            required = false
    )
    protected String next;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}

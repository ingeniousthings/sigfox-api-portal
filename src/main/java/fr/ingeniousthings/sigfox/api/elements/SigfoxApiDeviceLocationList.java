package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.List;

public class SigfoxApiDeviceLocationList {

    @ApiModelProperty(
            notes = "The array of device messages location",
            required = true
    )
    protected SigfoxApiLocationModel[] data;

    @ApiModelProperty(
            notes = "Access to the next page of devices",
            required = true
    )
    protected SigfoxApiPagingModel paging;




    public SigfoxApiLocationModel[] getData() {
        return data;
    }

    public void setData(SigfoxApiLocationModel[] data) {
        this.data = data;
    }

    public SigfoxApiPagingModel getPaging() {
        return paging;
    }

    public void setPaging(SigfoxApiPagingModel paging) {
        this.paging = paging;
    }
}

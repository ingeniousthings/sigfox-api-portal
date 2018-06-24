package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Format:
 *
 */


public class SigfoxApiUsersDetailList {

    @ApiModelProperty(
            notes = "Array of users information records",
            required = true
    )
    protected List<SigfoxApiUsersDetail> data;

    @ApiModelProperty(
            notes = "Paging information, if more users are available",
            required = true
    )
    protected SigfoxApiPagingModel paging;


    // ==================================================================
    // Getters & Setters
    // ==================================================================


    public List<SigfoxApiUsersDetail> getData() {
        return data;
    }

    public void setData(List<SigfoxApiUsersDetail> data) {
        this.data = data;
    }

    public SigfoxApiPagingModel getPaging() {
        return paging;
    }

    public void setPaging(SigfoxApiPagingModel paging) {
        this.paging = paging;
    }
}
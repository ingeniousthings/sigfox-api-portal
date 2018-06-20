package fr.ingeniousthings.sigfox.api.elements;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SigfoxApiGroupInfoList {
    public class GroupListPaging {
        @ApiModelProperty(
                notes = "URL of the next «page» of groups",
                required = true
        )
        protected String next;
        public String getNext() {
            return next;
        }
        public void setNext(String next) {
            this.next = next;
        }
    }

    @ApiModelProperty(
            notes = "Array of group information records",
            required = true
    )
    protected List<SigfoxApiGroupInfo> data;

    @ApiModelProperty(
            notes = "Paging information, if more groups are available",
            required = false
    )
    protected GroupListPaging paging;

    /**
     * Getter & Setters
     */
    public List<SigfoxApiGroupInfo> getData() {
        return data;
    }

    public void setData(List<SigfoxApiGroupInfo> data) {
        this.data = data;
    }

    public GroupListPaging getPaging() {
        return paging;
    }

    public void setPaging(GroupListPaging paging) {
        this.paging = paging;
    }
}

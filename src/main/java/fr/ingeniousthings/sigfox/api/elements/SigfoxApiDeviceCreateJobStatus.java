package fr.ingeniousthings.sigfox.api.elements;

/*
{
  "jobDone": true,
  "operatorId": "deadbeeffacecafebabecafe",
  "total": 2,
  "name": "API bulk create job 55770e9545cecc5f27b99e63",
  "description": "API bulk create job 55770e9545cecc5f27b99e63",
  "status": {
      "errors": ["Error meessage for entity 0001 ...", "..."],
      "success": 1
  }
}
 */

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SigfoxApiDeviceCreateJobStatus {

    @ApiModelProperty(
            notes = "True if the devices creation job is finished (not necessarily successfully), false otherwise.",
            required = true
    )
    protected boolean jobDone;              // true when finished


    @ApiModelProperty(
            notes = "The devices operator id. ",
            required = true
    )
    protected String operatorId;

    @ApiModelProperty(
            notes = "Total number of devices to be created. ",
            required = true
    )
    protected int total;                    // number of device created

    @ApiModelProperty(
            notes = "The creation job's name. ",
            required = true
    )
    protected String name;

    @ApiModelProperty(
            notes = "The creation job's description. ",
            required = true
    )
    protected String description;

    @ApiModelProperty(
            notes = "The job status: ",
            required = true
    )
    protected SigfoxApiDeviceCreateJobStatusStatus status;


    public boolean isJobDone() {
        return jobDone;
    }

    public void setJobDone(boolean jobDone) {
        this.jobDone = jobDone;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public SigfoxApiDeviceCreateJobStatusStatus getStatus() {
        return status;
    }

    public void setStatus(SigfoxApiDeviceCreateJobStatusStatus status) {
        this.status = status;
    }
}

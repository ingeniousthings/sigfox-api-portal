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

import java.util.List;

public class SigfoxApiDeviceCreateJobStatus {

    protected boolean jobDone;              // true when finished
    protected String operatorId;
    protected int total;                    // number of device created
    protected String name;
    protected String description;
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

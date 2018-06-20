package fr.ingeniousthings.sigfox.api.elements;

/*
{
  "total": 25
  "jobId" : "5256c4d6c9a871b80f5a2e50",
  "transferFailed" : ["AC10", "00C1",...]
}
 */

import java.util.List;

public class SigfoxApiDeviceCreateOutput {

    protected int total;
    protected String jobId;
    protected List<String> transferFailed;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public List<String> getTransferFailed() {
        return transferFailed;
    }

    public void setTransferFailed(List<String> transferFailed) {
        this.transferFailed = transferFailed;
    }
}

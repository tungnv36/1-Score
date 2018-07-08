package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity;

import com.google.gson.annotations.SerializedName;

public class JobResponse {

    @SerializedName("Job")
    private JobEntity job;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

    public JobEntity getJob() {
        return job;
    }

    public void setJob(JobEntity job) {
        this.job = job;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public static class JobEntity {
        @SerializedName("SalaryBoardId")
        private int salaryboardid;
        @SerializedName("ContractId")
        private int contractid;
        @SerializedName("CvId")
        private int cvid;
        @SerializedName("SalaryId")
        private int salaryid;
        @SerializedName("PositionId")
        private int positionid;
        @SerializedName("CompanyName")
        private String companyname;
        @SerializedName("JobId")
        private int jobid;
        @SerializedName("Username")
        private String username;

        public int getSalaryboardid() {
            return salaryboardid;
        }

        public void setSalaryboardid(int salaryboardid) {
            this.salaryboardid = salaryboardid;
        }

        public int getContractid() {
            return contractid;
        }

        public void setContractid(int contractid) {
            this.contractid = contractid;
        }

        public int getCvid() {
            return cvid;
        }

        public void setCvid(int cvid) {
            this.cvid = cvid;
        }

        public int getSalaryid() {
            return salaryid;
        }

        public void setSalaryid(int salaryid) {
            this.salaryid = salaryid;
        }

        public int getPositionid() {
            return positionid;
        }

        public void setPositionid(int positionid) {
            this.positionid = positionid;
        }

        public String getCompanyname() {
            return companyname;
        }

        public void setCompanyname(String companyname) {
            this.companyname = companyname;
        }

        public int getJobid() {
            return jobid;
        }

        public void setJobid(int jobid) {
            this.jobid = jobid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}

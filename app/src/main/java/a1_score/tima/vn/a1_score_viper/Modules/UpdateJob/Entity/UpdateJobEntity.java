package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity;

import com.google.gson.annotations.SerializedName;

public class UpdateJobEntity {

    @SerializedName("salary_board_id")
    private int salaryBoardId;
    @SerializedName("contract_id")
    private int contractId;
    @SerializedName("cv_id")
    private int cvId;
    @SerializedName("salary_id")
    private int salaryId;
    @SerializedName("position_id")
    private int positionId;
    @SerializedName("company_name")
    private String companyName;
    @SerializedName("company_address")
    private String companyAddress;
    @SerializedName("job_id")
    private int jobId;
    @SerializedName("username")
    private String username;

    public int getSalaryBoardId() {
        return salaryBoardId;
    }

    public void setSalaryBoardId(int salaryBoardId) {
        this.salaryBoardId = salaryBoardId;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getCvId() {
        return cvId;
    }

    public void setCvId(int cvId) {
        this.cvId = cvId;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
}

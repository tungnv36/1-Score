package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobDictionaryResultEntity {

    @SerializedName("SalaryLevels")
    private List<SalaryLevelsEntity> salarylevels;
    @SerializedName("Positions")
    private List<PositionsEntity> positions;
    @SerializedName("Jobs")
    private List<JobsEntity> jobs;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

    public List<SalaryLevelsEntity> getSalarylevels() {
        return salarylevels;
    }

    public void setSalarylevels(List<SalaryLevelsEntity> salarylevels) {
        this.salarylevels = salarylevels;
    }

    public List<PositionsEntity> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionsEntity> positions) {
        this.positions = positions;
    }

    public List<JobsEntity> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobsEntity> jobs) {
        this.jobs = jobs;
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

    public static class SalaryLevelsEntity {
        @SerializedName("Salary")
        private String salary;
        @SerializedName("Id")
        private int id;

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class PositionsEntity {
        @SerializedName("Position")
        private String position;
        @SerializedName("Id")
        private int id;

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class JobsEntity {
        @SerializedName("JobType")
        private String jobtype;
        @SerializedName("Id")
        private int id;

        public String getJobtype() {
            return jobtype;
        }

        public void setJobtype(String jobtype) {
            this.jobtype = jobtype;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

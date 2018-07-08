package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ColleagueResponse {

    @SerializedName("Colleagues")
    private List<ColleagueEntity> colleagues;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;
    @SerializedName("Username")
    private String username;

    public List<ColleagueEntity> getColleagues() {
        return colleagues;
    }

    public void setColleagues(List<ColleagueEntity> colleagues) {
        this.colleagues = colleagues;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class ColleagueEntity {
        @SerializedName("ColleaguePhone")
        private String colleaguephone;
        @SerializedName("ColleagueName")
        private String colleaguename;

        public String getColleaguephone() {
            return colleaguephone;
        }

        public void setColleaguephone(String colleaguephone) {
            this.colleaguephone = colleaguephone;
        }

        public String getColleaguename() {
            return colleaguename;
        }

        public void setColleaguename(String colleaguename) {
            this.colleaguename = colleaguename;
        }
    }
}

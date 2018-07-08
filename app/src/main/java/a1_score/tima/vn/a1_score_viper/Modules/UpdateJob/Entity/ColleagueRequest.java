package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ColleagueRequest {


    @SerializedName("colleagues")
    private List<ColleagueEntity> colleagues;
    @SerializedName("username")
    private String username;

    public List<ColleagueEntity> getColleagues() {
        return colleagues;
    }

    public void setColleagues(List<ColleagueEntity> colleagues) {
        this.colleagues = colleagues;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class ColleagueEntity {
        @SerializedName("colleague_phone")
        private String colleaguePhone;
        @SerializedName("colleague_name")
        private String colleagueName;

        public String getColleaguePhone() {
            return colleaguePhone;
        }

        public void setColleaguePhone(String colleaguePhone) {
            this.colleaguePhone = colleaguePhone;
        }

        public String getColleagueName() {
            return colleagueName;
        }

        public void setColleagueName(String colleagueName) {
            this.colleagueName = colleagueName;
        }
    }
}

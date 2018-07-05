package a1_score.tima.vn.a1_score_viper.Modules.Setting.Entity;

import com.google.gson.annotations.SerializedName;

public class LogoutResultEntity {

    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

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
}

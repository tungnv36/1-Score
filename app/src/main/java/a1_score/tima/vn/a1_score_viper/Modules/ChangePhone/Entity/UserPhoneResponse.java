package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity;

import com.google.gson.annotations.SerializedName;

public class UserPhoneResponse {
    @SerializedName("StatusCode")
    private int statusCode;
    @SerializedName("message")
    private String Message;
    @SerializedName("User")
    private UserPhoneRequest user;

    public UserPhoneResponse(int statusCode, String message, UserPhoneRequest user) {
        this.statusCode = statusCode;
        Message = message;
        this.user = user;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public UserPhoneRequest getUser() {
        return user;
    }

    public void setUser(UserPhoneRequest user) {
        this.user = user;
    }
}

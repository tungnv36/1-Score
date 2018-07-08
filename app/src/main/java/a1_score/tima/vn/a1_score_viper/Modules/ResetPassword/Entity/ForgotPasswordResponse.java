package a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Entity;

import com.google.gson.annotations.SerializedName;

public class ForgotPasswordResponse {

    @SerializedName("StatusCode")
    private int statusCode;
    @SerializedName("Message")
    private String message;
    @SerializedName("User")
    private UserPassword user;

    public ForgotPasswordResponse(int statusCode, String message, UserPassword user) {
        this.statusCode = statusCode;
        this.message = message;
        this.user = user;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserPassword getUser() {
        return user;
    }

    public void setUser(UserPassword user) {
        this.user = user;
    }
}

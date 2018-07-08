package a1_score.tima.vn.a1_score_viper.Modules.Register.Entity;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("StatusCode")
    private int statusCode;
    @SerializedName("Message")
    private String message;

    public RegisterResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
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
}

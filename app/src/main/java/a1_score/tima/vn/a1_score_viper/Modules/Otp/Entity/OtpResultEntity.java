package a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity;

import com.google.gson.annotations.SerializedName;

public class OtpResultEntity {

    @SerializedName("StatusCode")
    private int statusCode;
    @SerializedName("Message")
    private String message;
    @SerializedName("Token")
    private String token;

    public OtpResultEntity(int statusCode, String message, String token) {
        this.statusCode = statusCode;
        this.message = message;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

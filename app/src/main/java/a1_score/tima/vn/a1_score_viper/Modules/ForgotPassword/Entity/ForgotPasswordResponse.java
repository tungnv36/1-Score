package a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity;

import com.google.gson.annotations.SerializedName;

public class ForgotPasswordResponse {

    @SerializedName("StatusCode")
    private int statusCode;
    @SerializedName("OTPCode")
    private String oTPCode;
    @SerializedName("Message")
    private String message;

    public ForgotPasswordResponse(int statusCode, String oTPCode, String message) {
        this.statusCode = statusCode;
        this.oTPCode = oTPCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getoTPCode() {
        return oTPCode;
    }

    public void setoTPCode(String oTPCode) {
        this.oTPCode = oTPCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

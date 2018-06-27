package a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity;

public class ForgotPasswordResultEntity {

    private int StatusCode;
    private String OTPCode;
    private String Message;

    public ForgotPasswordResultEntity(int statusCode, String oTPCode, String message) {
        StatusCode = statusCode;
        OTPCode = oTPCode;
        Message = message;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getPhoneNumber() {
        return OTPCode;
    }

    public void setPhoneNumber(String oTPCode) {
        OTPCode = oTPCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}

package a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity;

public class ForgotPasswordResultEntity {

    private int StatusCode;
    private String PhoneNumber;
    private String Message;

    public ForgotPasswordResultEntity(int statusCode, String phoneNumber, String message) {
        StatusCode = statusCode;
        PhoneNumber = phoneNumber;
        Message = message;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}

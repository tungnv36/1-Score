package a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity;

public class OtpResultEntity {

    private int StatusCode;
    private String Message;
    private String Token;

    public OtpResultEntity(int statusCode, String message, String token) {
        StatusCode = statusCode;
        Message = message;
        Token = token;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}

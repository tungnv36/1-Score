package a1_score.tima.vn.a1_score_viper.Modules.Register.Entity;

public class RegisterResultEntity {

    private int StatusCode;
    private String Message;

    public RegisterResultEntity(int statusCode, String message) {
        StatusCode = statusCode;
        Message = message;
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

}

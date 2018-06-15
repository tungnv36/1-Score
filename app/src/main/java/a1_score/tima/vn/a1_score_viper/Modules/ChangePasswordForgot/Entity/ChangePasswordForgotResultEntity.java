package a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Entity;

public class ChangePasswordForgotResultEntity {

    private int StatusCode;
    private String Message;
    private ChangePassUserEntity User;

    public ChangePasswordForgotResultEntity(int statusCode, String message, ChangePassUserEntity user) {
        StatusCode = statusCode;
        Message = message;
        User = user;
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

    public ChangePassUserEntity getUser() {
        return User;
    }

    public void setUser(ChangePassUserEntity user) {
        User = user;
    }
}

package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity;

public class ChangePhoneResultEntity {
    private int StatusCode;
    private String Message;
    private ChangePhoneUserEntity User;

    public ChangePhoneResultEntity(int statusCode, String message, ChangePhoneUserEntity user) {
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

    public ChangePhoneUserEntity getUser() {
        return User;
    }

    public void setUser(ChangePhoneUserEntity user) {
        User = user;
    }
}

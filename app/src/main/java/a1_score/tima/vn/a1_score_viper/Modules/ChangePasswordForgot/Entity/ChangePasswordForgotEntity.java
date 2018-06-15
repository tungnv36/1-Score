package a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Entity;

public class ChangePasswordForgotEntity {

    private String username;
    private String password;
    private String confirm_password;

    public ChangePasswordForgotEntity(String username, String password, String confirm_password) {
        this.username = username;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}

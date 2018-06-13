package a1_score.tima.vn.a1_score_viper.Modules.Register.Entity;

import com.google.gson.annotations.SerializedName;

public class RegisterEntity {

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("confirm_password")
    private String confirm_password;
    @SerializedName("email")
    private String email;

    public RegisterEntity(String username, String password, String confirm_password, String email) {
        this.username = username;
        this.password = password;
        this.confirm_password = confirm_password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

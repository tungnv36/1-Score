package a1_score.tima.vn.a1_score_viper.Modules.Login.Entity;

import com.google.gson.annotations.SerializedName;

public class LoginEntity {

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public LoginEntity(String username, String password) {
        this.username = username;
        this.password = password;
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

}

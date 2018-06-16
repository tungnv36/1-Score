package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity;

import com.google.gson.annotations.SerializedName;

public class ChangePhoneEntity {

    @SerializedName("username")
    private String username;
    @SerializedName("new_phone_number")
    private String new_phone_number;
    @SerializedName("password")
    private String password;

    public ChangePhoneEntity(String username, String new_phone_number, String password) {
        this.username = username;
        this.new_phone_number = new_phone_number;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNew_phone_number() {
        return new_phone_number;
    }

    public void setNew_phone_number(String new_phone_number) {
        this.new_phone_number = new_phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

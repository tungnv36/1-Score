package a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity;

import com.google.gson.annotations.SerializedName;

public class ForgotPasswordEntity {

    @SerializedName("phone_number")
    private String phone_number;

    public ForgotPasswordEntity(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}

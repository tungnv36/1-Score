package a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity;

import com.google.gson.annotations.SerializedName;

public class ForgotPasswordRequest {

    @SerializedName("phone_number")
    private String phoneNumber;

    public ForgotPasswordRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

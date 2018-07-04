package a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity;

import com.google.gson.annotations.SerializedName;

public class OtpEntity {

    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("action")
    private String action;
    @SerializedName("otp_code")
    private String otp_code;

    public OtpEntity(String phoneNumber, String action, String otp_code) {
        this.phoneNumber = phoneNumber;
        this.action = action;
        this.otp_code = otp_code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPhone_number() {
        return action;
    }

    public void setPhone_number(String action) {
        this.action = action;
    }

    public String getOtp_code() {
        return otp_code;
    }

    public void setOtp_code(String otp_code) {
        this.otp_code = otp_code;
    }
}

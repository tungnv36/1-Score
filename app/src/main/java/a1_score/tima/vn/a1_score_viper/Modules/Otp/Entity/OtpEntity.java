package a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity;

import com.google.gson.annotations.SerializedName;

public class OtpEntity {

    @SerializedName("phone_number")
    private String phone_number;
    @SerializedName("otp_code")
    private String otp_code;

    public OtpEntity(String phone_number, String otp_code) {
        this.phone_number = phone_number;
        this.otp_code = otp_code;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getOtp_code() {
        return otp_code;
    }

    public void setOtp_code(String otp_code) {
        this.otp_code = otp_code;
    }
}

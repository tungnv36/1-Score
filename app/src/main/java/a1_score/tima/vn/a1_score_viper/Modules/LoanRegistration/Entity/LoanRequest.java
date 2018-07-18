package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity;

import com.google.gson.annotations.SerializedName;

public class LoanRequest {

    @SerializedName("username")
    private String username;
    @SerializedName("payment_method_id")
    private int paymentMethodId;
    @SerializedName("purpose_id")
    private int purposeId;
    @SerializedName("duration")
    private int duration;
    @SerializedName("package_id")
    private int packageId;
    @SerializedName("value")
    private long value;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public int getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(int purposeId) {
        this.purposeId = purposeId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}

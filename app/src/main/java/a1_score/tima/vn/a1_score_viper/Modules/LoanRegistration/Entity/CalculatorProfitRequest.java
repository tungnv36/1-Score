package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity;

import com.google.gson.annotations.SerializedName;

public class CalculatorProfitRequest {

    @SerializedName("value")
    private long value;
    @SerializedName("duration")
    private int duration;
    @SerializedName("package_id")
    private int packageId;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
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
}

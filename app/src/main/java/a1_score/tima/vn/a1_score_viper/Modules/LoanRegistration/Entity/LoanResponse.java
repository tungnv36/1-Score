package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity;

import com.google.gson.annotations.SerializedName;

public class LoanResponse {

    @SerializedName("LoanCredit")
    private LoancreditEntity loancredit;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

    public LoancreditEntity getLoancredit() {
        return loancredit;
    }

    public void setLoancredit(LoancreditEntity loancredit) {
        this.loancredit = loancredit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public static class LoancreditEntity {
        @SerializedName("Duration")
        private int duration;
        @SerializedName("PurposeId")
        private int purposeid;
        @SerializedName("PaymentMethodId")
        private int paymentmethodid;
        @SerializedName("Profit")
        private long profit;
        @SerializedName("ConsultantFee")
        private long consultantfee;
        @SerializedName("Fee")
        private long fee;
        @SerializedName("Value")
        private long value;
        @SerializedName("RequestTime")
        private String requesttime;
        @SerializedName("PackageId")
        private int packageid;
        @SerializedName("FormalityId")
        private int FormalityId;

        public int getFormalityId() {
            return FormalityId;
        }

        public void setFormalityId(int formalityId) {
            FormalityId = formalityId;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getPurposeid() {
            return purposeid;
        }

        public void setPurposeid(int purposeid) {
            this.purposeid = purposeid;
        }

        public int getPaymentmethodid() {
            return paymentmethodid;
        }

        public void setPaymentmethodid(int paymentmethodid) {
            this.paymentmethodid = paymentmethodid;
        }

        public long getProfit() {
            return profit;
        }

        public void setProfit(long profit) {
            this.profit = profit;
        }

        public long getConsultantfee() {
            return consultantfee;
        }

        public void setConsultantfee(long consultantfee) {
            this.consultantfee = consultantfee;
        }

        public long getFee() {
            return fee;
        }

        public void setFee(long fee) {
            this.fee = fee;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public String getRequesttime() {
            return requesttime;
        }

        public void setRequesttime(String requesttime) {
            this.requesttime = requesttime;
        }

        public int getPackageid() {
            return packageid;
        }

        public void setPackageid(int packageid) {
            this.packageid = packageid;
        }

    }
}

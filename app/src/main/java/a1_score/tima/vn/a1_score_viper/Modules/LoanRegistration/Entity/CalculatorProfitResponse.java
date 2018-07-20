package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity;

import com.google.gson.annotations.SerializedName;

public class CalculatorProfitResponse {

    @SerializedName("LoanCreditProfit")
    private LoancreditprofitEntity loancreditprofit;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

    public LoancreditprofitEntity getLoancreditprofit() {
        return loancreditprofit;
    }

    public void setLoancreditprofit(LoancreditprofitEntity loancreditprofit) {
        this.loancreditprofit = loancreditprofit;
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

    public static class LoancreditprofitEntity {
        @SerializedName("ConsultantFee")
        private int consultantfee;
        @SerializedName("Fee")
        private int fee;
        @SerializedName("Profit")
        private int profit;
        @SerializedName("TotalProfit")
        private int totalprofit;

        public int getConsultantfee() {
            return consultantfee;
        }

        public void setConsultantfee(int consultantfee) {
            this.consultantfee = consultantfee;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getProfit() {
            return profit;
        }

        public void setProfit(int profit) {
            this.profit = profit;
        }

        public int getTotalprofit() {
            return totalprofit;
        }

        public void setTotalprofit(int totalprofit) {
            this.totalprofit = totalprofit;
        }
    }
}

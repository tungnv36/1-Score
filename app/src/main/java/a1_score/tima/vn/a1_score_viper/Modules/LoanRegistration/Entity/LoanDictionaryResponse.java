package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoanDictionaryResponse {

    @SerializedName("PaymentMethod")
    private List<PaymentMethodEntity> paymentmethod;
    @SerializedName("Purpose")
    private List<PurposeEntity> purpose;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

    public List<PaymentMethodEntity> getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(List<PaymentMethodEntity> paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public List<PurposeEntity> getPurpose() {
        return purpose;
    }

    public void setPurpose(List<PurposeEntity> purpose) {
        this.purpose = purpose;
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

    public static class PaymentMethodEntity {
        @SerializedName("Method")
        private String method;
        @SerializedName("Id")
        private int id;

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class PurposeEntity {
        @SerializedName("Purpose")
        private String purpose;
        @SerializedName("Id")
        private int id;

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

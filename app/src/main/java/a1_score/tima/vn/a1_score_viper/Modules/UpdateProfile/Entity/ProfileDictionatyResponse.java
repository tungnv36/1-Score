package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileDictionatyResponse {

    @SerializedName("BankAccountTypes")
    private List<BankAccountTypesEntity> bankaccounttypes;
    @SerializedName("Banks")
    private List<BanksEntity> banks;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

    public List<BankAccountTypesEntity> getBankaccounttypes() {
        return bankaccounttypes;
    }

    public void setBankaccounttypes(List<BankAccountTypesEntity> bankaccounttypes) {
        this.bankaccounttypes = bankaccounttypes;
    }

    public List<BanksEntity> getBanks() {
        return banks;
    }

    public void setBanks(List<BanksEntity> banks) {
        this.banks = banks;
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

    public static class BankAccountTypesEntity {
        @SerializedName("Type")
        private String type;
        @SerializedName("Id")
        private int id;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class BanksEntity {
        @SerializedName("Name")
        private String name;
        @SerializedName("Id")
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

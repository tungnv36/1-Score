package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfileResultEntity {


    @Expose
    @SerializedName("Profile")
    private ProfileEntity profile;
    @Expose
    @SerializedName("Message")
    private String message;
    @Expose
    @SerializedName("StatusCode")
    private int statuscode;

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
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

    public static class ProfileEntity {
        @Expose
        @SerializedName("UpdatedDate")
        private int updateddate;
        @Expose
        @SerializedName("CreatedDate")
        private int createddate;
        @Expose
        @SerializedName("CardTerm")
        private String cardterm;
        @Expose
        @SerializedName("BankAccNumber")
        private String bankaccnumber;
        @Expose
        @SerializedName("IdImage1")
        private String idimage1;
        @Expose
        @SerializedName("Address")
        private String address;
        @Expose
        @SerializedName("IdNumber")
        private String idnumber;
        @Expose
        @SerializedName("DateOfBirth")
        private String dateofbirth;
        @Expose
        @SerializedName("Fullname")
        private String fullname;
        @Expose
        @SerializedName("Username")
        private String username;
        @Expose
        @SerializedName("Id")
        private int id;

        public int getUpdateddate() {
            return updateddate;
        }

        public void setUpdateddate(int updateddate) {
            this.updateddate = updateddate;
        }

        public int getCreateddate() {
            return createddate;
        }

        public void setCreateddate(int createddate) {
            this.createddate = createddate;
        }

        public String getCardterm() {
            return cardterm;
        }

        public void setCardterm(String cardterm) {
            this.cardterm = cardterm;
        }

        public String getBankaccnumber() {
            return bankaccnumber;
        }

        public void setBankaccnumber(String bankaccnumber) {
            this.bankaccnumber = bankaccnumber;
        }

        public String getIdimage1() {
            return idimage1;
        }

        public void setIdimage1(String idimage1) {
            this.idimage1 = idimage1;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIdnumber() {
            return idnumber;
        }

        public void setIdnumber(String idnumber) {
            this.idnumber = idnumber;
        }

        public String getDateofbirth() {
            return dateofbirth;
        }

        public void setDateofbirth(String dateofbirth) {
            this.dateofbirth = dateofbirth;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

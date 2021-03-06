package a1_score.tima.vn.a1_score_viper.Modules.Login.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @Expose
    @SerializedName("StatusCode")
    private int statuscode;
    @Expose
    @SerializedName("User")
    private UserEntity user;
    @Expose
    @SerializedName("Token")
    private String token;
    @Expose
    @SerializedName("Message")
    private String message;

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class UserEntity {
        @Expose
        @SerializedName("Progress")
        private int progress;
        @Expose
        @SerializedName("UrlAvatar")
        private String urlavatar;
        @Expose
        @SerializedName("UrlCardImage")
        private String urlcardimage;
        @Expose
        @SerializedName("UrlImage2")
        private String urlimage2;
        @Expose
        @SerializedName("UrlImage1")
        private String urlimage1;
        @Expose
        @SerializedName("Level")
        private int level;
        @Expose
        @SerializedName("Scored")
        private long scored;
        @Expose
        @SerializedName("Sex")
        private int sex;
        @Expose
        @SerializedName("CardTerm")
        private String cardterm;
        @Expose
        @SerializedName("BankAccNumber")
        private String bankaccnumber;
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
        @SerializedName("UserId")
        private String userid;

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public String getUrlavatar() {
            return urlavatar;
        }

        public void setUrlavatar(String urlavatar) {
            this.urlavatar = urlavatar;
        }

        public String getUrlcardimage() {
            return urlcardimage;
        }

        public void setUrlcardimage(String urlcardimage) {
            this.urlcardimage = urlcardimage;
        }

        public String getUrlimage2() {
            return urlimage2;
        }

        public void setUrlimage2(String urlimage2) {
            this.urlimage2 = urlimage2;
        }

        public String getUrlimage1() {
            return urlimage1;
        }

        public void setUrlimage1(String urlimage1) {
            this.urlimage1 = urlimage1;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public long getScored() {
            return scored;
        }

        public void setScored(long scored) {
            this.scored = scored;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }

}

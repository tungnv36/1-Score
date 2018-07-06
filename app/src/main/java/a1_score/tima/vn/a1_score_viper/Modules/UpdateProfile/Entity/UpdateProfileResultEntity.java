package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity;

import com.google.gson.annotations.SerializedName;

public class UpdateProfileResultEntity {
    @SerializedName("StatusCode")
    private int statuscode;
    @SerializedName("User")
    private UserEntity user;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class UserEntity {
        @SerializedName("Progress")
        private double progress;
        @SerializedName("Level")
        private int level;
        @SerializedName("UrlCardImage")
        private String urlcardimage;
        @SerializedName("UrlImage2")
        private String urlimage2;
        @SerializedName("UrlImage1")
        private String urlimage1;
        @SerializedName("Scored")
        private int scored;
        @SerializedName("Sex")
        private int sex;
        @SerializedName("CardTerm")
        private String cardterm;
        @SerializedName("BankAccNumber")
        private String bankaccnumber;
        @SerializedName("Address")
        private String address;
        @SerializedName("IdNumber")
        private String idnumber;
        @SerializedName("DateOfBirth")
        private String dateofbirth;
        @SerializedName("Fullname")
        private String fullname;
        @SerializedName("Phone")
        private String phone;
        @SerializedName("Username")
        private String username;
        @SerializedName("UserId")
        private String userid;

        public double getProgress() {
            return progress;
        }

        public void setProgress(double progress) {
            this.progress = progress;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
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

        public int getScored() {
            return scored;
        }

        public void setScored(int scored) {
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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


//    @Expose
//    @SerializedName("Profile")
//    private ProfileEntity profile;
//    @Expose
//    @SerializedName("Message")
//    private String message;
//    @Expose
//    @SerializedName("StatusCode")
//    private int statuscode;
//
//    public ProfileEntity getProfile() {
//        return profile;
//    }
//
//    public void setProfile(ProfileEntity profile) {
//        this.profile = profile;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public int getStatuscode() {
//        return statuscode;
//    }
//
//    public void setStatuscode(int statuscode) {
//        this.statuscode = statuscode;
//    }
//
//    public static class ProfileEntity {
//        @Expose
//        @SerializedName("UpdatedDate")
//        private int updateddate;
//        @Expose
//        @SerializedName("CreatedDate")
//        private int createddate;
//        @Expose
//        @SerializedName("CardTerm")
//        private String cardterm;
//        @Expose
//        @SerializedName("BankAccNumber")
//        private String bankaccnumber;
//        @Expose
//        @SerializedName("IdImage1")
//        private String idimage1;
//        @Expose
//        @SerializedName("Address")
//        private String address;
//        @Expose
//        @SerializedName("IdNumber")
//        private String idnumber;
//        @Expose
//        @SerializedName("DateOfBirth")
//        private String dateofbirth;
//        @Expose
//        @SerializedName("Fullname")
//        private String fullname;
//        @Expose
//        @SerializedName("Username")
//        private String username;
//        @Expose
//        @SerializedName("Id")
//        private int id;
//
//        public int getUpdateddate() {
//            return updateddate;
//        }
//
//        public void setUpdateddate(int updateddate) {
//            this.updateddate = updateddate;
//        }
//
//        public int getCreateddate() {
//            return createddate;
//        }
//
//        public void setCreateddate(int createddate) {
//            this.createddate = createddate;
//        }
//
//        public String getCardterm() {
//            return cardterm;
//        }
//
//        public void setCardterm(String cardterm) {
//            this.cardterm = cardterm;
//        }
//
//        public String getBankaccnumber() {
//            return bankaccnumber;
//        }
//
//        public void setBankaccnumber(String bankaccnumber) {
//            this.bankaccnumber = bankaccnumber;
//        }
//
//        public String getIdimage1() {
//            return idimage1;
//        }
//
//        public void setIdimage1(String idimage1) {
//            this.idimage1 = idimage1;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//        public void setAddress(String address) {
//            this.address = address;
//        }
//
//        public String getIdnumber() {
//            return idnumber;
//        }
//
//        public void setIdnumber(String idnumber) {
//            this.idnumber = idnumber;
//        }
//
//        public String getDateofbirth() {
//            return dateofbirth;
//        }
//
//        public void setDateofbirth(String dateofbirth) {
//            this.dateofbirth = dateofbirth;
//        }
//
//        public String getFullname() {
//            return fullname;
//        }
//
//        public void setFullname(String fullname) {
//            this.fullname = fullname;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//    }
}

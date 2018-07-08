package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity;

import com.google.gson.annotations.SerializedName;

public class ProfileRequest {
    @SerializedName("username")
    private String username;
    @SerializedName("sex")
    private int sex;
    @SerializedName("id_card_image")
    private int idCardImage;
    @SerializedName("card_term")
    private String cardTerm;
    @SerializedName("bank_acc_number")
    private String bankAccNumber;
    @SerializedName("id_image_2")
    private int idImage2;
    @SerializedName("id_image_1")
    private int idImage1;
    @SerializedName("address")
    private String address;
    @SerializedName("id_number")
    private String idNumber;
    @SerializedName("date_of_birth")
    private String dateOfBirth;
    @SerializedName("fullname")
    private String fullname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getIdCardImage() {
        return idCardImage;
    }

    public void setIdCardImage(int idCardImage) {
        this.idCardImage = idCardImage;
    }

    public String getCardTerm() {
        return cardTerm;
    }

    public void setCardTerm(String cardTerm) {
        this.cardTerm = cardTerm;
    }

    public String getBankAccNumber() {
        return bankAccNumber;
    }

    public void setBankAccNumber(String bankAccNumber) {
        this.bankAccNumber = bankAccNumber;
    }

    public int getIdImage2() {
        return idImage2;
    }

    public void setIdImage2(int idImage2) {
        this.idImage2 = idImage2;
    }

    public int getIdImage1() {
        return idImage1;
    }

    public void setIdImage1(int idImage1) {
        this.idImage1 = idImage1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}

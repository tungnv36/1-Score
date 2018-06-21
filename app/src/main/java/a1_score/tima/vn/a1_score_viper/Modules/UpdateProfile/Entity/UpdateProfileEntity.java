package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity;

import com.google.gson.annotations.SerializedName;

public class UpdateProfileEntity {

    @SerializedName("username")
    private String username;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("date_of_birth")
    private String date_of_birth;
    @SerializedName("id_number")
    private String id_number;
    @SerializedName("address")
    private String address;
    @SerializedName("id_image_1")
    private int id_image_1;
    @SerializedName("id_image_2")
    private int id_image_2;
    @SerializedName("bank_acc_number")
    private String bank_acc_number;
    @SerializedName("card_term")
    private String card_term;
    @SerializedName("id_card_image")
    private int card_image;
    @SerializedName("sex")
    private int sex;

    public UpdateProfileEntity() {

    }

    public UpdateProfileEntity(String username, String fullname, String date_of_birth, String id_number, String address, int id_image_1,
                               int id_image_2, String bank_acc_number, String card_term, int card_image, int sex) {
        this.username = username;
        this.fullname = fullname;
        this.date_of_birth = date_of_birth;
        this.id_number = id_number;
        this.address = address;
        this.id_image_1 = id_image_1;
        this.id_image_2 = id_image_2;
        this.bank_acc_number = bank_acc_number;
        this.card_term = card_term;
        this.card_image = card_image;
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId_image_1() {
        return id_image_1;
    }

    public void setId_image_1(int id_image_1) {
        this.id_image_1 = id_image_1;
    }

    public int getId_image_2() {
        return id_image_2;
    }

    public void setId_image_2(int id_image_2) {
        this.id_image_2 = id_image_2;
    }

    public String getBank_acc_number() {
        return bank_acc_number;
    }

    public void setBank_acc_number(String bank_acc_number) {
        this.bank_acc_number = bank_acc_number;
    }

    public String getCard_term() {
        return card_term;
    }

    public void setCard_term(String card_term) {
        this.card_term = card_term;
    }

    public int getCard_image() {
        return card_image;
    }

    public void setCard_image(int card_image) {
        this.card_image = card_image;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}

package a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity;

import com.google.gson.annotations.SerializedName;

public class FacebookRequest {

    @SerializedName("facebook_email")
    private String facebookEmail;
    @SerializedName("facebook_address")
    private String facebookAddress;
    @SerializedName("facebook_name")
    private String facebookName;
    @SerializedName("facebook_id")
    private String facebookId;
    @SerializedName("username")
    private String username;

    public String getFacebookEmail() {
        return facebookEmail;
    }

    public void setFacebookEmail(String facebookEmail) {
        this.facebookEmail = facebookEmail;
    }

    public String getFacebookAddress() {
        return facebookAddress;
    }

    public void setFacebookAddress(String facebookAddress) {
        this.facebookAddress = facebookAddress;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

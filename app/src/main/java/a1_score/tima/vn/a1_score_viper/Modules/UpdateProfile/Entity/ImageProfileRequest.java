package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity;

import com.google.gson.annotations.SerializedName;

public class ImageProfileRequest {
    @SerializedName("username")
    private String username;
    @SerializedName("type_id")
    private int type_id;
    @SerializedName("base64_image")
    private String base64_image;
    @SerializedName("description")
    private String description;

    public ImageProfileRequest(String username, int type_id, String base64_image, String description) {
        this.username = username;
        this.type_id = type_id;
        this.base64_image = base64_image;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getBase64_image() {
        return base64_image;
    }

    public void setBase64_image(String base64_image) {
        this.base64_image = base64_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

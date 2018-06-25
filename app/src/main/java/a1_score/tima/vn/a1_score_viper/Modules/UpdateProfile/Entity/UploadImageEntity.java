package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity;

import com.google.gson.annotations.SerializedName;

public class UploadImageEntity {
    @SerializedName("username")
    private String username;
    @SerializedName("type")
    private String type;
    @SerializedName("base64_image")
    private String base64_image;

    public UploadImageEntity(String username, String base64_image, String type) {
        this.username = username;
        this.base64_image = base64_image;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBase64_image() {
        return base64_image;
    }

    public void setBase64_image(String base64_image) {
        this.base64_image = base64_image;
    }
}

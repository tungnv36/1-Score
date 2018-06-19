package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity;

import com.google.gson.annotations.SerializedName;

public class HeaderEntity {
    @SerializedName("Content-Type")
    private String ContentType;
    @SerializedName("Accept")
    private String Accept;
    @SerializedName("Authorization")
    private String Authorization;

    public HeaderEntity(String contentType, String accept, String authorization) {
        ContentType = contentType;
        Accept = accept;
        Authorization = authorization;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String contentType) {
        ContentType = contentType;
    }

    public String getAccept() {
        return Accept;
    }

    public void setAccept(String accept) {
        Accept = accept;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }
}

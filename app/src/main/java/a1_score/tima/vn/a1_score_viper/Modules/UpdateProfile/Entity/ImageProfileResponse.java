package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageProfileResponse {
    @Expose
    @SerializedName("Image")
    private ImageEntity image;
    @Expose
    @SerializedName("Message")
    private String message;
    @Expose
    @SerializedName("StatusCode")
    private int statuscode;

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
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

    public static class ImageEntity {
        @Expose
        @SerializedName("ImageType")
        private String imagetype;
        @Expose
        @SerializedName("Url")
        private String url;
        @Expose
        @SerializedName("Id")
        private int id;

        public String getImagetype() {
            return imagetype;
        }

        public void setImagetype(String imagetype) {
            this.imagetype = imagetype;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

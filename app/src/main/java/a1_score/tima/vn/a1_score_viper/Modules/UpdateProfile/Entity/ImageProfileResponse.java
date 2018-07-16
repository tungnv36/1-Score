package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageProfileResponse {
    @SerializedName("Image")
    private ImageEntity image;
    @SerializedName("Message")
    private String message;
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
        @SerializedName("Username")
        private String Username;
        @SerializedName("TypeId")
        private String TypeId;
        @SerializedName("Id")
        private int Id;
        @SerializedName("Description")
        private String Description;
        @SerializedName("ImageType")
        private String ImageType;
        @SerializedName("Url")
        private String Url;

        public String getUsername() {
            return Username;
        }

        public void setUsername(String username) {
            Username = username;
        }

        public String getTypeId() {
            return TypeId;
        }

        public void setTypeId(String typeId) {
            TypeId = typeId;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getImageType() {
            return ImageType;
        }

        public void setImageType(String imageType) {
            ImageType = imageType;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            Url = url;
        }
    }
}

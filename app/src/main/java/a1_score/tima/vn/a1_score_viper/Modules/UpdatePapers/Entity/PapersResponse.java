package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PapersResponse {

    @SerializedName("ImageType")
    private List<ImageTypesEntity> imagetypes;
    @SerializedName("StatusCode")
    private int statuscode;
    @SerializedName("Message")
    private String message;

    public List<ImageTypesEntity> getImagetypes() {
        return imagetypes;
    }

    public void setImagetypes(List<ImageTypesEntity> imagetypes) {
        this.imagetypes = imagetypes;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ImageTypesEntity {
        @SerializedName("ImageSize")
        private int imagesize;
        @SerializedName("TypeName")
        private String typename;
        @SerializedName("TypeId")
        private int typeid;

        public int getImagesize() {
            return imagesize;
        }

        public void setImagesize(int imagesize) {
            this.imagesize = imagesize;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }
    }
}

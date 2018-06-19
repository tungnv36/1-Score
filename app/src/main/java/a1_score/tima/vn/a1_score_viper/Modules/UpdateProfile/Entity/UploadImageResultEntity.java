package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity;

public class UploadImageResultEntity {
    private int StatusCode;
    private String Message;
    private ImageEntity Image;

    public UploadImageResultEntity(int statusCode, String message, ImageEntity image) {
        StatusCode = statusCode;
        Message = message;
        Image = image;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public ImageEntity getImage() {
        return Image;
    }

    public void setImage(ImageEntity image) {
        Image = image;
    }
}

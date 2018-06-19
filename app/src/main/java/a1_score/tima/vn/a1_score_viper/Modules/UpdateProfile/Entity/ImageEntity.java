package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity;

public class ImageEntity {

    private String Username;
    private String ImageUrl;
    private String ImageType;
    private long CreatedTime;

    public ImageEntity(String username, String imageUrl, String imageType, long createdTime) {
        Username = username;
        ImageUrl = imageUrl;
        ImageType = imageType;
        CreatedTime = createdTime;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getImageType() {
        return ImageType;
    }

    public void setImageType(String imageType) {
        ImageType = imageType;
    }

    public long getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(long createdTime) {
        CreatedTime = createdTime;
    }

}

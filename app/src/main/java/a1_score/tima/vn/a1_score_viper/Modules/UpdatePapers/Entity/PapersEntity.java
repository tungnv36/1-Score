package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class PapersEntity {
    @SerializedName("Username")
    private String username;
    @SerializedName("ImageSize")
    private int imagesize;
    @SerializedName("TypeName")
    private String typename;
    @SerializedName("TypeId")
    private int typeid;
    @SerializedName("Done")
    private boolean done;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity;

import android.graphics.Bitmap;

public class PapersEntity {
    private int id;
    private boolean isShow;
    private Bitmap image;
    private String title;
    private int type;//1: ô chụp hình chữ nhật (CMND, Bằng lái, ...) 2: ô chụp hợp đồng

    public PapersEntity(int id, boolean isShow, Bitmap image, String title, int type) {
        this.id = id;
        this.isShow = isShow;
        this.image = image;
        this.title = title;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

package a1_score.tima.vn.a1_score_viper.Modules.Profile.Entity;

public class MenuEntity {
    private int icon;
    private String title;
    private String subTitle;
    private int lastProgress;
    private int progress;
    private boolean isShowSubTitle;

    public MenuEntity(int icon, String title, String subTitle, int lastProgress, int progress, boolean isShowSubTitle) {
        this.icon = icon;
        this.title = title;
        this.subTitle = subTitle;
        this.lastProgress = lastProgress;
        this.progress = progress;
        this.isShowSubTitle = isShowSubTitle;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getLastProgress() {
        return lastProgress;
    }

    public void setLastProgress(int lastProgress) {
        this.lastProgress = lastProgress;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isShowSubTitle() {
        return isShowSubTitle;
    }

    public void setShowSubTitle(boolean showSubTitle) {
        isShowSubTitle = showSubTitle;
    }
}

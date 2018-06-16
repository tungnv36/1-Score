package a1_score.tima.vn.a1_score_viper.Modules.Setting.Entity;

public class SettingEntity {
    private int icon;
    private String title;

    public SettingEntity(int icon, String title) {
        this.icon = icon;
        this.title = title;
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
}

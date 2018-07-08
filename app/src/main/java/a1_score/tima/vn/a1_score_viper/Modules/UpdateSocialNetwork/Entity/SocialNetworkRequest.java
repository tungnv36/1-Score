package a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity;

public class SocialNetworkRequest {

    private int id;
    private int icon;
    private String name;
    private boolean isOn;
    private int textColor;

    public SocialNetworkRequest(int id, int icon, String name, boolean isOn, int textColor) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.isOn = isOn;
        this.textColor = textColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}

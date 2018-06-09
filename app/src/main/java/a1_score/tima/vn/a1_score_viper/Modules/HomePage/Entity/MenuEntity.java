package a1_score.tima.vn.a1_score_viper.Modules.HomePage.Entity;

public class MenuEntity {

    private long id;
    private int icon;
    private String title;
    private String score;

    public MenuEntity(long id, int icon, String title, String score) {
        this.id = id;
        this.icon = icon;
        this.title= title;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

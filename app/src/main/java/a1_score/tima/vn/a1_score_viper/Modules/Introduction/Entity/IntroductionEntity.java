package a1_score.tima.vn.a1_score_viper.Modules.Introduction.Entity;

public class IntroductionEntity {
    private boolean isFirstTime;

    public IntroductionEntity(boolean isFirstTime) {
        this.isFirstTime = isFirstTime;
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public void setFirstTime(boolean firstTime) {
        isFirstTime = firstTime;
    }
}

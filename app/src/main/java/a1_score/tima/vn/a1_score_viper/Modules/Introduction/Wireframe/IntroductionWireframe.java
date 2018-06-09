package a1_score.tima.vn.a1_score_viper.Modules.Introduction.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Interface.IntroductionInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Main.View.MainView;

public class IntroductionWireframe implements IntroductionInterface.Wireframe {

    @Override
    public void goToMainView(Activity activity) {
        Intent intent = new Intent(activity, MainView.class);
        activity.startActivity(intent);
    }

}

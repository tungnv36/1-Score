package a1_score.tima.vn.a1_score_viper.Modules.Login.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Modules.HomePage.View.HomePageView;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;

public class LoginWireframe implements LoginInterface.Wireframe {

    @Override
    public void gotToHomePage(Activity activity) {
        Intent intent = new Intent(activity, HomePageView.class);
        activity.startActivity(intent);
    }

}

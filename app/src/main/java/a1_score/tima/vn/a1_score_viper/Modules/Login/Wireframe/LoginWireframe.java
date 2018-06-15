package a1_score.tima.vn.a1_score_viper.Modules.Login.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.View.ForgotPasswordView;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.View.HomePageView;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;

public class LoginWireframe implements LoginInterface.Wireframe {

    @Override
    public void gotToHomePage(Activity activity) {
        Intent intent = new Intent(activity, HomePageView.class);
        activity.startActivity(intent);
    }

    @Override
    public void gotToForgotPassword(Activity activity) {
        Intent intent = new Intent(activity, ForgotPasswordView.class);
        activity.startActivity(intent);
    }

}

package a1_score.tima.vn.a1_score_viper.Modules.Register.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Modules.HomePage.View.HomePageView;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Login.View.LoginView;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Interface.RegisterInterface;

public class RegisterWireframe implements RegisterInterface.Wireframe {

    @Override
    public void gotToLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginView.class);
        activity.startActivity(intent);
    }

}

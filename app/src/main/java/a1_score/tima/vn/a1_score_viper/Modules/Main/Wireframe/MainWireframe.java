package a1_score.tima.vn.a1_score_viper.Modules.Main.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Modules.Login.View.LoginView;
import a1_score.tima.vn.a1_score_viper.Modules.Main.Interface.MainInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Register.View.RegisterView;

public class MainWireframe implements MainInterface.Wireframe {

    @Override
    public void goToLoginView(Activity activity) {
        Intent intent = new Intent(activity, LoginView.class);
        activity.startActivity(intent);
    }

    @Override
    public void goToRegisterView(Activity activity) {
        Intent intent = new Intent(activity, RegisterView.class);
        activity.startActivity(intent);
    }

}

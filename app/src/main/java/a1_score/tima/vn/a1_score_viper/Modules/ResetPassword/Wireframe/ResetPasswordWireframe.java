package a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Interface.ResetPasswordInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Login.View.LoginView;

public class ResetPasswordWireframe implements ResetPasswordInterface.Wireframe {

    @Override
    public void goToLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginView.class);
        activity.startActivity(intent);
    }

}

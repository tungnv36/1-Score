package a1_score.tima.vn.a1_score_viper.Modules.Otp.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.View.ChangePasswordForgotView;
import a1_score.tima.vn.a1_score_viper.Modules.Login.View.LoginView;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Interface.OtpInterface;

public class OtpWireframe implements OtpInterface.Wireframe {

    @Override
    public void goToLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginView.class);
        activity.startActivity(intent);
    }

    @Override
    public void goToChangePass(Activity activity, String phoneNumber, String token) {
        Intent intent = new Intent(activity, ChangePasswordForgotView.class);
        intent.putExtra("PHONE_NUMBER", phoneNumber);
        intent.putExtra("TOKEN", token);
        activity.startActivity(intent);
    }

}

package a1_score.tima.vn.a1_score_viper.Modules.Login.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.View.ForgotPasswordView;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.View.HomePageView;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.View.OtpView;

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

    @Override
    public void goToOtp(Activity activity, String phoneNumber, String msg) {
        Intent intent = new Intent(activity, OtpView.class);
        intent.putExtra("TYPE", 1);//1: Register, 2: Lost pass
        intent.putExtra("PHONE_NUMBER", phoneNumber);
        intent.putExtra("ACTION", Constant.ACTION_CONFIRM_USER);
        intent.putExtra("MSG", msg);
        activity.startActivity(intent);
    }

}

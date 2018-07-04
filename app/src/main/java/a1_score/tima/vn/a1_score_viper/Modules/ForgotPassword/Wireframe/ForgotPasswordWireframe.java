package a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Interface.ForgotPasswordInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.View.OtpView;

public class ForgotPasswordWireframe implements ForgotPasswordInterface.Wireframe {

    @Override
    public void goToOtp(Activity activity, String phoneNumber, String msg) {
        Intent intent = new Intent(activity, OtpView.class);
        intent.putExtra("TYPE", 2);//1: Register, 2: Lost pass
        intent.putExtra("PHONE_NUMBER", phoneNumber);
        intent.putExtra("ACTION", Constant.ACTION_FORGOT_PASSWORD);
        intent.putExtra("MSG", msg);
        activity.startActivity(intent);
    }

}

package a1_score.tima.vn.a1_score_viper.Modules.Register.Wireframe;

import android.app.Activity;
import android.content.Intent;

import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.View.OtpView;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Interface.RegisterInterface;
import a1_score.tima.vn.a1_score_viper.R;

public class RegisterWireframe implements RegisterInterface.Wireframe {

    @Override
    public void gotToOTP(Activity activity, String phoneNumber) {
        Intent intent = new Intent(activity, OtpView.class);
        intent.putExtra("TYPE", 1);//1: Register, 2: Lost pass
        intent.putExtra("PHONE_NUMBER", phoneNumber);
        intent.putExtra("ACTION", Constant.ACTION_CONFIRM_USER);
        intent.putExtra("MSG", activity.getString(R.string.des_otp));
        activity.startActivity(intent);
    }

}

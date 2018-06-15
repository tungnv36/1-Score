package a1_score.tima.vn.a1_score_viper.Modules.Otp.Interface;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity.OtpEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity.OtpResultEntity;

public interface OtpInterface {

    interface View {
        void compareOtpSuccess(String msg);
        void compareOtpFailed(String err);
    }

    interface Presenter {
        void compareOtp(String phoneNumber, String otp, int type);
        void onDestroy();
    }

    interface InteractorInput {
        void compareOtp(String phoneNumber, String otp, int type);
        void unRegister();
    }

    interface InteractorOutput {
        void compareOtpSuccess();
        void compareOtpForgotPassSuccess(String phoneNumber, String token);
        void compareOtpFailed(String err);
    }

    interface Wireframe {
        void goToLogin(Activity activity);
        void goToChangePass(Activity activity, String phoneNumber, String token);
    }

    interface DataStore {
        void compareOtp(final OnResponse<String, OtpResultEntity> m_Response, OtpEntity otpEntity);
    }

}

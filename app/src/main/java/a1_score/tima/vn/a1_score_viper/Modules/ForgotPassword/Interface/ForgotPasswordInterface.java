package a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Interface;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity.ForgotPasswordEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity.ForgotPasswordResultEntity;

public interface ForgotPasswordInterface {

    interface View {
        void forgotPasswordSuccess(String msg);
        void forgotPasswordFailed(String err);
    }

    interface Presenter {
        void forgotPassword(String phone);
        void onDestroy();
    }

    interface InteractorInput {
        void forgotPassword(String phone);
        void unRegister();
    }

    interface InteractorOutput {
        void forgotPasswordSuccess(String msg, String phone);
        void forgotPasswordFailed(String err);
    }

    interface Wireframe {
        void goToOtp(Activity activity, String phoneNumber, String msg);
    }

    interface DataStore {
        void sendOtp(final OnResponse<String, ForgotPasswordResultEntity> m_Response, ForgotPasswordEntity forgotPasswordEntity);
    }

}

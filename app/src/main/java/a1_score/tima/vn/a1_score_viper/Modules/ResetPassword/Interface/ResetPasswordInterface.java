package a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Interface;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Entity.ForgotPasswordRequest;
import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Entity.ForgotPasswordResponse;

public interface ResetPasswordInterface {

    interface View {
        void changePassSuccess(String msg);
        void changePassFailed(String err);
    }

    interface Presenter {
        void changePass(String userName, String newPass, String rePass, String token);
        void onDestroy();
    }

    interface InteractorInput {
        void changePass(String userName, String newPass, String rePass, String token);
        void unRegister();
    }

    interface InteractorOutput {
        void changePassSuccess(String msg);
        void changePassFailed(String err);
    }

    interface Wireframe {
        void goToLogin(Activity activity);
    }

    interface DataStore {
        void callChangePass(final OnResponse<String, ForgotPasswordResponse> m_Response, String Token, ForgotPasswordRequest forgotPasswordRequest);
    }

}

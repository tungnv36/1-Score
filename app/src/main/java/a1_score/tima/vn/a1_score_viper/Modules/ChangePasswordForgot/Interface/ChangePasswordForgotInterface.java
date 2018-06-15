package a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Interface;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Entity.ChangePasswordForgotEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Entity.ChangePasswordForgotResultEntity;

public interface ChangePasswordForgotInterface {

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
        void callChangePass(final OnResponse<String, ChangePasswordForgotResultEntity> m_Response, String Token, ChangePasswordForgotEntity changePasswordForgotEntity);
    }

}

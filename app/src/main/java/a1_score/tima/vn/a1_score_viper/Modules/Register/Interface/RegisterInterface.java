package a1_score.tima.vn.a1_score_viper.Modules.Register.Interface;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.EditText;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterResultEntity;

public interface RegisterInterface {
    //view
    interface View {
        void EdittextEmpty(ProgressDialog mProgress, int type, String error);
        void registerFailed(ProgressDialog mProgress, String error);
        void onDestroy();
    }
    //presenter
    interface Presenter {
        void register(ProgressDialog mProgress, String username, String password, String confirmPassword, String fullName);
        void onDestroy();
    }
    //interactor
    interface InteractorInput {
        void register(ProgressDialog mProgress, String username, String password, String confirmPassword, String fullName);
        void unRegister();
    }

    interface InteractorOutput {
        void EdittextEmpty(ProgressDialog mProgress, int type, String error);
        void registerSuccess(ProgressDialog mProgress, String msg, String phoneNumber);
        void registerFailed(ProgressDialog mProgress, String error);
    }
    //wireframe
    interface Wireframe {
        void gotToOTP(Activity activity, String phoneNumber);
    }
    //DataStore
    interface DataStore {
        void callRegister(final OnResponse<String, RegisterResultEntity> m_Response, RegisterEntity registerEntity);
    }
}

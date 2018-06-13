package a1_score.tima.vn.a1_score_viper.Modules.Register.Interface;

import android.app.Activity;
import android.content.Context;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterResultEntity;

public interface RegisterInterface {
    //view
    interface View {
        void usernameEmpty(String error);
        void passwordEmpty(String error);
        void confirmPasswordEmpty(String error);
        void EmailEmpty(String error);
        void registerSuccess(String msg);
        void registerFailed(String error);
        void onDestroy();
    }
    //presenter
    interface Presenter {
        void register(String username, String password, String confirmPassword, String email);
        void onDestroy();
    }
    //interactor
    interface InteractorInput {
        void register(String username, String password, String confirmPassword, String email);
        void unRegister();
    }

    interface InteractorOutput {
        void registerSuccess(String msg);
        void registerFailed(String error);
        void usernameEmpty(String error);
        void passwordEmpty(String error);
        void confirmPasswordEmpty(String error);
        void EmailEmpty(String error);
    }
    //wireframe
    interface Wireframe {
        void gotToLogin(Activity activity);
    }
    //DataStore
    interface DataStore {
        void callRegister(final OnResponse<String, RegisterResultEntity> m_Response, RegisterEntity registerEntity);
    }
}

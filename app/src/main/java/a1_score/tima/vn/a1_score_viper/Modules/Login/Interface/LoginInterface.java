package a1_score.tima.vn.a1_score_viper.Modules.Login.Interface;

import android.app.Activity;
import android.content.Context;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResultEntity;

public interface LoginInterface {

    //view
    interface View {
        void changeHeightBanner(int height, int margin);
        void usernameEmpty(String error);
        void passwordEmpty(String error);
        void loginFailed(String error);
        void onDestroy();
    }
    //presenter
    interface Presenter {
        void changeHeightBanner(int height, int margin);
        void login(String username, String password);
        void goToForgotPassword();
        void onDestroy();
    }
    //interactor
    interface InteractorInput {
        void changeHeightBanner(int height, int margin);
        void login(String username, String password);
        void goToForgotPassword();
        void unRegister();
    }

    interface InteractorOutput {
        void changeHeightBannerOutput(int height, int margin);
        void goToForgotPasswordOutput();
        void loginSuccess();
        void loginFailed(String error);
        void usernameEmpty(String error);
        void passwordEmpty(String error);
    }
    //wireframe
    interface Wireframe {
        void gotToHomePage(Activity activity);
        void gotToForgotPassword(Activity activity);
    }
    //DataStore
    interface DataStore {
        void callLogin(final OnResponse<String, LoginResultEntity> m_Response, LoginEntity loginEntity);
        void setUser(Context context, LoginResultEntity user);
    }

}

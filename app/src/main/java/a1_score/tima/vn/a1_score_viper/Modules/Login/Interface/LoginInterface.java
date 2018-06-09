package a1_score.tima.vn.a1_score_viper.Modules.Login.Interface;

import android.app.Activity;

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
        void onDestroy();
    }
    //interactor
    interface InteractorInput {
        void changeHeightBanner(int height, int margin);
        void login(String username, String password);
        void unRegister();
    }

    interface InteractorOutput {
        void changeHeightBannerOutput(int height, int margin);
        void loginSuccess();
        void loginFailed(String error);
        void usernameEmpty(String error);
        void passwordEmpty(String error);
    }
    //wireframe
    interface Wireframe {
        void gotToHomePage(Activity activity);
    }
    //DataStore
    interface DataStore {
        String callLogin(String username, String password);
    }

}

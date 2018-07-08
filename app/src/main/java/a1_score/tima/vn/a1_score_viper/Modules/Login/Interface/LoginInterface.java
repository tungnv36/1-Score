package a1_score.tima.vn.a1_score_viper.Modules.Login.Interface;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginRequest;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;

public interface LoginInterface {

    //view
    interface View {
        void changeHeightBanner(int height, int margin);
        void usernameEmpty(String error);
        void passwordEmpty(String error);

        void loginFailed(String error);
        void loginFailedLostOtp(String phoneNumber, String error);

        void onDestroy();
    }
    //presenter
    interface Presenter {
        void createFolder();
        void changeHeightBanner(int height, int margin);
        void login(ProgressDialog mProgress, String username, String password);

        void goToForgotPassword();
        void goToOtp(String phoneNumber, String msg);

        void onDestroy();
    }
    //interactor
    interface InteractorInput {
        void createFolder();
        void changeHeightBanner(int height, int margin);
        void login(ProgressDialog mProgress, String username, String password);

        void goToForgotPassword();
        void goToOtp(String phoneNumber, String msg);

        void unRegister();
    }

    interface InteractorOutput {
        void changeHeightBannerOutput(int height, int margin);
        void loginSuccess(ProgressDialog mProgress);
        void loginFailed(String error);
        void loginFailedLostOtp(String phoneNumber, String error);

        void usernameEmpty(String error);
        void passwordEmpty(String error);

        void goToForgotPasswordOutput();
        void goToOtpOutput(String phoneNumber, String msg);
    }
    //wireframe
    interface Wireframe {
        void gotToHomePage(Activity activity);
        void gotToForgotPassword(Activity activity);
        void goToOtp(Activity activity, String phoneNumber, String msg);
    }
    //DataStore
    interface DataStore {
        void callLogin(final OnResponse<String, LoginResponse> m_Response, LoginRequest loginRequest);
        void setUser(Context context, LoginResponse user);
        void saveUser(LoginResponse user);
        void saveImageToLocal(String fineName, Bitmap bmp);
        void saveImageToDB(ImageProfileResponse imageProfileResponse, String imageName, String username, String type);
        int getImageID(String username, String type);
        void createFolder();
    }

}

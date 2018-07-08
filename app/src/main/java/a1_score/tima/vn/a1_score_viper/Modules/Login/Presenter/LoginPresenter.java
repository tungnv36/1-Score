package a1_score.tima.vn.a1_score_viper.Modules.Login.Presenter;

import android.app.Activity;
import android.app.ProgressDialog;

import a1_score.tima.vn.a1_score_viper.Modules.Login.Interactor.LoginInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Wireframe.LoginWireframe;

public class LoginPresenter implements LoginInterface.Presenter, LoginInterface.InteractorOutput {

    private LoginInterface.View mView;
    private LoginInterface.InteractorInput mInteractorInput;
    private LoginInterface.Wireframe mWireframe;

    public LoginPresenter(LoginInterface.View view) {
        mView = view;
        mInteractorInput = new LoginInteractor(view, this);
        mWireframe = new LoginWireframe();
    }

    @Override
    public void createFolder() {
        mInteractorInput.createFolder();
    }

    @Override
    public void changeHeightBanner(int height, int margin) {
        mInteractorInput.changeHeightBanner(height, margin);
    }

    @Override
    public void login(ProgressDialog mProgress, String username, String password) {
        mInteractorInput.login(mProgress, username, password);
    }

    @Override
    public void goToForgotPassword() {
        mInteractorInput.goToForgotPassword();
    }

    @Override
    public void goToOtp(String phoneNumber, String msg) {
        mInteractorInput.goToOtp(phoneNumber, msg);
    }

    @Override
    public void onDestroy() {
        mInteractorInput.unRegister();
        mInteractorInput = null;
        mWireframe = null;
    }

    @Override
    public void changeHeightBannerOutput(int height, int margin) {
        mView.changeHeightBanner(height, margin);
    }

    @Override
    public void goToForgotPasswordOutput() {
        mWireframe.gotToForgotPassword((Activity)mView);
    }

    @Override
    public void loginSuccess(ProgressDialog mProgress) {
        mProgress.dismiss();
        mWireframe.gotToHomePage((Activity) mView);
    }

    @Override
    public void loginFailed(String error) {
        mView.loginFailed(error);
    }

    @Override
    public void loginFailedLostOtp(String phoneNumber, String error) {
        mView.loginFailedLostOtp(phoneNumber, error);
    }

    @Override
    public void usernameEmpty(String error) {
        mView.usernameEmpty(error);
    }

    @Override
    public void passwordEmpty(String error) {
        mView.passwordEmpty(error);
    }

    @Override
    public void goToOtpOutput(String phoneNumber, String msg) {
        mWireframe.goToOtp((Activity)mView, phoneNumber, msg);
    }

}

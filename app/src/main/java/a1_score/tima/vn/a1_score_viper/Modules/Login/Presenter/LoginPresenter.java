package a1_score.tima.vn.a1_score_viper.Modules.Login.Presenter;

import android.app.Activity;
import android.app.ProgressDialog;

import a1_score.tima.vn.a1_score_viper.Modules.Login.Interactor.LoginInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Wireframe.LoginWireframe;

public class LoginPresenter implements LoginInterface.Presenter, LoginInterface.InteractorOutput {

    private LoginInterface.View view;
    private LoginInterface.InteractorInput interactorInput;
    private LoginInterface.Wireframe wireframe;

    public LoginPresenter(LoginInterface.View view) {
        this.view = view;
        interactorInput = new LoginInteractor(view, this);
        wireframe = new LoginWireframe();
    }

    @Override
    public void createFolder() {
        interactorInput.createFolder();
    }

    @Override
    public void changeHeightBanner(int height, int margin) {
        interactorInput.changeHeightBanner(height, margin);
    }

    @Override
    public void login(ProgressDialog mProgress, String username, String password) {
        interactorInput.login(mProgress, username, password);
    }

    @Override
    public void goToForgotPassword() {
        interactorInput.goToForgotPassword();
    }

    @Override
    public void onDestroy() {
        interactorInput.unRegister();
        interactorInput = null;
        wireframe = null;
    }

    @Override
    public void changeHeightBannerOutput(int height, int margin) {
        view.changeHeightBanner(height, margin);
    }

    @Override
    public void goToForgotPasswordOutput() {
        wireframe.gotToForgotPassword((Activity)view);
    }

    @Override
    public void loginSuccess(ProgressDialog mProgress) {
        mProgress.dismiss();
        wireframe.gotToHomePage((Activity) view);
    }

    @Override
    public void loginFailed(String error) {
        view.loginFailed(error);
    }

    @Override
    public void usernameEmpty(String error) {
        view.usernameEmpty(error);
    }

    @Override
    public void passwordEmpty(String error) {
        view.passwordEmpty(error);
    }

}

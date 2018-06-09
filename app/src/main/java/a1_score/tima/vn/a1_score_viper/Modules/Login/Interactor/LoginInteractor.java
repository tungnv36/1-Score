package a1_score.tima.vn.a1_score_viper.Modules.Login.Interactor;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.Login.DataStore.LoginDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import a1_score.tima.vn.a1_score_viper.R;

public class LoginInteractor implements LoginInterface.InteractorInput {

    private LoginInterface.InteractorOutput interactorOutput;
    private LoginInterface.DataStore dataStore;
    private LoginInterface.View view;

    public LoginInteractor(LoginInterface.View view, LoginInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        dataStore = new LoginDataStore(view);
        this.view = view;
    }

    @Override
    public void changeHeightBanner(int height, int margin) {
        interactorOutput.changeHeightBannerOutput(height, margin);
    }

    @Override
    public void login(String username, String password) {
        if(username.isEmpty()) {
            interactorOutput.usernameEmpty(((Activity)view).getString(R.string.err_user_empty));
            return;
        }
        if(password.isEmpty()) {
            interactorOutput.passwordEmpty(((Activity)view).getString(R.string.err_pass_empty));
            return;
        }
        String result = dataStore.callLogin(username, password);
        if(result.equals(((Activity)view).getString(R.string.login_success))) {
            interactorOutput.loginSuccess();
        } else {
            interactorOutput.loginFailed(result);
        }
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
        dataStore = null;
    }

}

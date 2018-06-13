package a1_score.tima.vn.a1_score_viper.Modules.Login.Interactor;

import android.app.Activity;
import android.content.Context;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Login.DataStore.LoginDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import a1_score.tima.vn.a1_score_viper.R;

public class LoginInteractor implements LoginInterface.InteractorInput {

    private LoginInterface.InteractorOutput interactorOutput;
    private LoginInterface.DataStore dataStore;
    private LoginInterface.View view;

    public LoginInteractor(LoginInterface.View view, LoginInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        dataStore = LoginDataStore.getInstance(view);
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
        LoginEntity loginEntity = new LoginEntity(username, password);
        dataStore.callLogin(new OnResponse<String, LoginResultEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, LoginResultEntity extraData) {
                if(extraData == null || extraData.getStatusCode() != 200) {
                    interactorOutput.loginFailed(rs);
                } else {
                    dataStore.setUser((Context) view, extraData);
                    interactorOutput.loginSuccess();
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                interactorOutput.loginFailed(message);
            }
        }, loginEntity);
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
        dataStore = null;
    }

}

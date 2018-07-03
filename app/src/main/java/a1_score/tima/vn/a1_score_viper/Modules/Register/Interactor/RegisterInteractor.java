package a1_score.tima.vn.a1_score_viper.Modules.Register.Interactor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Register.DataStore.RegisterDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Interface.RegisterInterface;
import a1_score.tima.vn.a1_score_viper.R;

public class RegisterInteractor implements RegisterInterface.InteractorInput {

    private RegisterInterface.InteractorOutput interactorOutput;
    private RegisterInterface.DataStore dataStore;
    private RegisterInterface.View view;

    public RegisterInteractor(RegisterInterface.View view, RegisterInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        this.view = view;
        dataStore = RegisterDataStore.getInstance(view);
    }

    @Override
    public void register(final ProgressDialog mProgress, final String username, String password, String confirmPassword, String fullName) {
        if(username.isEmpty()) {
            interactorOutput.EdittextEmpty(mProgress, 0, ((Activity)view).getString(R.string.err_user_empty));
            return;
        }
        if(password.isEmpty()) {
            interactorOutput.EdittextEmpty(mProgress, 1, ((Activity)view).getString(R.string.err_pass_empty));
            return;
        }
        if(confirmPassword.isEmpty()) {
            interactorOutput.EdittextEmpty(mProgress, 2, ((Activity)view).getString(R.string.err_repass_empty));
            return;
        }
        if(fullName.isEmpty()) {
            interactorOutput.EdittextEmpty(mProgress, 3, ((Activity)view).getString(R.string.err_fullname_empty));
            return;
        }
        RegisterEntity registerEntity = new RegisterEntity(username, password, confirmPassword, fullName);
        dataStore.callRegister(new OnResponse<String, RegisterResultEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, RegisterResultEntity extraData) {
                if(extraData == null || extraData.getStatusCode() != 200) {
                    interactorOutput.registerFailed(mProgress, rs);
                } else {
                    interactorOutput.registerSuccess(mProgress, "Đăng ký thành công!", username);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                interactorOutput.registerFailed(mProgress, message);
            }
        }, registerEntity);
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
        dataStore = null;
    }
}

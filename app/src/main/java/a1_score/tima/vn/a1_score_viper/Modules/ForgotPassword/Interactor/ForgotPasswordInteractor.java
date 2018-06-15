package a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Interactor;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.DataStore.ForgotPasswordDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity.ForgotPasswordEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity.ForgotPasswordResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Interface.ForgotPasswordInterface;
import a1_score.tima.vn.a1_score_viper.R;

public class ForgotPasswordInteractor implements ForgotPasswordInterface.InteractorInput {

    private ForgotPasswordInterface.InteractorOutput interactorOutput;
    private ForgotPasswordInterface.View view;
    private ForgotPasswordInterface.DataStore dataStore;

    public ForgotPasswordInteractor(ForgotPasswordInterface.View view, ForgotPasswordInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        this.view = view;
        dataStore = ForgotPasswordDataStore.getInstance(view);
    }

    @Override
    public void forgotPassword(final String phone) {
        if(phone.isEmpty()) {
            interactorOutput.forgotPasswordFailed(((Activity)view).getString(R.string.err_phone_empty));
            return;
        }
        ForgotPasswordEntity forgotPasswordEntity = new ForgotPasswordEntity(phone);
        dataStore.sendOtp(new OnResponse<String, ForgotPasswordResultEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, ForgotPasswordResultEntity extraData) {
                if(extraData == null || extraData.getStatusCode() != 200) {
                    interactorOutput.forgotPasswordFailed(rs);
                } else {
                    interactorOutput.forgotPasswordSuccess(extraData.getMessage(), phone);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                interactorOutput.forgotPasswordFailed(message);
            }
        }, forgotPasswordEntity);
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
    }
}

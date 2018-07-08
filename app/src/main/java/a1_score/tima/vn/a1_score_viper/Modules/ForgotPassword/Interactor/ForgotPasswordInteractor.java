package a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Interactor;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.DataStore.ForgotPasswordDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity.ForgotPasswordRequest;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity.ForgotPasswordResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Interface.ForgotPasswordInterface;
import a1_score.tima.vn.a1_score_viper.R;

public class ForgotPasswordInteractor implements ForgotPasswordInterface.InteractorInput {

    private ForgotPasswordInterface.InteractorOutput mInteractorOutput;
    private ForgotPasswordInterface.View mView;
    private ForgotPasswordInterface.DataStore mDataStore;

    public ForgotPasswordInteractor(ForgotPasswordInterface.View view, ForgotPasswordInterface.InteractorOutput mInteractorOutput) {
        this.mInteractorOutput = mInteractorOutput;
        this.mView = view;
        mDataStore = ForgotPasswordDataStore.getInstance(view);
    }

    @Override
    public void forgotPassword(final String phone) {
        if(phone.isEmpty()) {
            mInteractorOutput.forgotPasswordFailed(((Activity)mView).getString(R.string.err_phone_empty));
            return;
        }
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest(phone);
        mDataStore.sendOtp(new OnResponse<String, ForgotPasswordResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, ForgotPasswordResponse extraData) {
                if(extraData == null || extraData.getStatusCode() != 200) {
                    mInteractorOutput.forgotPasswordFailed(rs);
                } else {
                    mInteractorOutput.forgotPasswordSuccess(extraData.getMessage(), phone);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mInteractorOutput.forgotPasswordFailed(message);
            }
        }, forgotPasswordRequest);
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
    }
}

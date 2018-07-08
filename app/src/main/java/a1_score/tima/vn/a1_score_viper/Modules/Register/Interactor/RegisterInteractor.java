package a1_score.tima.vn.a1_score_viper.Modules.Register.Interactor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Register.DataStore.RegisterDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterRequest;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Interface.RegisterInterface;
import a1_score.tima.vn.a1_score_viper.R;

public class RegisterInteractor implements RegisterInterface.InteractorInput {

    private RegisterInterface.InteractorOutput mInteractorOutput;
    private RegisterInterface.DataStore mDataStore;
    private RegisterInterface.View mView;

    public RegisterInteractor(RegisterInterface.View view, RegisterInterface.InteractorOutput mInteractorOutput) {
        this.mInteractorOutput = mInteractorOutput;
        mView = view;
        mDataStore = RegisterDataStore.getInstance(view);
    }

    @Override
    public void register(final ProgressDialog mProgress, final String username, String password, String confirmPassword, String fullName) {
        if(!Commons.isNetworkConnected((Context)mView)) {
            mInteractorOutput.registerFailed(mProgress, ((Activity)mView).getString(R.string.err_internet_connection));
            return;
        }
        if(username.isEmpty()) {
            mInteractorOutput.EdittextEmpty(mProgress, 0, ((Activity)mView).getString(R.string.err_user_empty));
            return;
        }
        if(password.isEmpty()) {
            mInteractorOutput.EdittextEmpty(mProgress, 1, ((Activity)mView).getString(R.string.err_pass_empty));
            return;
        }
        if(confirmPassword.isEmpty()) {
            mInteractorOutput.EdittextEmpty(mProgress, 2, ((Activity)mView).getString(R.string.err_repass_empty));
            return;
        }
        if(fullName.isEmpty()) {
            mInteractorOutput.EdittextEmpty(mProgress, 3, ((Activity)mView).getString(R.string.err_fullname_empty));
            return;
        }
        RegisterRequest registerRequest = new RegisterRequest(username, password, confirmPassword, fullName);
        mDataStore.callRegister(new OnResponse<String, RegisterResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, RegisterResponse extraData) {
                if(extraData == null || extraData.getStatusCode() != 200) {
                    mInteractorOutput.registerFailed(mProgress, rs);
                } else {
                    mInteractorOutput.registerSuccess(mProgress, ((Context)mView).getString(R.string.msg_register_success), username);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mInteractorOutput.registerFailed(mProgress, message);
            }
        }, registerRequest);
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
        mDataStore = null;
    }
}

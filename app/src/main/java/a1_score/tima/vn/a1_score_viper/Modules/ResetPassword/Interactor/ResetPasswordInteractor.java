package a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Interactor;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.DataStore.ResetPasswordDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Entity.ForgotPasswordRequest;
import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Entity.ForgotPasswordResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Interface.ResetPasswordInterface;

public class ResetPasswordInteractor implements ResetPasswordInterface.InteractorInput {

    private ResetPasswordInterface.InteractorOutput mInteractorOutput;
    private ResetPasswordInterface.View mView;
    private ResetPasswordInterface.DataStore mDataStore;

    public ResetPasswordInteractor(ResetPasswordInterface.View view, ResetPasswordInterface.InteractorOutput mInteractorOutput) {
        this.mInteractorOutput = mInteractorOutput;
        mView = view;
        mDataStore = ResetPasswordDataStore.getInstance(view);
    }

    @Override
    public void changePass(String userName, String newPass, String rePass, String token) {
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest(userName, newPass, rePass);
        mDataStore.callChangePass(new OnResponse<String, ForgotPasswordResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, ForgotPasswordResponse extraData) {
                if(extraData == null || extraData.getStatusCode() != 200) {
                    mInteractorOutput.changePassFailed(rs);
                } else {
                    mInteractorOutput.changePassSuccess(extraData.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mInteractorOutput.changePassFailed(message);
            }
        }, "Bearer " + token, forgotPasswordRequest);
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
    }

}

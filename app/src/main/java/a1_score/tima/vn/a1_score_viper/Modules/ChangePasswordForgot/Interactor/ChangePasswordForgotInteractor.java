package a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Interactor;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.DataStore.ChangePasswordForgotDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Entity.ChangePasswordForgotEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Entity.ChangePasswordForgotResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Interface.ChangePasswordForgotInterface;

public class ChangePasswordForgotInteractor implements ChangePasswordForgotInterface.InteractorInput {

    private ChangePasswordForgotInterface.InteractorOutput interactorOutput;
    private ChangePasswordForgotInterface.View view;
    private ChangePasswordForgotInterface.DataStore dataStore;

    public ChangePasswordForgotInteractor(ChangePasswordForgotInterface.View view, ChangePasswordForgotInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        this.view = view;
        dataStore = ChangePasswordForgotDataStore.getInstance(view);
    }

    @Override
    public void changePass(String userName, String newPass, String rePass, String token) {
        ChangePasswordForgotEntity changePasswordForgotEntity = new ChangePasswordForgotEntity(userName, newPass, rePass);
        dataStore.callChangePass(new OnResponse<String, ChangePasswordForgotResultEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, ChangePasswordForgotResultEntity extraData) {
                if(extraData == null || extraData.getStatusCode() != 200) {
                    interactorOutput.changePassFailed(rs);
                } else {
                    interactorOutput.changePassSuccess(extraData.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                interactorOutput.changePassFailed(message);
            }
        }, "Bearer " + token, changePasswordForgotEntity);
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
    }

}

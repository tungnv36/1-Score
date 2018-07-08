package a1_score.tima.vn.a1_score_viper.Modules.Setting.Interactor;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.DataStore.SettingDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Entity.LogoutResponseEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Interface.SettingInterface;

public class SettingInteractor implements SettingInterface.InteractorInput {

    private SettingInterface.InteractorOutput mInteractorOutput;
    private SettingInterface.View mView;
    private SettingInterface.DataStore mDataStore;

    public SettingInteractor(SettingInterface.View view, SettingInterface.InteractorOutput mInteractorOutput) {
        mView = view;
        this.mInteractorOutput = mInteractorOutput;
        mDataStore = SettingDataStore.getInstance(view);
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
    }

    @Override
    public void goToChangePhone() {
        mInteractorOutput.goToChangePhoneOutput();
    }

    @Override
    public void logout() {
        mDataStore.logout(new OnResponse<String, LogoutResponseEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, LogoutResponseEntity extraData) {
                if(extraData != null && extraData.getStatuscode() == 200) {
                    mInteractorOutput.logoutOutput();
                } else {
                    mInteractorOutput.logoutFailed(extraData.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mInteractorOutput.logoutFailed(message);
            }
        }, "Bearer " + mDataStore.getToken());
    }
}

package a1_score.tima.vn.a1_score_viper.Modules.Setting.Interactor;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.DataStore.SettingDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Entity.LogoutResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Interface.SettingInterface;

public class SettingInteractor implements SettingInterface.InteractorInput {

    private SettingInterface.InteractorOutput interactorOutput;
    private SettingInterface.View view;
    private SettingInterface.DataStore dataStore;

    public SettingInteractor(SettingInterface.View view, SettingInterface.InteractorOutput interactorOutput) {
        this.view = view;
        this.interactorOutput = interactorOutput;
        dataStore = SettingDataStore.getInstance(view);
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
    }

    @Override
    public void goToChangePhone() {
        interactorOutput.goToChangePhoneOutput();
    }

    @Override
    public void logout() {
        dataStore.logout(new OnResponse<String, LogoutResultEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, LogoutResultEntity extraData) {
                if(extraData != null && extraData.getStatuscode() == 200) {
                    interactorOutput.logoutOutput();
                } else {
                    interactorOutput.logoutFailed(extraData.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                interactorOutput.logoutFailed(message);
            }
        }, "Bearer " + dataStore.getToken());
    }
}

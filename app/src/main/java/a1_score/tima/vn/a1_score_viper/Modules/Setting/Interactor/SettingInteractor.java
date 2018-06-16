package a1_score.tima.vn.a1_score_viper.Modules.Setting.Interactor;

import a1_score.tima.vn.a1_score_viper.Modules.Setting.Interface.SettingInterface;

public class SettingInteractor implements SettingInterface.InteractorInput {

    private SettingInterface.InteractorOutput interactorOutput;

    public SettingInteractor(SettingInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
    }

    @Override
    public void goToChangePhone() {
        interactorOutput.goToChangePhoneOutput();
    }
}

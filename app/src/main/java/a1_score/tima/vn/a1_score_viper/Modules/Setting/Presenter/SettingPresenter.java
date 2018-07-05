package a1_score.tima.vn.a1_score_viper.Modules.Setting.Presenter;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.Setting.Interactor.SettingInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Interface.SettingInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Wireframe.SettingWireframe;

public class SettingPresenter implements SettingInterface.Presenter, SettingInterface.InteractorOutput {

    private SettingInterface.View view;
    private SettingInterface.InteractorInput interactorInput;
    private SettingInterface.Wireframe wireframe;

    public SettingPresenter(SettingInterface.View view) {
        this.view = view;
        interactorInput = new SettingInteractor(view, this);
        wireframe = new SettingWireframe();
    }

    @Override
    public void onDestroy() {
        view = null;
        interactorInput.unRegister();
        interactorInput = null;
        wireframe = null;
    }

    @Override
    public void goToChangePhone() {
        interactorInput.goToChangePhone();
    }

    @Override
    public void logout() {
        interactorInput.logout();
    }

    @Override
    public void goToChangePhoneOutput() {
        wireframe.goToChangePhone((Activity)view);
    }

    @Override
    public void logoutOutput() {
        view.logout();
    }

    @Override
    public void logoutFailed(String msg) {
        view.logoutFailed(msg);
    }
}

package a1_score.tima.vn.a1_score_viper.Modules.Setting.Presenter;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.Setting.Interactor.SettingInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Interface.SettingInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Wireframe.SettingWireframe;

public class SettingPresenter implements SettingInterface.Presenter, SettingInterface.InteractorOutput {

    private SettingInterface.View mView;
    private SettingInterface.InteractorInput mInteractorInput;
    private SettingInterface.Wireframe mWireframe;

    public SettingPresenter(SettingInterface.View view) {
        mView = view;
        mInteractorInput = new SettingInteractor(view, this);
        mWireframe = new SettingWireframe();
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput.unRegister();
        mInteractorInput = null;
        mWireframe = null;
    }

    @Override
    public void goToChangePhone() {
        mInteractorInput.goToChangePhone();
    }

    @Override
    public void logout() {
        mInteractorInput.logout();
    }

    @Override
    public void goToChangePhoneOutput() {
        mWireframe.goToChangePhone((Activity)mView);
    }

    @Override
    public void logoutOutput() {
        mView.logout();
    }

    @Override
    public void logoutFailed(String msg) {
        mView.logoutFailed(msg);
    }
}

package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Presenter;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Interactor.ChangePhoneInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Interface.ChangePhoneInterface;

public class ChangePhonePresenter implements ChangePhoneInterface.Presenter, ChangePhoneInterface.InteractorOutput {

    private ChangePhoneInterface.View mView;
    private ChangePhoneInterface.InteractorInput mInteractorInput;
    private ChangePhoneInterface.Wireframe mWireframe;

    public ChangePhonePresenter(ChangePhoneInterface.View view) {
        this.mView = view;
        mInteractorInput = new ChangePhoneInteractor(view, this);
    }

    @Override
    public void initPhone() {
        mInteractorInput.initPhone();
    }

    @Override
    public void changePhone(String oldPhone, String newPhone, String password) {
        mInteractorInput.changePhone(oldPhone, newPhone, password);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput.unRegister();
        mInteractorInput = null;
    }

    @Override
    public void initPhoneOutput(String phone) {
        mView.initPhone(phone);
    }

    @Override
    public void changePhoneSuccess(String msg) {
        mView.changePhoneSuccess(msg);
        ((Activity)mView).finish();
    }

    @Override
    public void changePhoneFailed(String err) {
        mView.changePhoneFailed(err);
    }
}

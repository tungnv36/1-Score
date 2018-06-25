package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Presenter;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Interactor.ChangePhoneInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Interface.ChangePhoneInterface;

public class ChangePhonePresenter implements ChangePhoneInterface.Presenter, ChangePhoneInterface.InteractorOutput {

    private ChangePhoneInterface.View view;
    private ChangePhoneInterface.InteractorInput interactorInput;
    private ChangePhoneInterface.Wireframe wireframe;

    public ChangePhonePresenter(ChangePhoneInterface.View view) {
        this.view = view;
        interactorInput = new ChangePhoneInteractor(view, this);
    }

    @Override
    public void initPhone() {
        interactorInput.initPhone();
    }

    @Override
    public void changePhone(String oldPhone, String newPhone, String password) {
        interactorInput.changePhone(oldPhone, newPhone, password);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactorInput.unRegister();
        interactorInput = null;
    }

    @Override
    public void initPhoneOutput(String phone) {
        view.initPhone(phone);
    }

    @Override
    public void changePhoneSuccess(String msg) {
        view.changePhoneSuccess(msg);
        ((Activity)view).finish();
    }

    @Override
    public void changePhoneFailed(String err) {
        view.changePhoneFailed(err);
    }
}

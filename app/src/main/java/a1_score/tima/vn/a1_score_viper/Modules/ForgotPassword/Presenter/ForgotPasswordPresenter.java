package a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Presenter;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Interactor.ForgotPasswordInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Interface.ForgotPasswordInterface;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Wireframe.ForgotPasswordWireframe;

public class ForgotPasswordPresenter implements ForgotPasswordInterface.Presenter, ForgotPasswordInterface.InteractorOutput {

    private ForgotPasswordInterface.View view;
    private ForgotPasswordInterface.InteractorInput interactorInput;
    private ForgotPasswordInterface.Wireframe wireframe;

    public ForgotPasswordPresenter(ForgotPasswordInterface.View view) {
        this.view = view;
        interactorInput = new ForgotPasswordInteractor(view, this);
        wireframe = new ForgotPasswordWireframe();
    }

    @Override
    public void forgotPassword(String phone) {
        interactorInput.forgotPassword(phone);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactorInput.unRegister();
        interactorInput = null;
    }

    @Override
    public void forgotPasswordSuccess(String msg, String phone) {
//        view.forgotPasswordSuccess(msg);
        wireframe.goToOtp((Activity)view, phone, msg);
        ((Activity)view).finish();
    }

    @Override
    public void forgotPasswordFailed(String err) {
        view.forgotPasswordFailed(err);
    }
}

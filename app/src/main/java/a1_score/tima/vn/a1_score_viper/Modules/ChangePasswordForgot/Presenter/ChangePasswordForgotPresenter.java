package a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Presenter;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Interactor.ChangePasswordForgotInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Interface.ChangePasswordForgotInterface;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Wireframe.ChangePasswordForgotWireframe;

public class ChangePasswordForgotPresenter implements ChangePasswordForgotInterface.Presenter, ChangePasswordForgotInterface.InteractorOutput {

    private ChangePasswordForgotInterface.View view;
    private ChangePasswordForgotInterface.InteractorInput interactorInput;
    private ChangePasswordForgotInterface.Wireframe wireframe;

    public ChangePasswordForgotPresenter(ChangePasswordForgotInterface.View view) {
        this.view = view;
        interactorInput = new ChangePasswordForgotInteractor(view, this);
        wireframe = new ChangePasswordForgotWireframe();
    }

    @Override
    public void changePass(String userName, String newPass, String rePass, String token) {
        interactorInput.changePass(userName, newPass, rePass, token);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactorInput.unRegister();
        interactorInput = null;
    }

    @Override
    public void changePassSuccess(String msg) {
        view.changePassSuccess(msg);
        wireframe.goToLogin((Activity)view);
        ((Activity)view).finish();
    }

    @Override
    public void changePassFailed(String err) {
        view.changePassFailed(err);
    }
}

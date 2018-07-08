package a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Presenter;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Interactor.ResetPasswordInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Interface.ResetPasswordInterface;
import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Wireframe.ResetPasswordWireframe;

public class ResetPasswordPresenter implements ResetPasswordInterface.Presenter, ResetPasswordInterface.InteractorOutput {

    private ResetPasswordInterface.View mView;
    private ResetPasswordInterface.InteractorInput mInteractorInput;
    private ResetPasswordInterface.Wireframe mWireframe;

    public ResetPasswordPresenter(ResetPasswordInterface.View view) {
        mView = view;
        mInteractorInput = new ResetPasswordInteractor(view, this);
        mWireframe = new ResetPasswordWireframe();
    }

    @Override
    public void changePass(String userName, String newPass, String rePass, String token) {
        mInteractorInput.changePass(userName, newPass, rePass, token);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput.unRegister();
        mInteractorInput = null;
    }

    @Override
    public void changePassSuccess(String msg) {
        mView.changePassSuccess(msg);
        mWireframe.goToLogin((Activity)mView);
        ((Activity)mView).finish();
    }

    @Override
    public void changePassFailed(String err) {
        mView.changePassFailed(err);
    }
}

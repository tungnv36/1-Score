package a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Presenter;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Interactor.ForgotPasswordInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Interface.ForgotPasswordInterface;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Wireframe.ForgotPasswordWireframe;

public class ForgotPasswordPresenter implements ForgotPasswordInterface.Presenter, ForgotPasswordInterface.InteractorOutput {

    private ForgotPasswordInterface.View mView;
    private ForgotPasswordInterface.InteractorInput mInteractorInput;
    private ForgotPasswordInterface.Wireframe mWireframe;

    public ForgotPasswordPresenter(ForgotPasswordInterface.View view) {
        mView = view;
        mInteractorInput = new ForgotPasswordInteractor(view, this);
        mWireframe = new ForgotPasswordWireframe();
    }

    @Override
    public void forgotPassword(String phone) {
        mInteractorInput.forgotPassword(phone);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput.unRegister();
        mInteractorInput = null;
    }

    @Override
    public void forgotPasswordSuccess(String msg, String phone) {
        mWireframe.goToOtp((Activity)mView, phone, msg);
        ((Activity)mView).finish();
    }

    @Override
    public void forgotPasswordFailed(String err) {
        mView.forgotPasswordFailed(err);
    }
}

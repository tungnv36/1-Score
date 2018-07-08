package a1_score.tima.vn.a1_score_viper.Modules.Register.Presenter;

import android.app.Activity;
import android.app.ProgressDialog;

import a1_score.tima.vn.a1_score_viper.Modules.Register.Interactor.RegisterInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Interface.RegisterInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Wireframe.RegisterWireframe;

public class RegisterPresenter implements RegisterInterface.Presenter, RegisterInterface.InteractorOutput {

    private RegisterInterface.View mView;
    private RegisterInterface.InteractorInput mInteractorInput;
    private RegisterInterface.Wireframe mWireframe;

    public RegisterPresenter(RegisterInterface.View view) {
        mView = view;
        mInteractorInput = new RegisterInteractor(view, this);
        mWireframe = new RegisterWireframe();
    }

    @Override
    public void register(ProgressDialog mProgress, String username, String password, String confirmPassword, String email) {
        mInteractorInput.register(mProgress, username, password, confirmPassword, email);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput.unRegister();
        mInteractorInput = null;
    }

    @Override
    public void EdittextEmpty(ProgressDialog mProgress, int type, String error) {
        mView.EdittextEmpty(mProgress, type, error);
    }

    @Override
    public void registerSuccess(ProgressDialog mProgress, String msg, String phoneNumber) {
        mProgress.dismiss();
        mWireframe.gotToOTP((Activity)mView, phoneNumber);
        ((Activity)mView).finish();
    }

    @Override
    public void registerFailed(ProgressDialog mProgress, String error) {
        mView.registerFailed(mProgress, error);
    }

}

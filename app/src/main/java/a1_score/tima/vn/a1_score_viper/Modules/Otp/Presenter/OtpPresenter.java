package a1_score.tima.vn.a1_score_viper.Modules.Otp.Presenter;

import android.app.Activity;
import android.content.Context;

import a1_score.tima.vn.a1_score_viper.Modules.Otp.Interactor.OtpInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Interface.OtpInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Wireframe.OtpWireframe;
import a1_score.tima.vn.a1_score_viper.R;

public class OtpPresenter implements OtpInterface.Presenter, OtpInterface.InteractorOutput {

    private OtpInterface.View mView;
    private OtpInterface.InteractorInput mInteractorInput;
    private OtpInterface.Wireframe mWireframe;

    public OtpPresenter(OtpInterface.View view) {
        this.mView = view;
        mInteractorInput = new OtpInteractor(view, this);
        mWireframe = new OtpWireframe();
    }

    @Override
    public void compareOtp(String phoneNumber, String action, String otp, int type) {
        mInteractorInput.compareOtp(phoneNumber, action, otp, type);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput.unRegister();
        mInteractorInput = null;
    }

    @Override
    public void compareOtpSuccess() {
        mView.compareOtpSuccess(((Activity)mView).getString(R.string.register_success));
        mWireframe.goToLogin((Activity)mView);
        ((Activity)mView).finish();
    }

    @Override
    public void compareOtpForgotPassSuccess(String phoneNumber, String token) {
        mWireframe.goToChangePass((Activity)mView, phoneNumber, token);
        ((Activity)mView).finish();
    }

    @Override
    public void compareOtpFailed(String err) {
        mView.compareOtpFailed(err);
    }

}

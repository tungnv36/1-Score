package a1_score.tima.vn.a1_score_viper.Modules.Otp.Presenter;

import android.app.Activity;
import android.content.Context;

import a1_score.tima.vn.a1_score_viper.Modules.Otp.Interactor.OtpInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Interface.OtpInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Wireframe.OtpWireframe;
import a1_score.tima.vn.a1_score_viper.R;

public class OtpPresenter implements OtpInterface.Presenter, OtpInterface.InteractorOutput {

    private OtpInterface.View view;
    private OtpInterface.InteractorInput interactorInput;
    private OtpInterface.Wireframe wireframe;

    public OtpPresenter(OtpInterface.View view) {
        this.view = view;
        interactorInput = new OtpInteractor(view, this);
        wireframe = new OtpWireframe();
    }

    @Override
    public void compareOtp(String phoneNumber, String action, String otp, int type) {
        interactorInput.compareOtp(phoneNumber, action, otp, type);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactorInput.unRegister();
        interactorInput = null;
    }

    @Override
    public void compareOtpSuccess() {
        view.compareOtpSuccess(((Activity)view).getString(R.string.register_success));
        wireframe.goToLogin((Activity)view);
        ((Activity)view).finish();
    }

    @Override
    public void compareOtpForgotPassSuccess(String phoneNumber, String token) {
        wireframe.goToChangePass((Activity)view, phoneNumber, token);
        ((Activity)view).finish();
    }

    @Override
    public void compareOtpFailed(String err) {
        view.compareOtpFailed(err);
    }

}

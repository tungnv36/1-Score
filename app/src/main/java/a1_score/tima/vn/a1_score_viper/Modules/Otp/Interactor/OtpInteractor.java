package a1_score.tima.vn.a1_score_viper.Modules.Otp.Interactor;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.DataStore.OtpDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity.OtpRequest;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity.OtpResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Interface.OtpInterface;

public class OtpInteractor implements OtpInterface.InteractorInput {

    private OtpInterface.InteractorOutput mInteractorOutput;
    private OtpInterface.DataStore mDataStore;

    public OtpInteractor(OtpInterface.View view, OtpInterface.InteractorOutput interactorOutput) {
        this.mInteractorOutput = interactorOutput;
        mDataStore = OtpDataStore.getInstance(view);
    }

    @Override
    public void compareOtp(final String phoneNumber, final String aciton, String otp, final int type) {
        if(otp.length() < 6) {
            mInteractorOutput.compareOtpFailed("Bạn chưa nhập đủ OTP");
            return;
        }
        OtpRequest otpRequest = new OtpRequest(phoneNumber, aciton, otp);
        mDataStore.compareOtp(new OnResponse<String, OtpResultEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, OtpResultEntity extraData) {
                if(extraData == null || extraData.getStatusCode() != 200) {
                    mInteractorOutput.compareOtpFailed(rs);
                } else {
                    if(type == 1) {
                        mInteractorOutput.compareOtpSuccess();
                    } else {
                        mInteractorOutput.compareOtpForgotPassSuccess(phoneNumber, extraData.getToken());
                    }
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mInteractorOutput.compareOtpFailed(message);
            }
        }, otpRequest);
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
        mDataStore = null;
    }

}

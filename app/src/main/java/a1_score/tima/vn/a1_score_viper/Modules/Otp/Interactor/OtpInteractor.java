package a1_score.tima.vn.a1_score_viper.Modules.Otp.Interactor;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.DataStore.OtpDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity.OtpEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity.OtpResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Interface.OtpInterface;

public class OtpInteractor implements OtpInterface.InteractorInput {

    private OtpInterface.InteractorOutput interactorOutput;
    private OtpInterface.DataStore dataStore;

    public OtpInteractor(OtpInterface.View view, OtpInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        dataStore = OtpDataStore.getInstance(view);
    }

    @Override
    public void compareOtp(final String phoneNumber, final String aciton, String otp, final int type) {
        if(otp.length() < 6) {
            interactorOutput.compareOtpFailed("Bạn chưa nhập đủ OTP");
            return;
        }
        OtpEntity otpEntity = new OtpEntity(phoneNumber, aciton, otp);
        dataStore.compareOtp(new OnResponse<String, OtpResultEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, OtpResultEntity extraData) {
                if(extraData == null || extraData.getStatusCode() != 200) {
                    interactorOutput.compareOtpFailed(rs);
                } else {
                    if(type == 1) {
                        interactorOutput.compareOtpSuccess();
                    } else {
                        interactorOutput.compareOtpForgotPassSuccess(phoneNumber, extraData.getToken());
                    }
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                interactorOutput.compareOtpFailed(message);
            }
        }, otpEntity);
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
        dataStore = null;
    }

}

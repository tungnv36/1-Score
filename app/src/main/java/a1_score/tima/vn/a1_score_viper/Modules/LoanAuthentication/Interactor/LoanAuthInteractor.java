package a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Interactor;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.DataStore.LoanAuthDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Entity.LoanAuthRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Entity.LoanAuthResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Interface.LoanAuthInterface;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanResponse;

public class LoanAuthInteractor implements LoanAuthInterface.InteractorInput {

    private LoanAuthInterface.View mView;
    private LoanAuthInterface.InteractorOutput mInteractorOutput;
    private LoanAuthInterface.DataStore mDataStore;

    public LoanAuthInteractor(LoanAuthInterface.View view, LoanAuthInterface.InteractorOutput interactorOutput) {
        mView = view;
        mInteractorOutput = interactorOutput;
        mDataStore = LoanAuthDataStore.getInstance(view);
    }

    @Override
    public void unRegister() {
        mView = null;
        mInteractorOutput = null;
        mDataStore = null;
    }

    @Override
    public void uploadVideo(String des) {
        LoanAuthRequest loanAuthRequest = new LoanAuthRequest();
        loanAuthRequest.setUsername(mDataStore.getUser());
        loanAuthRequest.setDescription(des);

        mDataStore.uploadVideo(new OnResponse<String, LoanAuthResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, LoanAuthResponse extraData) {
                if(extraData != null && extraData.getStatuscode() == 200) {
                    mInteractorOutput.uploadVideoSuccess(extraData.getMessage());
                } else {
                    mInteractorOutput.uploadVideoFail(extraData.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mInteractorOutput.uploadVideoFail(message);
            }
        }, String.format("Bearer %s", mDataStore.getToken()), loanAuthRequest);
    }

    @Override
    public void registerLoanCredit(final String username, long value, int duration, int packageId, int paymentMethodId, int purposeId) {
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setUsername(username);
        loanRequest.setValue(value);
        loanRequest.setPurposeId(purposeId);
        loanRequest.setPaymentMethodId(paymentMethodId);
        loanRequest.setPackageId(packageId);
        loanRequest.setDuration(duration);
        mDataStore.registerLoanCredit(new OnResponse<String, LoanResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, LoanResponse extraData) {
                if(extraData != null && extraData.getStatuscode() == 200) {
                    mDataStore.updateRegisterLoanCreditToDB(extraData.getLoancredit(), username);
                    mInteractorOutput.registerLoanCreditSuccess(extraData.getMessage());
                } else {
                    mInteractorOutput.registerLoanCreditFail(extraData.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mInteractorOutput.registerLoanCreditFail(message);
            }
        }, String.format("Bearer %s", mDataStore.getToken()), loanRequest);
    }

}

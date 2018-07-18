package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Interactor;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.DataStore.LoanRegistrationDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Interface.LoanRegistrationInterface;

public class LoanRegistrationInteractor implements LoanRegistrationInterface.InteractorInput {

    private LoanRegistrationInterface.View mView;
    private LoanRegistrationInterface.InteractorOutput mInteractorOutput;
    private LoanRegistrationInterface.DataStore mDataStore;

    public LoanRegistrationInteractor(LoanRegistrationInterface.View view, LoanRegistrationInterface.InteractorOutput interactorOutput) {
        mView = view;
        mInteractorOutput = interactorOutput;
        mDataStore = LoanRegistrationDataStore.getInstance(view);
    }

    @Override
    public void getLoanDictionary() {
        if(mDataStore.checkLoanDicExist()) {
            mInteractorOutput.getPurposeOutput(mDataStore.getPurposeDic());
            mInteractorOutput.getPaymentMethodOutput(mDataStore.getPaymentMethodDic());
        } else {
            mDataStore.getLoanDictionary(new OnResponse<String, LoanDictionaryResponse>() {
                @Override
                public void onResponseSuccess(String tag, String rs, LoanDictionaryResponse extraData) {
                    if(extraData != null && extraData.getStatuscode() == 200) {
                        if(mDataStore.updateLoanDicToDB(extraData) > 0) {
                            mInteractorOutput.getPurposeOutput(mDataStore.getPurposeDic());
                            mInteractorOutput.getPaymentMethodOutput(mDataStore.getPaymentMethodDic());
                        }
                    }
                }

                @Override
                public void onResponseError(String tag, String message) {

                }
            }, String.format("Bearer %s", mDataStore.getToken()));
        }
    }

    @Override
    public void goToLoanAuth(LoanRequest loanRequest) {
        loanRequest.setUsername(mDataStore.getUser());
        mInteractorOutput.goToLoanAuthOutput(loanRequest);
    }

    @Override
    public void unRegister() {
        mView = null;
        mInteractorOutput = null;
    }

}

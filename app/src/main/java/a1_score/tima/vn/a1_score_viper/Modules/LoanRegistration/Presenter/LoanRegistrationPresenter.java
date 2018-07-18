package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Presenter;

import android.app.Activity;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Interactor.LoanRegistrationInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Interface.LoanRegistrationInterface;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Wireframe.LoanRegistrationWireframe;

public class LoanRegistrationPresenter implements LoanRegistrationInterface.Presenter, LoanRegistrationInterface.InteractorOutput {

    private LoanRegistrationInterface.View mView;
    private LoanRegistrationInterface.InteractorInput mInteractorInput;
    private LoanRegistrationInterface.Wireframe mWireframe;

    public LoanRegistrationPresenter(LoanRegistrationInterface.View view) {
        mView = view;
        mInteractorInput = new LoanRegistrationInteractor(view, this);
        mWireframe = new LoanRegistrationWireframe();
    }

    @Override
    public void getLoanDictionary() {
        mInteractorInput.getLoanDictionary();
    }

    @Override
    public void goToLoanAuth(LoanRequest loanRequest) {
        mInteractorInput.goToLoanAuth(loanRequest);
    }

    @Override
    public void onDestroy() {
        mInteractorInput.unRegister();
        mInteractorInput = null;
        mView = null;
        mWireframe = null;
    }

    @Override
    public void goToLoanAuthOutput(LoanRequest loanRequest) {
        mWireframe.goToLoanAuth((Activity)mView, loanRequest);
    }

    @Override
    public void getPurposeOutput(List<LoanDictionaryResponse.PurposeEntity> purposeEntities) {
        mView.initPurpose(purposeEntities);
    }

    @Override
    public void getPaymentMethodOutput(List<LoanDictionaryResponse.PaymentMethodEntity> paymentMethodEntities) {
        mView.initPaymentMethod(paymentMethodEntities);
    }

}

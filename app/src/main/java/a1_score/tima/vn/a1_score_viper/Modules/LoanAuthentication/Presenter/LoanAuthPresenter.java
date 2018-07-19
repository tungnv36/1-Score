package a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Presenter;

import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Interactor.LoanAuthInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Interface.LoanAuthInterface;

public class LoanAuthPresenter implements LoanAuthInterface.Presenter, LoanAuthInterface.InteractorOutput {

    private LoanAuthInterface.View mView;
    private LoanAuthInterface.InteractorInput mInteractorInput;
    private LoanAuthInterface.Wireframe mWireframe;

    public LoanAuthPresenter(LoanAuthInterface.View view) {
        mView = view;
        mInteractorInput = new LoanAuthInteractor(view, this);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput = null;
    }

    @Override
    public void uploadVideo(String des) {
        mInteractorInput.uploadVideo(des);
    }

    @Override
    public void registerLoanCredit(String username, long value, int duration, int packageId, int paymentMethodId, int purposeId) {
        mInteractorInput.registerLoanCredit(username, value, duration, packageId, paymentMethodId, purposeId);
    }

    @Override
    public void uploadVideoSuccess(String msg) {
        mView.uploadVideoSuccess(msg);
    }

    @Override
    public void uploadVideoFail(String err) {
        mView.uploadVideoFail(err);
    }

    @Override
    public void registerLoanCreditSuccess(String msg) {
        mView.registerLoanCreditSuccess(msg);
    }

    @Override
    public void registerLoanCreditFail(String err) {
        mView.registerLoanCreditFail(err);
    }
}

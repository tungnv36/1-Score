package a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Interactor;

import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.DataStore.LoanAuthDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Interface.LoanAuthInterface;

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

}

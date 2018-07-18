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

    }

}

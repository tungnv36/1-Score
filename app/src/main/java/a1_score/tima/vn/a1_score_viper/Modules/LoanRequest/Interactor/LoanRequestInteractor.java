package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interactor;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interface.LoanRequestInterface;

public class LoanRequestInteractor implements LoanRequestInterface.InteractorInput {

    private LoanRequestInterface.InteractorOutput presenter;

    public LoanRequestInteractor(LoanRequestInterface.InteractorOutput presenter) {
        this.presenter = presenter;
    }

    @Override
    public void goToLoanRegistration() {
        presenter.goToLoanRegistrationOutput();
    }

    @Override
    public void initAnimationLogo(ImageView view) {
        presenter.runAnimationLogo(view);
    }

    @Override
    public void setupAnimationProgress(ProgressBar progress, int start, int end) {
        presenter.runAnimationProgress(progress, start, end);
    }

    @Override
    public void openOrCloseInfo(Context context, RelativeLayout view, Button button, boolean isOpen, int position) {
        presenter.openOrCloseInfoOutput(context, view, button, isOpen, position);
    }

    @Override
    public void setupAnimationItem(Context context, View view) {
        presenter.runAnimationItem(context, view);
    }

    @Override
    public void setupAnimationItemOpenOrClose(Context context, View view, boolean isOpen) {
        presenter.runAnimationItemOpenOrClose(context, view, isOpen);
    }

    @Override
    public void unRegister() {
        presenter = null;
    }

}

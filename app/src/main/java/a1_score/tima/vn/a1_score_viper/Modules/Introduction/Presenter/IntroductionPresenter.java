package a1_score.tima.vn.a1_score_viper.Modules.Introduction.Presenter;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Interactor.IntroductionInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Interface.IntroductionInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Wireframe.IntroductionWireframe;

public class IntroductionPresenter implements IntroductionInterface.Presenter, IntroductionInterface.InteractorOutput {

    private IntroductionInterface.InteractorInput mInteractorInput;
    private IntroductionInterface.View mView;
    private IntroductionInterface.Wireframe mWireframe;

    public IntroductionPresenter(IntroductionInterface.View view) {
        mInteractorInput = new IntroductionInteractor(this, view);
        mWireframe = new IntroductionWireframe();
        mView = view;
    }

    @Override
    public void nextPagePresenter(int currentPage, int countPage) {
        mInteractorInput.nextPage(currentPage, countPage);
    }

    @Override
    public void backPagePresenter(int currentPage) {
        mInteractorInput.backPage(currentPage);
    }

    @Override
    public void checkFirstLaunch() {
        mInteractorInput.checkFirstLaunch();
    }

    @Override
    public void onDestroy() {
        mInteractorInput.unRegister();
        mInteractorInput = null;
        mWireframe = null;
    }

    @Override
    public void nextPageOutput(int page) {
        mView.setPage(page);
    }

    @Override
    public void backPageOutput(int page) {
        mView.setPage(page);
    }

    @Override
    public void launchMainScreen() {
        mWireframe.goToMainView((Activity) mView);
        mView.finishView();
    }

}

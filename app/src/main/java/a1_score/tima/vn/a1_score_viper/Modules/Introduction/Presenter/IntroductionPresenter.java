package a1_score.tima.vn.a1_score_viper.Modules.Introduction.Presenter;

import android.app.Activity;
import android.content.Context;

import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Interactor.IntroductionInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Interface.IntroductionInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Introduction.View.IntroductionView;
import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Wireframe.IntroductionWireframe;

public class IntroductionPresenter implements IntroductionInterface.Presenter, IntroductionInterface.InteractorOutput {

    private IntroductionInterface.InteractorInput introInteractor;
    private IntroductionInterface.View introView;
    private IntroductionInterface.Wireframe introWireframe;

    public IntroductionPresenter(IntroductionInterface.View introView) {
        introInteractor = new IntroductionInteractor(this, introView);
        introWireframe = new IntroductionWireframe();
        this.introView = introView;
    }

    @Override
    public void nextPagePresenter(int currentPage, int countPage) {
        introInteractor.nextPage(currentPage, countPage);
    }

    @Override
    public void backPagePresenter(int currentPage) {
        introInteractor.backPage(currentPage);
    }

    @Override
    public void checkFirstLaunch() {
        introInteractor.checkFirstLaunch();
    }

    @Override
    public void onDestroy() {
        introInteractor.unRegister();
        introInteractor = null;
        introWireframe = null;
    }

    @Override
    public void nextPageOutput(int page) {
        introView.setPage(page);
    }

    @Override
    public void backPageOutput(int page) {
        introView.setPage(page);
    }

    @Override
    public void launchMainScreen() {
        introWireframe.goToMainView((Activity) introView);
        introView.finishView();
    }

}

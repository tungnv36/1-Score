package a1_score.tima.vn.a1_score_viper.Modules.Introduction.Interactor;

import android.content.Context;

import a1_score.tima.vn.a1_score_viper.Modules.Introduction.DataStore.IntroductionDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Entity.IntroductionEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Interface.IntroductionInterface;

public class IntroductionInteractor implements IntroductionInterface.InteractorInput {

    private IntroductionInterface.InteractorOutput presenterOutput;
    private IntroductionDataStore introductionDataStore;

    public IntroductionInteractor(IntroductionInterface.InteractorOutput presenterOutput, IntroductionInterface.View introView) {
        this.presenterOutput = presenterOutput;
        introductionDataStore = new IntroductionDataStore((Context) introView);
    }

    @Override
    public void nextPage(int currentPage, int countPage) {
        if(currentPage < countPage - 1) {
            presenterOutput.nextPageOutput(++currentPage);
        } else {
            presenterOutput.launchMainScreen();
            IntroductionEntity introductionEntity = new IntroductionEntity(false);
            introductionDataStore.setFirstTimeLaunch(introductionEntity);
        }
    }

    @Override
    public void backPage(int currentPage) {
        if(currentPage > 0) {
            presenterOutput.backPageOutput(--currentPage);
        }
    }

    @Override
    public void checkFirstLaunch() {
        if (!introductionDataStore.isFirstTimeLaunch()) {
            presenterOutput.launchMainScreen();
//            IntroductionEntity introductionEntity = new IntroductionEntity(true);
//            introductionDataStore.setFirstTimeLaunch(introductionEntity);
        }
    }

    @Override
    public void unRegister() {
        presenterOutput = null;
    }

}

package a1_score.tima.vn.a1_score_viper.Modules.Introduction.Interactor;

import android.content.Context;

import a1_score.tima.vn.a1_score_viper.Modules.Introduction.DataStore.IntroductionDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Entity.IntroductionEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Interface.IntroductionInterface;

public class IntroductionInteractor implements IntroductionInterface.InteractorInput {

    private IntroductionInterface.InteractorOutput mInteractorOutput;
    private IntroductionDataStore mIntroductionDataStore;

    public IntroductionInteractor(IntroductionInterface.InteractorOutput interactorOutput, IntroductionInterface.View introView) {
        this.mInteractorOutput = interactorOutput;
        mIntroductionDataStore = new IntroductionDataStore((Context) introView);
    }

    @Override
    public void nextPage(int currentPage, int countPage) {
        if(currentPage < countPage - 1) {
            mInteractorOutput.nextPageOutput(++currentPage);
        } else {
            mInteractorOutput.launchMainScreen();
            IntroductionEntity introductionEntity = new IntroductionEntity(false);
            mIntroductionDataStore.setFirstTimeLaunch(introductionEntity);
        }
    }

    @Override
    public void backPage(int currentPage) {
        if(currentPage > 0) {
            mInteractorOutput.backPageOutput(--currentPage);
        }
    }

    @Override
    public void checkFirstLaunch() {
        if (!mIntroductionDataStore.isFirstTimeLaunch()) {
            mInteractorOutput.launchMainScreen();
//            IntroductionEntity introductionEntity = new IntroductionEntity(true);
//            introductionDataStore.setFirstTimeLaunch(introductionEntity);
        }
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
    }

}

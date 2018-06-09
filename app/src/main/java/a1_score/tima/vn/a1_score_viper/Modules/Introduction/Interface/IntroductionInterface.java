package a1_score.tima.vn.a1_score_viper.Modules.Introduction.Interface;

import android.app.Activity;

public interface IntroductionInterface {
    //View
    interface View {
        void setPage(int changePage);
        void finishView();
        void onDestroy();
    }
    //Presenter
    interface Presenter {
        void nextPagePresenter(int currentPage, int countPage);
        void backPagePresenter(int currentPage);
        void checkFirstLaunch();
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void nextPage(int currentPage, int countPage);
        void backPage(int currentPage);
        void checkFirstLaunch();
        void unRegister();
    }

    interface InteractorOutput {
        void nextPageOutput(int page);
        void backPageOutput(int page);
        void launchMainScreen();
    }
    //Wireframe
    interface Wireframe {
        void goToMainView(Activity activity);
    }

}

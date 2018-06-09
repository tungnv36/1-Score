package a1_score.tima.vn.a1_score_viper.Modules.Profile.Interface;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public interface ProfileInterface {
    //View
    interface View {
        void onDestroy();
    }
    //Presenter
    interface Presenter {
        void goToUpdateProfile();
        void goToUpdateJob();
        void goToUpdateFamily();
        void goToUpdateSocialNetwork();
        void goToUpdatePapers();
        void initAnimationLogo(ImageView view);
        void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end);
        void setupAnimationProgress(ProgressBar progress, int start, int end);
        void setupAnimationPress(Context context, android.view.View view);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void goToUpdateProfile();
        void goToUpdateJob();
        void goToUpdateFamily();
        void goToUpdateSocialNetwork();
        void goToUpdatePapers();
        void initAnimationLogo(ImageView view);
        void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end);
        void setupAnimationProgress(ProgressBar progress, int start, int end);
        void setupAnimationPress(Context context, android.view.View view);
        void unRegister();
    }

    interface InteractorOutput {
        void goToUpdateProfileOutput();
        void goToUpdateJobOutput();
        void goToUpdateFamilyOutput();
        void goToUpdateSocialNetworkOutput();
        void goToUpdatePapersOutput();
        void runAnimationLogo(ImageView view);
        void runAnimationSeekBar(CircularSeekBar seekBar, int start, int end);
        void runAnimationProgress(ProgressBar progress, int start, int end);
        void runAnimationPress(Context context, android.view.View view);
    }
    //Wireframe
    interface Wireframe {
        void goToUpdateProfile(Activity activity);
        void goToUpdateJob(Activity activity);
        void goToUpdateFamily(Activity activity);
        void goToUpdateSocialNetwork(Activity activity);
        void goToUpdatePapers(Activity activity);
    }
}

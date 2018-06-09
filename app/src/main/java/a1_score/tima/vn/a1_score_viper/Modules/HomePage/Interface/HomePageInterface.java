package a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interface;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public interface HomePageInterface {
    //View
    interface View {
        void onDestroy();
        void setProgressValue(int progress);
        void callSupportFailed(String err);
    }
    //Presenter
    interface Presenter {
        void initAnimationLogo(ImageView view);
        void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end);
        void goToProfile();
        void goToLoanRequest();
        void goToIntroduceFriends();
        void setupAnimationPress(Context context, android.view.View view);
        void setupAnimationSupport(Context context, android.view.View view, int animOpen, int animClose);
        void callSupport(Context context, String phoneNumber);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void initAnimationLogo(ImageView view);
        void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end);
        void goToProfile();
        void goToLoanRequest();
        void goToIntroduceFriends();
        void setupAnimationPress(Context context, android.view.View view);
        void setupAnimationSupport(Context context, android.view.View view, int animOpen, int animClose);
        void callSupport(Context context, String phoneNumber);
        void unRegister();
    }

    interface InteractorOutput {
        void runAnimationLogo(ImageView view);
        void runAnimationSeekBar(CircularSeekBar seekBar, int start, int end);
        void goToProfileOutput();
        void goToLoanRequestOutput();
        void goToIntroduceFriendsOutput();
        void runAnimationPress(Context context, android.view.View view);
        void runAnimationSupport(Context context, android.view.View view, int animOpen, int animClose);
        void callSupportOutput(Context context, String phoneNumber);
    }
    //Wireframe
    interface Wireframe {
        void goToProfile(Activity activity);
        void goToLoanRequest(Activity activity);
        void goToIntroduceFriends(Activity activity);
        void callSupportOutput(Context context, String phoneNumber);
    }
}

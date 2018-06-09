package a1_score.tima.vn.a1_score_viper.Modules.Profile.Presenter;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import a1_score.tima.vn.a1_score_viper.Modules.Profile.Interactor.ProfileInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.Interface.ProfileInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.Wireframe.ProfileWireframe;
import a1_score.tima.vn.a1_score_viper.R;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class ProfilePresenter implements ProfileInterface.Presenter, ProfileInterface.InteractorOutput {

    private ProfileInterface.View view;
    private ProfileInterface.InteractorInput interactorInput;
    private ProfileInterface.Wireframe wireframe;

    public ProfilePresenter(ProfileInterface.View view) {
        this.view = view;
        interactorInput = new ProfileInteractor(this);
        wireframe = new ProfileWireframe();
    }

    @Override
    public void goToUpdateProfile() {
        interactorInput.goToUpdateProfile();
    }

    @Override
    public void goToUpdateJob() {
        interactorInput.goToUpdateJob();
    }

    @Override
    public void goToUpdateFamily() {
        interactorInput.goToUpdateFamily();
    }

    @Override
    public void goToUpdateSocialNetwork() {
        interactorInput.goToUpdateSocialNetwork();
    }

    @Override
    public void goToUpdatePapers() {
        interactorInput.goToUpdatePapers();
    }

    @Override
    public void initAnimationLogo(ImageView view) {
        interactorInput.initAnimationLogo(view);
    }

    @Override
    public void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end) {
        interactorInput.setupAnimationSeekBar(seekBar, start, end);
    }

    @Override
    public void setupAnimationProgress(ProgressBar progress, int start, int end) {
        interactorInput.setupAnimationProgress(progress, start, end);
    }

    @Override
    public void setupAnimationPress(Context context, View view) {
        interactorInput.setupAnimationPress(context, view);
    }

    @Override
    public void onDestroy() {
        interactorInput.unRegister();
        interactorInput = null;
        view.onDestroy();
        view = null;
    }

    @Override
    public void goToUpdateProfileOutput() {
        wireframe.goToUpdateProfile((Activity)view);
    }

    @Override
    public void goToUpdateJobOutput() {
        wireframe.goToUpdateJob((Activity)view);
    }

    @Override
    public void goToUpdateFamilyOutput() {
        wireframe.goToUpdateFamily((Activity)view);
    }

    @Override
    public void goToUpdateSocialNetworkOutput() {
        wireframe.goToUpdateSocialNetwork((Activity)view);
    }

    @Override
    public void goToUpdatePapersOutput() {
        wireframe.goToUpdatePapers((Activity)view);
    }

    @Override
    public void runAnimationLogo(ImageView view) {
        Animation anim = AnimationUtils.loadAnimation((Context) this.view, R.anim.scale_logo);
        view.startAnimation(anim);
    }

    @Override
    public void runAnimationSeekBar(CircularSeekBar seekBar, int start, int end) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(seekBar, "progress", start,end);
        anim.setDuration(1000 * (end - start)/100);
        anim.start();
    }

    @Override
    public void runAnimationProgress(ProgressBar progress, int start, int end) {
        ObjectAnimator anim = ObjectAnimator.ofInt(progress, "progress", start,end);
        anim.setDuration(1000 * (end - start)/100);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
    }

    @Override
    public void runAnimationPress(Context context, android.view.View view) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_press_item);
        view.startAnimation(anim);
    }

}

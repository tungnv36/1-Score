package a1_score.tima.vn.a1_score_viper.Modules.Profile.Interactor;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;

import a1_score.tima.vn.a1_score_viper.Modules.Profile.Interface.ProfileInterface;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class ProfileInteractor implements ProfileInterface.InteractorInput {

    private ProfileInterface.InteractorOutput interactorOutput;

    public ProfileInteractor(ProfileInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
    }

    @Override
    public void goToUpdateProfile() {
        interactorOutput.goToUpdateProfileOutput();
    }

    @Override
    public void goToUpdateJob() {
        interactorOutput.goToUpdateJobOutput();
    }

    @Override
    public void goToUpdateFamily() {
        interactorOutput.goToUpdateFamilyOutput();
    }

    @Override
    public void goToUpdateSocialNetwork() {
        interactorOutput.goToUpdateSocialNetworkOutput();
    }

    @Override
    public void goToUpdatePapers() {
        interactorOutput.goToUpdatePapersOutput();
    }

    @Override
    public void initAnimationLogo(ImageView view) {
        interactorOutput.runAnimationLogo(view);
    }

    @Override
    public void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end) {
        interactorOutput.runAnimationSeekBar(seekBar, start, end);
    }

    @Override
    public void setupAnimationProgress(ProgressBar progress, int start, int end) {
        interactorOutput.runAnimationProgress(progress, start, end);
    }

    @Override
    public void setupAnimationPress(Context context, android.view.View view) {
        interactorOutput.runAnimationPress(context, view);
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
    }

}

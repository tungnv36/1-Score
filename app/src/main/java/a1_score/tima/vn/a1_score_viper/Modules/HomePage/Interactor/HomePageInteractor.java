package a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interactor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;

import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interface.HomePageInterface;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.View.HomePageView;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class HomePageInteractor implements HomePageInterface.InteractorInput {

    private HomePageInterface.InteractorOutput interactorOutput;

    public HomePageInteractor(HomePageInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
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
    public void goToProfile() {
        interactorOutput.goToProfileOutput();
    }

    @Override
    public void goToLoanRequest() {
        interactorOutput.goToLoanRequestOutput();
    }

    @Override
    public void goToIntroduceFriends() {
        interactorOutput.goToIntroduceFriendsOutput();
    }

    @Override
    public void setupAnimationPress(Context context, View view) {
        interactorOutput.runAnimationPress(context, view);
    }

    @Override
    public void setupAnimationSupport(Context context, View view, int animOpen, int aimClose) {
        interactorOutput.runAnimationSupport(context, view, animOpen, aimClose);
    }

    @Override
    public void callSupport(Context context, String phoneNumber) {
        if (Build.VERSION.SDK_INT < 23) {
            interactorOutput.callSupportOutput(context, phoneNumber);
        }else {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                interactorOutput.callSupportOutput(context, phoneNumber);
            }else {
                final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                ActivityCompat.requestPermissions((Activity) context, PERMISSIONS_STORAGE, 9);
            }
        }
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
    }

}

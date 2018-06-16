package a1_score.tima.vn.a1_score_viper.Modules.HomePage.Presenter;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interactor.HomePageInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interface.HomePageInterface;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Wireframe.HomePageWireframe;
import a1_score.tima.vn.a1_score_viper.R;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class HomePagePresenter implements HomePageInterface.Presenter, HomePageInterface.InteractorOutput {

    private HomePageInterface.InteractorInput interactorInput;
    private HomePageInterface.Wireframe wireframe;
    private HomePageInterface.View view;

    public HomePagePresenter(HomePageInterface.View view) {
        interactorInput = new HomePageInteractor(this);
        wireframe = new HomePageWireframe();
        this.view = view;
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
    public void goToProfile() {
        interactorInput.goToProfile();
    }

    @Override
    public void goToLoanRequest() {
        interactorInput.goToLoanRequest();
    }

    @Override
    public void goToIntroduceFriends() {
        interactorInput.goToIntroduceFriends();
    }

    @Override
    public void goToSetting() {
        interactorInput.goToSetting();
    }

    @Override
    public void setupAnimationPress(Context context, View view) {
        interactorInput.setupAnimationPress(context, view);
    }

    @Override
    public void setupAnimationSupport(Context context, View view, int animOpen, int animClose) {
        interactorInput.setupAnimationSupport(context, view, animOpen, animClose);
    }

    @Override
    public void callSupport(Context context, String phoneNumber) {
        interactorInput.callSupport(context, phoneNumber);
    }

    @Override
    public void onDestroy() {
        interactorInput.unRegister();
        interactorInput = null;
        wireframe = null;
        view = null;
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
    public void goToProfileOutput() {
        wireframe.goToProfile((Activity)view);
    }

    @Override
    public void goToLoanRequestOutput() {
        wireframe.goToLoanRequest((Activity)view);
    }

    @Override
    public void goToIntroduceFriendsOutput() {
        wireframe.goToIntroduceFriends((Activity)view);
    }

    @Override
    public void goToSettingOutput() {
        wireframe.goToSetting((Activity)view);
    }

    @Override
    public void runAnimationPress(Context context, View view) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_press_item);
        view.startAnimation(anim);
    }

    @Override
    public void runAnimationSupport(Context context, View view, int animOpen, int animClose) {
        if(view.isShown()) {//close button
            Animation anim = AnimationUtils.loadAnimation(context, animClose);
            view.startAnimation(anim);
            view.setVisibility(View.GONE);
        } else {//open button
            view.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(context, animOpen);
            view.startAnimation(anim);
        }
    }

    @Override
    public void callSupportOutput(Context context, String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            wireframe.callSupportOutput(context, phoneNumber);
        }else{
            view.callSupportFailed(context.getString(R.string.permission_fail));
        }
    }

}

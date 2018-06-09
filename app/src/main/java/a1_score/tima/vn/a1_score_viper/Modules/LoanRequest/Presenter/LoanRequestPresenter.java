package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Presenter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interactor.LoanRequestInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interface.LoanRequestInterface;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Wireframe.LoanRequestWireframe;
import a1_score.tima.vn.a1_score_viper.R;

public class LoanRequestPresenter implements LoanRequestInterface.Presenter, LoanRequestInterface.InteractorOutput {

    private LoanRequestInterface.View view;
    private LoanRequestInterface.InteractorInput interactorInput;
    private LoanRequestInterface.Wireframe wireframe;

    public LoanRequestPresenter(LoanRequestInterface.View view) {
        this.view = view;
        this.interactorInput = new LoanRequestInteractor(this);
        wireframe = new LoanRequestWireframe();
    }

    @Override
    public void goToLoanRegistration() {
        interactorInput.goToLoanRegistration();
    }

    @Override
    public void initAnimationLogo(ImageView view) {
        interactorInput.initAnimationLogo(view);
    }

    @Override
    public void setupAnimationProgress(ProgressBar progress, int start, int end) {
        interactorInput.setupAnimationProgress(progress, start, end);
    }

    @Override
    public void openOrCloseInfo(Context context, RelativeLayout view, Button button, boolean isOpen, int position) {
        interactorInput.openOrCloseInfo(context, view, button, isOpen, position);
    }

    @Override
    public void setupAnimationItem(Context context, View view) {
        interactorInput.setupAnimationItem(context, view);
    }

    @Override
    public void setupAnimationOpenOrClose(Context context, View view, boolean isOpen) {
        interactorInput.setupAnimationItemOpenOrClose(context, view, isOpen);
    }

    @Override
    public void onDestroy() {
        interactorInput.unRegister();
        interactorInput = null;
        view.onDestroy();
        view = null;
        wireframe = null;
    }

    @Override
    public void goToLoanRegistrationOutput() {
        wireframe.goToLoanRegistration((Activity)view);
    }

    @Override
    public void runAnimationLogo(ImageView view) {
        Animation anim = AnimationUtils.loadAnimation((Context) this.view, R.anim.scale_logo);
        view.startAnimation(anim);
    }

    @Override
    public void runAnimationProgress(ProgressBar progress, int start, int end) {
        ObjectAnimator anim = ObjectAnimator.ofInt(progress, "progress", start,end);
        anim.setDuration(1000 * (end - start)/100);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
    }

    @Override
    public void openOrCloseInfoOutput(Context context, RelativeLayout view, Button button, boolean isOpen, int position) {
        this.view.openOrCloseInfo(view, button, isOpen, position);
    }

    @Override
    public void runAnimationItem(Context context, View view) {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.item_move);
        view.startAnimation(anim);
    }

    @Override
    public void runAnimationItemOpenOrClose(Context context, View view, boolean isOpen) {
        if(isOpen) {
//            Animation anim = AnimationUtils.loadAnimation(context, R.anim.open_view);
//            view.startAnimation(anim);
            ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationX", -90.0f, 0f);
            view.setPivotX(view.getMeasuredWidth() / 2);
            view.setPivotY(0);
            animation.setDuration(300);
            animation.setRepeatCount(0);
            animation.setInterpolator(new AccelerateDecelerateInterpolator());
            animation.start();
        } else {
//            ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationX", 0f, -90.0f);
//            view.setPivotX(view.getMeasuredWidth() / 2);
//            view.setPivotY(0);
//            animation.setDuration(300);
//            animation.setRepeatCount(0);
//            animation.setInterpolator(new AccelerateDecelerateInterpolator());
//            animation.start();
        }
    }

}

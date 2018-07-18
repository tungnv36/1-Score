package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Presenter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanEntity;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interactor.LoanRequestInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interface.LoanRequestInterface;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Wireframe.LoanRequestWireframe;
import a1_score.tima.vn.a1_score_viper.R;

public class LoanRequestPresenter implements LoanRequestInterface.Presenter, LoanRequestInterface.InteractorOutput {

    private LoanRequestInterface.View mView;
    private LoanRequestInterface.InteractorInput mInteractorInput;
    private LoanRequestInterface.Wireframe mWireframe;

    public LoanRequestPresenter(LoanRequestInterface.View view) {
        mView = view;
        mInteractorInput = new LoanRequestInteractor(view, this);
        mWireframe = new LoanRequestWireframe();
    }

    @Override
    public void initAvatar() {
        mInteractorInput.initAvatar();
    }

    @Override
    public void initData() {
        mInteractorInput.initData();
    }

    @Override
    public void getLoanCreditPackage() {
        mInteractorInput.getLoanCreditPackage();
    }

    @Override
    public void goToLoanRegistration(LoanEntity loanEntity) {
        mInteractorInput.goToLoanRegistration(loanEntity);
    }

    @Override
    public void initAnimationLogo(ImageView view) {
        mInteractorInput.initAnimationLogo(view);
    }

    @Override
    public void setupAnimationProgress(ProgressBar progress, int start, int end) {
        mInteractorInput.setupAnimationProgress(progress, start, end);
    }

    @Override
    public void openOrCloseInfo(Context context, RelativeLayout view, Button button, boolean isOpen, int position) {
        mInteractorInput.openOrCloseInfo(context, view, button, isOpen, position);
    }

    @Override
    public void setupAnimationItem(Context context, View view) {
        mInteractorInput.setupAnimationItem(context, view);
    }

    @Override
    public void setupAnimationOpenOrClose(Context context, View view, boolean isOpen) {
        mInteractorInput.setupAnimationItemOpenOrClose(context, view, isOpen);
    }

    @Override
    public void onDestroy() {
        mInteractorInput.unRegister();
        mInteractorInput = null;
        mView.onDestroy();
        mView = null;
        mWireframe = null;
    }

    @Override
    public void initAvatarOutput(Bitmap bmp) {
        mView.initAvatar(bmp);
    }

    @Override
    public void initDataOutput(String fullName, int level, long score) {
        mView.initData(fullName, level, score);
    }

    @Override
    public void getLoanCreditPackageSuccess(List<LoanResponse.LoanCreditPackagesEntity> loanCreditPackagesEntities) {
        mView.getLoanCreditPackageSuccess(loanCreditPackagesEntities);
    }

    @Override
    public void getLoanCreditPackageFail(String err) {
        mView.getLoanCreditPackageFail(err);
    }

    @Override
    public void goToLoanRegistrationOutput(LoanEntity loanEntity) {
        mWireframe.goToLoanRegistration((Activity)mView, loanEntity);
    }

    @Override
    public void runAnimationLogo(ImageView view) {
        Animation anim = AnimationUtils.loadAnimation((Context) mView, R.anim.scale_logo);
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
        mView.openOrCloseInfo(view, button, isOpen, position);
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

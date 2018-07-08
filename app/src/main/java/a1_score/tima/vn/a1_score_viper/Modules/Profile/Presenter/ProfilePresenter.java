package a1_score.tima.vn.a1_score_viper.Modules.Profile.Presenter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.Interactor.ProfileInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.Interface.ProfileInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.Wireframe.ProfileWireframe;
import a1_score.tima.vn.a1_score_viper.R;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class ProfilePresenter implements ProfileInterface.Presenter, ProfileInterface.InteractorOutput {

    private ProfileInterface.View mView;
    private ProfileInterface.InteractorInput mInteractorInput;
    private ProfileInterface.Wireframe mWireframe;

    public ProfilePresenter(ProfileInterface.View view) {
        mView = view;
        mInteractorInput = new ProfileInteractor(view, this);
        mWireframe = new ProfileWireframe();
    }

    @Override
    public void initData() {
        mInteractorInput.initData();
    }

    @Override
    public void initAvatar() {
        mInteractorInput.initAvatar();
    }

    @Override
    public void takePhoto(int type, int imageType) {
        mInteractorInput.takePhoto(type, imageType);
    }

    @Override
    public void updateImage(int type, int imageType, String filePath) {
        mInteractorInput.updateImage(type, imageType, filePath);
    }

    @Override
    public void goToUpdateProfile() {
        mInteractorInput.goToUpdateProfile();
    }

    @Override
    public void goToUpdateJob() {
        mInteractorInput.goToUpdateJob();
    }

    @Override
    public void goToUpdateFamily() {
        mInteractorInput.goToUpdateFamily();
    }

    @Override
    public void goToUpdateSocialNetwork() {
        mInteractorInput.goToUpdateSocialNetwork();
    }

    @Override
    public void goToUpdatePapers() {
        mInteractorInput.goToUpdatePapers();
    }

    @Override
    public void initAnimationLogo(ImageView view) {
        mInteractorInput.initAnimationLogo(view);
    }

    @Override
    public void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end) {
        mInteractorInput.setupAnimationSeekBar(seekBar, start, end);
    }

    @Override
    public void setupAnimationProgress(ProgressBar progress, int start, int end) {
        mInteractorInput.setupAnimationProgress(progress, start, end);
    }

    @Override
    public void setupAnimationPress(Context context, View view) {
        mInteractorInput.setupAnimationPress(context, view);
    }

    @Override
    public void onDestroy() {
        mInteractorInput.unRegister();
        mInteractorInput = null;
        mView = null;
        mWireframe = null;
    }

    @Override
    public void initAvatarOutput(Bitmap bmp) {
        if(bmp != null) {
            Bitmap imageRounded = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
            Canvas canvas = new Canvas(imageRounded);
            Paint mpaint = new Paint();
            mpaint.setAntiAlias(true);
            mpaint.setShader(new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            canvas.drawRoundRect((new RectF(0, 0, bmp.getWidth(), bmp.getHeight())), bmp.getWidth() / 2, bmp.getHeight() / 2, mpaint);
            mView.initAvatar(imageRounded);
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(((Context)mView).getResources(), R.drawable.avatar);
            mView.initAvatar(bitmap);
        }
    }

    @Override
    public void initData(LoginResponse.UserEntity userEntity) {
        mView.initData(userEntity);
    }

    @Override
    public void takePhotoOutput(int type, int imageType) {
        mWireframe.goToCamera((Activity)mView, type, imageType);
    }

    @Override
    public void updateImageOutput(int type, int imageType, Bitmap img) {
        Bitmap imageRounded = Bitmap.createBitmap(img.getWidth(), img.getHeight(), img.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(img, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, img.getWidth(), img.getHeight())), img.getWidth() / 2, img.getHeight() / 2, mpaint);
        mView.updateImage(imageType, imageRounded);
    }

    @Override
    public void updateImageFailed(String err) {
        mView.updateImageFailed(err);
    }

    @Override
    public void goToUpdateProfileOutput() {
        mWireframe.goToUpdateProfile((Activity)mView);
    }

    @Override
    public void goToUpdateJobOutput() {
        mWireframe.goToUpdateJob((Activity)mView);
    }

    @Override
    public void goToUpdateFamilyOutput() {
        mWireframe.goToUpdateFamily((Activity)mView);
    }

    @Override
    public void goToUpdateSocialNetworkOutput() {
        mWireframe.goToUpdateSocialNetwork((Activity)mView);
    }

    @Override
    public void goToUpdatePapersOutput() {
        mWireframe.goToUpdatePapers((Activity)mView);
    }

    @Override
    public void runAnimationLogo(ImageView view) {
        Animation anim = AnimationUtils.loadAnimation((Context) this.mView, R.anim.scale_logo);
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

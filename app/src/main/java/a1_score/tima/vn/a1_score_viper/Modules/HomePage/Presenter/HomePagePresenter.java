package a1_score.tima.vn.a1_score_viper.Modules.HomePage.Presenter;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interactor.HomePageInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interface.HomePageInterface;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Wireframe.HomePageWireframe;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResponse;
import a1_score.tima.vn.a1_score_viper.R;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class HomePagePresenter implements HomePageInterface.Presenter, HomePageInterface.InteractorOutput {

    private HomePageInterface.InteractorInput mInteractorInput;
    private HomePageInterface.Wireframe mWireframe;
    private HomePageInterface.View mView;

    public HomePagePresenter(HomePageInterface.View view) {
        mInteractorInput = new HomePageInteractor(view,this);
        mWireframe = new HomePageWireframe();
        mView = view;
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
    public void initAnimationLogo(ImageView view) {
        mInteractorInput.initAnimationLogo(view);
    }

    @Override
    public void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end) {
        mInteractorInput.setupAnimationSeekBar(seekBar, start, end);
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
    public void goToProfile() {
        mInteractorInput.goToProfile();
    }

    @Override
    public void goToLoanRequest() {
        mInteractorInput.goToLoanRequest();
    }

    @Override
    public void goToIntroduceFriends() {
        mInteractorInput.goToIntroduceFriends();
    }

    @Override
    public void goToSetting() {
        mInteractorInput.goToSetting();
    }

    @Override
    public void setupAnimationPress(Context context, View view) {
        mInteractorInput.setupAnimationPress(context, view);
    }

    @Override
    public void setupAnimationSupport(Context context, View view, int animOpen, int animClose) {
        mInteractorInput.setupAnimationSupport(context, view, animOpen, animClose);
    }

    @Override
    public void callSupport(Context context, String phoneNumber) {
        mInteractorInput.callSupport(context, phoneNumber);
    }

    @Override
    public void onDestroy() {
        mInteractorInput.unRegister();
        mInteractorInput = null;
        mWireframe = null;
        mView = null;
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
    public void initDataOutput(LoginResponse.UserEntity userEntity) {
        mView.initData(userEntity);
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
    public void goToProfileOutput() {
        mWireframe.goToProfile((Activity)mView);
    }

    @Override
    public void goToLoanRequestOutput() {
        mWireframe.goToLoanRequest((Activity)mView);
    }

    @Override
    public void goToIntroduceFriendsOutput() {
        mWireframe.goToIntroduceFriends((Activity)mView);
    }

    @Override
    public void goToSettingOutput() {
        mWireframe.goToSetting((Activity)mView);
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
            mWireframe.callSupportOutput(context, phoneNumber);
        }else{
            mView.callSupportFailed(context.getString(R.string.permission_fail));
        }
    }

}

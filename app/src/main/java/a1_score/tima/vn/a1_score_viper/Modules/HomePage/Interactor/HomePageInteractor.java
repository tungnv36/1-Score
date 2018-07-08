package a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interactor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.DataStore.HomePageDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interface.HomePageInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class HomePageInteractor implements HomePageInterface.InteractorInput {

    private HomePageInterface.InteractorOutput mInteractorOutput;
    private HomePageInterface.View mView;
    private HomePageInterface.DataStore mDataStore;

    public HomePageInteractor(HomePageInterface.View view, HomePageInterface.InteractorOutput mInteractorOutput) {
        this.mInteractorOutput = mInteractorOutput;
        mDataStore = HomePageDataStore.getInstance(view);
        mView = view;
    }

    @Override
    public void initData() {
        mInteractorOutput.initDataOutput(mDataStore.getUser());
    }

    @Override
    public void initAvatar() {
        String filePath = Environment.getExternalStorageDirectory()
                + File.separator + Constant.ROOT_FOLDER + File.separator
                + Constant.PHOTO_FOLDER + File.separator + mDataStore.getUserName() + "_avatar.jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        mInteractorOutput.initAvatarOutput(bitmap);
    }

    @Override
    public void initAnimationLogo(ImageView view) {
        mInteractorOutput.runAnimationLogo(view);
    }

    @Override
    public void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end) {
        mInteractorOutput.runAnimationSeekBar(seekBar, start, end);
    }

    @Override
    public void takePhoto(int type, int imageType) {
        mInteractorOutput.takePhotoOutput(type, imageType);
    }

    @Override
    public void updateImage(final int type, final int imageType, String filePath) {
        final Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if(bitmap != null) {
            Bitmap bmp = Commons.rotateImage(bitmap, -90);
            List<Integer> lstCameraSize = Commons.getCropSize((Activity)mView, type, bmp);
            if(lstCameraSize != null) {
                final Bitmap bmpCrop = Bitmap.createBitmap(bmp, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));

                ImageProfileRequest imageProfileRequest = new ImageProfileRequest(mDataStore.getUserName(), Commons.convertBitmapToBase64(bmpCrop), "avatar");
                mDataStore.uploadImage(new OnResponse<String, ImageProfileResponse>() {
                    @Override
                    public void onResponseSuccess(String tag, String rs, ImageProfileResponse extraData) {
                        if (extraData != null && extraData.getStatuscode() == 200) {
                            mDataStore.saveImageToLocal(mDataStore.getUserName() + "_avatar.jpg", bmpCrop);
                            mDataStore.saveImageToDB(extraData, mDataStore.getUserName() + "_avatar", mDataStore.getUserName(), "AVATAR");
                            mInteractorOutput.updateImageOutput(type, imageType, bmpCrop);
                        } else {
                            mInteractorOutput.updateImageFailed(rs);
                        }
                    }

                    @Override
                    public void onResponseError(String tag, String message) {
                        mInteractorOutput.updateImageFailed(message);
                    }
                }, "Bearer " + mDataStore.getToken(), imageProfileRequest);
            } else {
                mInteractorOutput.updateImageFailed("Lỗi xử lý ảnh");
            }
        } else {
            mInteractorOutput.updateImageFailed("Lỗi tải ảnh");
        }
    }

    @Override
    public void goToProfile() {
        mInteractorOutput.goToProfileOutput();
    }

    @Override
    public void goToLoanRequest() {
        mInteractorOutput.goToLoanRequestOutput();
    }

    @Override
    public void goToIntroduceFriends() {
        mInteractorOutput.goToIntroduceFriendsOutput();
    }

    @Override
    public void goToSetting() {
        mInteractorOutput.goToSettingOutput();
    }

    @Override
    public void setupAnimationPress(Context context, View view) {
        mInteractorOutput.runAnimationPress(context, view);
    }

    @Override
    public void setupAnimationSupport(Context context, View view, int animOpen, int aimClose) {
        mInteractorOutput.runAnimationSupport(context, view, animOpen, aimClose);
    }

    @Override
    public void callSupport(Context context, String phoneNumber) {
        if (Build.VERSION.SDK_INT < 23) {
            mInteractorOutput.callSupportOutput(context, phoneNumber);
        }else {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                mInteractorOutput.callSupportOutput(context, phoneNumber);
            }else {
                final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                ActivityCompat.requestPermissions((Activity) context, PERMISSIONS_STORAGE, 9);
            }
        }
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
        mDataStore = null;
    }

}

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
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class HomePageInteractor implements HomePageInterface.InteractorInput {

    private HomePageInterface.InteractorOutput interactorOutput;
    private HomePageInterface.View view;
    private HomePageInterface.DataStore dataStore;

    public HomePageInteractor(HomePageInterface.View view, HomePageInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        dataStore = HomePageDataStore.getInstance(view);
        this.view = view;
    }

    @Override
    public void initData() {
        interactorOutput.initDataOutput(dataStore.getUser());
    }

    @Override
    public void initAvatar() {
        String filePath = Environment.getExternalStorageDirectory()
                + File.separator + Constant.ROOT_FOLDER + File.separator
                + Constant.PHOTO_FOLDER + File.separator + dataStore.getUserName() + "_avatar.jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        interactorOutput.initAvatarOutput(bitmap);
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
    public void takePhoto(int type, int imageType) {
        interactorOutput.takePhotoOutput(type, imageType);
    }

    @Override
    public void updateImage(final int type, final int imageType, String filePath) {
        final Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if(bitmap != null) {
            Bitmap bmp = Commons.rotateImage(bitmap, -90);
            List<Integer> lstCameraSize = Commons.getCropSize((Activity)view, type, bmp);
            if(lstCameraSize != null) {
                final Bitmap bmpCrop = Bitmap.createBitmap(bmp, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));

                UploadImageEntity uploadImageEntity = new UploadImageEntity(dataStore.getUserName(), Commons.convertBitmapToBase64(bmpCrop), "avatar");
                dataStore.uploadImage(new OnResponse<String, UploadImageResultEntity>() {
                    @Override
                    public void onResponseSuccess(String tag, String rs, UploadImageResultEntity extraData) {
                        if (extraData != null && extraData.getStatuscode() == 200) {
                            dataStore.saveImageToLocal(dataStore.getUserName() + "_avatar.jpg", bmpCrop);
                            dataStore.saveImageToDB(extraData, dataStore.getUserName() + "_avatar", dataStore.getUserName(), "AVATAR");
                            interactorOutput.updateImageOutput(type, imageType, bmpCrop);
                        } else {
                            interactorOutput.updateImageFailed(rs);
                        }
                    }

                    @Override
                    public void onResponseError(String tag, String message) {
                        interactorOutput.updateImageFailed(message);
                    }
                }, "Bearer " + dataStore.getToken(), uploadImageEntity);
            } else {
                interactorOutput.updateImageFailed("Lỗi xử lý ảnh");
            }
        } else {
            interactorOutput.updateImageFailed("Lỗi tải ảnh");
        }
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
    public void goToSetting() {
        interactorOutput.goToSettingOutput();
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
        dataStore = null;
    }

}

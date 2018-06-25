package a1_score.tima.vn.a1_score_viper.Modules.Profile.Interactor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.DataStore.ProfileDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.Interface.ProfileInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class ProfileInteractor implements ProfileInterface.InteractorInput {

    private ProfileInterface.InteractorOutput interactorOutput;
    private ProfileInterface.View view;
    private ProfileDataStore dataStore;

    public ProfileInteractor(ProfileInterface.View view, ProfileInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        this.view = view;
        dataStore = ProfileDataStore.getInstance(view);
    }

    @Override
    public void initData() {
        interactorOutput.initData(dataStore.getUser());
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
        dataStore = null;
    }

}

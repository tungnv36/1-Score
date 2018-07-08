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
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class ProfileInteractor implements ProfileInterface.InteractorInput {

    private ProfileInterface.InteractorOutput mInteractorOutput;
    private ProfileInterface.View mView;
    private ProfileDataStore mDataStore;

    public ProfileInteractor(ProfileInterface.View view, ProfileInterface.InteractorOutput mInteractorOutput) {
        this.mInteractorOutput = mInteractorOutput;
        mView = view;
        mDataStore = ProfileDataStore.getInstance(view);
    }

    @Override
    public void initData() {
        mInteractorOutput.initData(mDataStore.getUser());
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
    public void goToUpdateProfile() {
        mInteractorOutput.goToUpdateProfileOutput();
    }

    @Override
    public void goToUpdateJob() {
        mInteractorOutput.goToUpdateJobOutput();
    }

    @Override
    public void goToUpdateFamily() {
        mInteractorOutput.goToUpdateFamilyOutput();
    }

    @Override
    public void goToUpdateSocialNetwork() {
        mInteractorOutput.goToUpdateSocialNetworkOutput();
    }

    @Override
    public void goToUpdatePapers() {
        mInteractorOutput.goToUpdatePapersOutput();
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
    public void setupAnimationProgress(ProgressBar progress, int start, int end) {
        mInteractorOutput.runAnimationProgress(progress, start, end);
    }

    @Override
    public void setupAnimationPress(Context context, android.view.View view) {
        mInteractorOutput.runAnimationPress(context, view);
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
        mDataStore = null;
    }

}

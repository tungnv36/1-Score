package a1_score.tima.vn.a1_score_viper.Modules.Profile.Interface;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public interface ProfileInterface {
    //View
    interface View {
        void initData(LoginResultEntity.UserEntity userEntity);
        void initAvatar(Bitmap bmp);
        void updateImage(int imageType, Bitmap img);
        void updateImageFailed(String err);
    }
    //Presenter
    interface Presenter {
        void initData();
        void initAvatar();
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath);
        void goToUpdateProfile();
        void goToUpdateJob();
        void goToUpdateFamily();
        void goToUpdateSocialNetwork();
        void goToUpdatePapers();
        void initAnimationLogo(ImageView view);
        void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end);
        void setupAnimationProgress(ProgressBar progress, int start, int end);
        void setupAnimationPress(Context context, android.view.View view);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void initData();
        void initAvatar();
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath);
        void goToUpdateProfile();
        void goToUpdateJob();
        void goToUpdateFamily();
        void goToUpdateSocialNetwork();
        void goToUpdatePapers();
        void initAnimationLogo(ImageView view);
        void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end);
        void setupAnimationProgress(ProgressBar progress, int start, int end);
        void setupAnimationPress(Context context, android.view.View view);
        void unRegister();
    }

    interface InteractorOutput {
        void initAvatarOutput(Bitmap bmp);
        void initData(LoginResultEntity.UserEntity userEntity);
        void takePhotoOutput(int type, int imageType);
        void updateImageOutput(int type, int imageType, Bitmap img);
        void updateImageFailed(String err);
        void goToUpdateProfileOutput();
        void goToUpdateJobOutput();
        void goToUpdateFamilyOutput();
        void goToUpdateSocialNetworkOutput();
        void goToUpdatePapersOutput();
        void runAnimationLogo(ImageView view);
        void runAnimationSeekBar(CircularSeekBar seekBar, int start, int end);
        void runAnimationProgress(ProgressBar progress, int start, int end);
        void runAnimationPress(Context context, android.view.View view);
    }
    //Wireframe
    interface Wireframe {
        void goToUpdateProfile(Activity activity);
        void goToUpdateJob(Activity activity);
        void goToUpdateFamily(Activity activity);
        void goToUpdateSocialNetwork(Activity activity);
        void goToUpdatePapers(Activity activity);
        void goToCamera(Activity activity, int type, int imageType);
    }
    //DataStore
    interface DataStore {
        String getUserName();
        String getToken();
        LoginResultEntity.UserEntity getUser();
        void saveImageToLocal(String fineName, Bitmap bmp);
        void saveImageToDB(UploadImageResultEntity uploadImageResultEntity, String imageName, String username, String type);
        void uploadImage(final OnResponse<String, UploadImageResultEntity> m_Response, String token, UploadImageEntity uploadImageEntity);
    }
}

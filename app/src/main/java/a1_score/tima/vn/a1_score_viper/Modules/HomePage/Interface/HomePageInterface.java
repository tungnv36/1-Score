package a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interface;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public interface HomePageInterface {
    //View
    interface View {
        void onDestroy();
        void setProgressValue(int progress);
        void callSupportFailed(String err);
        void initData(LoginResultEntity.UserEntity userEntity);
        void initAvatar(Bitmap bmp);
        void updateImage(int imageType, Bitmap img);
        void updateImageFailed(String err);
    }
    //Presenter
    interface Presenter {
        void initData();
        void initAvatar();
        void initAnimationLogo(ImageView view);
        void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end);
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath);
        void goToProfile();
        void goToLoanRequest();
        void goToIntroduceFriends();
        void goToSetting();
        void setupAnimationPress(Context context, android.view.View view);
        void setupAnimationSupport(Context context, android.view.View view, int animOpen, int animClose);
        void callSupport(Context context, String phoneNumber);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void initData();
        void initAvatar();
        void initAnimationLogo(ImageView view);
        void setupAnimationSeekBar(CircularSeekBar seekBar, int start, int end);
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath);
        void goToProfile();
        void goToLoanRequest();
        void goToIntroduceFriends();
        void goToSetting();
        void setupAnimationPress(Context context, android.view.View view);
        void setupAnimationSupport(Context context, android.view.View view, int animOpen, int animClose);
        void callSupport(Context context, String phoneNumber);
        void unRegister();
    }

    interface InteractorOutput {
        void initAvatarOutput(Bitmap bmp);
        void initDataOutput(LoginResultEntity.UserEntity userEntity);
        void runAnimationLogo(ImageView view);
        void runAnimationSeekBar(CircularSeekBar seekBar, int start, int end);
        void takePhotoOutput(int type, int imageType);
        void updateImageOutput(int type, int imageType, Bitmap img);
        void updateImageFailed(String err);
        void goToProfileOutput();
        void goToLoanRequestOutput();
        void goToIntroduceFriendsOutput();
        void goToSettingOutput();
        void runAnimationPress(Context context, android.view.View view);
        void runAnimationSupport(Context context, android.view.View view, int animOpen, int animClose);
        void callSupportOutput(Context context, String phoneNumber);
    }
    //Wireframe
    interface Wireframe {
        void goToProfile(Activity activity);
        void goToLoanRequest(Activity activity);
        void goToIntroduceFriends(Activity activity);
        void goToSetting(Activity activity);
        void goToCamera(Activity activity, int type, int imageType);
        void callSupportOutput(Context context, String phoneNumber);
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

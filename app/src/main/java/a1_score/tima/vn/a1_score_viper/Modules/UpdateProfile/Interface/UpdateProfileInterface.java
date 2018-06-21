package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interface;

import android.app.Activity;
import android.graphics.Bitmap;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.HeaderEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UpdateProfileEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UpdateProfileResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;

public interface UpdateProfileInterface {
    //View
    interface View {
        void initImage(int type, Bitmap bitmap);//type = 1: cmnd truoc, type = 2: cmnd sau, type = 3: card
        void initDataSuccess(UpdateProfileEntity updateProfileEntity);
        void updateImage(int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateProfileFailed(String err);
        void updateProfileSuccess(String msg);
        void emptyField(String msg);
    }
    //Presenter
    interface Presenter {
        void initImage(int type, String name);
        void initData();
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath, String fileName);
        void updateProfile(String fullname, String date_of_birth, String id_number, String address, String bank_acc_number, String card_term, int sex);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void initImage(int type, String name);
        void initData();
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath, String fileName);
        void updateProfile(String fullname, String date_of_birth, String id_number, String address, String bank_acc_number, String card_term, int sex);
        void unRegister();
    }

    interface InteractorOutput {
        void initImageOutput(int type, Bitmap bitmap);
        void initDataOutput(UpdateProfileEntity updateProfileEntity);
        void takePhotoOutput(int type, int imageType);
        void updateImageOutput(int type, int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateProfileOutput(String msg);
        void updateProfileFailed(String err);
        void emptyField(String msg);
    }
    //Wireframe
    interface Wireframe {
        void gotoCamera(Activity activity, int type, int imageType);
    }
    //DataStore
    interface DataStore {
        String getUser();
        String getToken();
        int getImageID(String phone, String type);
        UpdateProfileEntity getData(String userName);
        void saveImageToLocal(String fineName, Bitmap bmp);
        void saveImageToDB(UploadImageResultEntity uploadImageResultEntity, String imageName, String username, String type);
        void saveProfileToDB(UpdateProfileEntity updateProfileEntity);
        void uploadImage(final OnResponse<String, UploadImageResultEntity> m_Response, String token, UploadImageEntity uploadImageEntity);
        void updateProfile(final OnResponse<String, UpdateProfileResultEntity> m_Response, String token, UpdateProfileEntity updateProfileEntity);
    }
}

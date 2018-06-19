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
        void updateImage(int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateProfileFailed(String err);
        void emptyField(String msg);
    }
    //Presenter
    interface Presenter {
        void initImage(int type, String name);
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath, String fileName);
        void updateProfile(String username, String fullname, String date_of_birth, String id_number, String address, String id_image_1, String id_image_2, String bank_acc_number, String card_term, String card_image);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void initImage(int type, String name);
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath, String fileName);
        void updateProfile(String username, String fullname, String date_of_birth, String id_number, String address, String id_image_1, String id_image_2, String bank_acc_number, String card_term, String card_image);
        void unRegister();
    }

    interface InteractorOutput {
        void initImageOutput(int type, Bitmap bitmap);
        void takePhotoOutput(int type, int imageType);
        void updateImageOutput(int type, int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateProfileOutput();
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
        void saveImageToLocal(String fineName, Bitmap bmp);
        void uploadImage(final OnResponse<String, UploadImageResultEntity> m_Response, String token, UploadImageEntity uploadImageEntity);
        void updateProfile(final OnResponse<String, UpdateProfileResultEntity> m_Response, String token, UpdateProfileEntity updateProfileEntity);
    }
}

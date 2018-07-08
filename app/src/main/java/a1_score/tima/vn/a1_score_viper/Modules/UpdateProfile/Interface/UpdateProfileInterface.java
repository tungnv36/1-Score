package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interface;

import android.app.Activity;
import android.graphics.Bitmap;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;

public interface UpdateProfileInterface {
    //View
    interface View {
        void initImage(int type, Bitmap bitmap);//type = 1: cmnd truoc, type = 2: cmnd sau, type = 3: card
        void initDataSuccess(ProfileRequest profileRequest);

        void updateImage(int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateProfileFailed(String err);
        void updateProfileSuccess(String msg);
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
        void initDataOutput(ProfileRequest profileRequest);

        void takePhotoOutput(int type, int imageType);
        void updateImageOutput(int type, int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateProfileSuccess(String msg);
        void updateProfileFailed(String err);
    }
    //Wireframe
    interface Wireframe {
        void gotoCamera(Activity activity, int type, int imageType);
    }
    //DataStore
    interface DataStore {
        String getUser();
        String getFullName();
        String getToken();
        int getImageID(String phone, String type);
        ProfileRequest getData(String userName);

        void updateFullName(String fullname);
        void saveImageToLocal(String fineName, Bitmap bmp);
        void saveImageToDB(ImageProfileResponse imageProfileResponse, String imageName, String username, String type);
        void saveProfileToDB(ProfileRequest profileRequest);
        void uploadImage(final OnResponse<String, ImageProfileResponse> m_Response, String token, ImageProfileRequest imageProfileRequest);
        void updateProfile(final OnResponse<String, ProfileResponse> m_Response, String token, ProfileRequest profileRequest);
    }
}

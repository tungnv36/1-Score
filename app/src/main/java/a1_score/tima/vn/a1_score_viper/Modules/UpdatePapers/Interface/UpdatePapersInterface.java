package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface;

import android.app.Activity;
import android.graphics.Bitmap;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;

public interface UpdatePapersInterface {
    //View
    interface View {
        void updateList(int position, Bitmap img);
        void updateListFailed(String err);
    }
    //Presenter
    interface Presenter {
        void takePhoto(int type);
        void updateImage(int type, int position, int id, String filePath);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void takePhoto(int type);
        void updateImage(int type, int position, int id, String filePath);
        void unRegister();
    }

    interface InteractorOutput {
        void takePhotoOutput(int type);
        void updateImageOutput(int type, int position, int id, Bitmap img);
        void updateImageFailed(String err);
    }
    //Wireframe
    interface Wireframe {
        void gotoCamera(Activity activity, int type);
    }
    //DataStore
    interface DataStore {
        String getUser();
        String getFullName();
        String getToken();
        int getImageID(String phone, String type);

        void saveImageToLocal(String fineName, Bitmap bmp);
        void saveImageToDB(ImageProfileResponse imageProfileResponse, String imageName, String username, String type);
        void uploadImage(final OnResponse<String, ImageProfileResponse> m_Response, String token, ImageProfileRequest imageProfileRequest);
    }
}

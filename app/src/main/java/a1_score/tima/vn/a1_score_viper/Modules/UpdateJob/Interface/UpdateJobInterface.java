package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interface;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;

public interface UpdateJobInterface {
    //View
    interface View {
        void initImage(int type, Bitmap bitmap);//type = 1: cv, type = 2: hop dong, type = 3: bang luong

        void updateImage(int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateJobSuccess(String msg);
        void updateJobFailed(String err);
    }
    //Presenter
    interface Presenter {
        void initImage(int type, String name);

        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath, String fileName);
        void updateJob(int jobID, String companyName, String companyAddress, int positionID, int salaryID, List<ColleagueEntity> colleagueEntities);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void initImage(int type, String name);

        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath, String fileName);
        void updateJob(int jobID, String companyName, String companyAddress, int positionID, int salaryID, List<ColleagueEntity> colleagueEntities);

        void unRegister();
    }

    interface InteractorOutput {
        void initImageOutput(int type, Bitmap bitmap);

        void takePhotoOutput(int type, int imageType);
        void updateImageOutput(int type, int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateJobSuccess(String msg);
        void updateJobFailed(String err);
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

        void updateFullName(String fullname);
        void saveImageToLocal(String fineName, Bitmap bmp);
        void saveImageToDB(UploadImageResultEntity uploadImageResultEntity, String imageName, String username, String type);
        void uploadImage(final OnResponse<String, UploadImageResultEntity> m_Response, String token, UploadImageEntity uploadImageEntity);
    }
}

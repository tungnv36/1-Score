package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.ImagesEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.PapersEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.PapersResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;

public interface UpdatePapersInterface {
    //View
    interface View {
        void getImageTypeSuccess(List<PapersEntity> papersEntities);
        void getImageTypeFail(String err);
        void getImagesSeccess(List<ImagesEntity> imageEntities);

        void updateList(int position, Bitmap img);
        void updateListFailed(String err);
    }
    //Presenter
    interface Presenter {
        void getImageType();
        void getImages();

        void takePhoto(int type);
        void updateImage(int type, int position, String filePath, int typeId);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void getImageType();
        void getImages();

        void takePhoto(int type);
        void updateImage(int type, int position, String filePath, int typeId);
        void unRegister();
    }

    interface InteractorOutput {
        void getImagesSeccess(List<ImagesEntity> imageEntities);
        void getImageTypeSuccess(List<PapersEntity> papersEntities);
        void getImageTypeFail(String err);

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
        void getImageType(final OnResponse<String, PapersResponse> m_Response, String token);
        void deleteAllImageType();
        void updateImageTypeToDB(PapersResponse.ImageTypesEntity imageTypesEntity, String username, boolean done);
        List<PapersEntity> getImageType();
        List<ImagesEntity> getImages(String username);

        void saveImageToLocal(String fineName, Bitmap bmp);
        long saveImageToDB(ImageProfileResponse imageProfileResponse, String imageName, String username, String type);
        void updateImageTypeState(String username, int typeId, boolean done);
        void uploadImage(final OnResponse<String, ImageProfileResponse> m_Response, String token, ImageProfileRequest imageProfileRequest);
    }
}

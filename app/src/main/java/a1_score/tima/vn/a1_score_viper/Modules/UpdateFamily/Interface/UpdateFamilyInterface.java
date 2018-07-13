package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Interface;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyMembersRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyMembersResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.RelationshipDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.RelationshipResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;

public interface UpdateFamilyInterface {
    //View
    interface View {
        void initImage(int type, Bitmap bitmap);//type = 1: cv, type = 2: hop dong, type = 3: bang luong
        void initFamily(FamilyResponse.FamilyEntity familyEntity);
        void initRelationshipDictionary(List<RelationshipDictionaryResponse.RelationshipTypeEntity> relationshipTypeEntities);
        void initRelationship(String username, List<FamilyMembersResponse.RelationshipEntity> relationshipEntities);

        void updateImage(int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateFamilyFailed(String err);
        void updateFamilySuccess(String msg);
        void updateFamilyMembersSuccess(Dialog dialog, String msg, FamilyMembersRequest familyMembersRequest);
        void updateFamilyMembersFailed(Dialog dialog, String err);
    }
    //View adapter
    interface ViewAdapter {
        void initRelationshipType(int id);
    }
    //Presenter
    interface Presenter {
        void initImage(int type, String name);
        void getRelationshipDictionary();
        void initRelationship();
        void getFamily();
//        void getRelationshipType();

        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath, String fileName);
        void updateFamilyMembers(Dialog dialog, int relationshipTypeId, String name, String phone, String sbcName, String scName);
        void updateFamily(boolean isFamily, String nameVC, String phoneVC, int numberOfSon);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void initImage(int type, String name);
        void getRelationshipDictionary();
        void initRelationship();
        void getFamily();
//        void getRelationshipType();

        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath, String fileName);
        void updateFamilyMembers(Dialog dialog, int relationshipTypeId, String name, String phone, String sbcName, String scName);
        void updateFamily(boolean isFamily, String nameVC, String phoneVC, int numberOfSon);
        void unRegister();
    }

    interface InteractorOutput {
        void initImageOutput(int type, Bitmap bitmap);
        void getRelationshipDictionary(List<RelationshipDictionaryResponse.RelationshipTypeEntity> relationshipTypeEntities);
        void initRelationship(String username, List<FamilyMembersResponse.RelationshipEntity> relationshipEntities);
//        void initRelationshipType(int id);

        void getFamilyOutput(FamilyResponse.FamilyEntity familyEntity);
        void takePhotoOutput(int type, int imageType);
        void updateImageOutput(int type, int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateFamilySuccess(String msg);
        void updateFamilyFailed(String err);
        void updateFamilyMembersSuccess(Dialog dialog, String msg, FamilyMembersRequest familyMembersRequest);
        void updateFamilyMembersFailed(Dialog dialog, String err);
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
        List<FamilyMembersResponse.RelationshipEntity> getFamilyMember(String username);
        FamilyResponse.FamilyEntity getFamily(String username);
//        int getRelationshipType(int id);

        void saveImageToLocal(String fineName, Bitmap bmp);
        void saveImageToDB(ImageProfileResponse imageProfileResponse, String imageName, String username, String type);
        void updateFamilyToDB(String username, FamilyResponse.FamilyEntity familyEntity);
        void updateFamilyMembersToDB(String username, FamilyMembersResponse.RelationshipEntity relationshipEntity);
        void uploadImage(final OnResponse<String, ImageProfileResponse> m_Response, String token, ImageProfileRequest imageProfileRequest);
        void updateFamily(final OnResponse<String, FamilyResponse> m_Response, String token, FamilyRequest familyRequest);
        void updateFamilyMembers(final OnResponse<String, FamilyMembersResponse> m_Response, String token, FamilyMembersRequest familyMembersRequest);
        void getRelationshipDictionary(final OnResponse<String, RelationshipDictionaryResponse> m_Response, String token);
        void getRelationship(final OnResponse<String, RelationshipResponse> m_Response, String token, String username);
    }
}

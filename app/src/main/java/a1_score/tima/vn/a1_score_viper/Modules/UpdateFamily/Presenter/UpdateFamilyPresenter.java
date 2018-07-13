package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Presenter;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyMembersRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyMembersResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.RelationshipDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.RelationshipResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Interactor.UpdateFamilyInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Interface.UpdateFamilyInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Wireframe.UpdateFamilyWireframe;

public class UpdateFamilyPresenter implements UpdateFamilyInterface.Presenter, UpdateFamilyInterface.InteractorOutput {

    private UpdateFamilyInterface.View mView;
    private UpdateFamilyInterface.InteractorInput mInteractorInput;
    private UpdateFamilyInterface.Wireframe mWireframe;

    public UpdateFamilyPresenter(UpdateFamilyInterface.View view) {
        mView = view;
        mInteractorInput = new UpdateFamilyInteractor(view, this);
        mWireframe = new UpdateFamilyWireframe();
    }

    @Override
    public void initImage(int type, String name) {
        mInteractorInput.initImage(type, name);
    }

    @Override
    public void getRelationshipDictionary() {
        mInteractorInput.getRelationshipDictionary();
    }

    @Override
    public void initRelationship() {
        mInteractorInput.initRelationship();
    }

    @Override
    public void getFamily() {
        mInteractorInput.getFamily();
    }

    @Override
    public void takePhoto(int type, int imageType) {
        mInteractorInput.takePhoto(type, imageType);
    }

    @Override
    public void updateImage(int type, int imageType, String filePath, String fileName) {
        mInteractorInput.updateImage(type, imageType, filePath, fileName);
    }

    @Override
    public void updateFamilyMembers(Dialog dialog, int relationshipTypeId, String name, String phone, String sbcName, String scName) {
        mInteractorInput.updateFamilyMembers(dialog, relationshipTypeId, name, phone, sbcName, scName);
    }

    @Override
    public void updateFamily(boolean isFamily, String nameVC, String phoneVC, int numberOfSon) {
        mInteractorInput.updateFamily(isFamily, nameVC, phoneVC, numberOfSon);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput.unRegister();
        mInteractorInput = null;
    }

    @Override
    public void takePhotoOutput(int type, int imageType) {
        mWireframe.gotoCamera((Activity)mView, type, imageType);
    }

    @Override
    public void initImageOutput(int type, Bitmap bitmap) {
        mView.initImage(type, bitmap);
    }

    @Override
    public void getRelationshipDictionary(List<RelationshipDictionaryResponse.RelationshipTypeEntity> relationshipTypeEntities) {
        mView.initRelationshipDictionary(relationshipTypeEntities);
    }

    @Override
    public void initRelationship(String username, List<FamilyMembersResponse.RelationshipEntity> relationshipEntities) {
        mView.initRelationship(username, relationshipEntities);
    }

    @Override
    public void getFamilyOutput(FamilyResponse.FamilyEntity familyEntity) {
        mView.initFamily(familyEntity);
    }

    @Override
    public void updateImageOutput(int type, int imageType, Bitmap img) {
        mView.updateImage(imageType, img);
    }

    @Override
    public void updateImageFailed(String err) {
        mView.updateImageFailed(err);
    }

    @Override
    public void updateFamilySuccess(String msg) {
        mView.updateFamilySuccess(msg);
    }

    @Override
    public void updateFamilyFailed(String err) {
        mView.updateImageFailed(err);
    }

    @Override
    public void updateFamilyMembersSuccess(Dialog dialog, String msg, FamilyMembersRequest familyMembersRequest) {
        mView.updateFamilyMembersSuccess(dialog, msg, familyMembersRequest);
    }

    @Override
    public void updateFamilyMembersFailed(Dialog dialog, String err) {
        mView.updateFamilyMembersFailed(dialog, err);
    }
}

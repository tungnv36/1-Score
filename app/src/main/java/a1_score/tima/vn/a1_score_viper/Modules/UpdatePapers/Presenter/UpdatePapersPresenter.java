package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Presenter;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.ImagesEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.PapersEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interactor.UpdatePapersInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface.UpdatePapersInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.View.UpdatePapersView;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Wireframe.UpdatePapersWireframe;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;

public class UpdatePapersPresenter implements UpdatePapersInterface.Presenter, UpdatePapersInterface.InteractorOutput {

    private UpdatePapersInterface.View mView;
    private UpdatePapersInterface.InteractorInput mInteractorInput;
    private UpdatePapersInterface.Wireframe mWireframe;

    public UpdatePapersPresenter(UpdatePapersInterface.View view) {
        mView = view;
        mInteractorInput = new UpdatePapersInteractor(view, this);
        mWireframe = new UpdatePapersWireframe();
    }

    @Override
    public void getImageType() {
        mInteractorInput.getImageType();
    }

    @Override
    public void getImages() {
        mInteractorInput.getImages();
    }

    @Override
    public void takePhoto(int type) {
        mInteractorInput.takePhoto(type);
    }

    @Override
    public void updateImage(int type, int position, String filePath, int typeId) {
        mInteractorInput.updateImage(type, position, filePath, typeId);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput = null;
        mWireframe = null;
    }

    @Override
    public void getImagesSeccess(List<ImagesEntity> imageEntities) {
        mView.getImagesSeccess(imageEntities);
    }

    @Override
    public void getImageTypeSuccess(List<PapersEntity> papersEntities) {
        mView.getImageTypeSuccess(papersEntities);
    }

    @Override
    public void getImageTypeFail(String err) {
        mView.getImageTypeFail(err);
    }

    @Override
    public void takePhotoOutput(int type) {
        mWireframe.gotoCamera((Activity)mView, type);
    }

    @Override
    public void updateImageOutput(int type, int position, int imageType, Bitmap img) {
        mView.updateList(position, img);
    }

    @Override
    public void updateImageFailed(String err) {
        mView.updateListFailed(err);
    }

}

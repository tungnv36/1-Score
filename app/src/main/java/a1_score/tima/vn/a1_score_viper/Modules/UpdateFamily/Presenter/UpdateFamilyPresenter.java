package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Presenter;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Interactor.UpdateFamilyInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Interface.UpdateFamilyInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Wireframe.UpdateFamilyWireframe;

public class UpdateFamilyPresenter implements UpdateFamilyInterface.Presenter, UpdateFamilyInterface.InteractorOutput {

    private UpdateFamilyInterface.View view;
    private UpdateFamilyInterface.InteractorInput interactorInput;
    private UpdateFamilyInterface.Wireframe wireframe;

    public UpdateFamilyPresenter(UpdateFamilyInterface.View view) {
        this.view = view;
        interactorInput = new UpdateFamilyInteractor(this);
        wireframe = new UpdateFamilyWireframe();
    }

    @Override
    public void takePhoto(int type, int imageType) {
        interactorInput.takePhoto(type, imageType);
    }

    @Override
    public void updateImage(int type, int imageType, String filePath) {
        interactorInput.updateImage(type, imageType, filePath);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactorInput.unRegister();
        interactorInput = null;
    }

    @Override
    public void takePhotoOutput(int type, int imageType) {
        wireframe.gotoCamera((Activity)view, type, imageType);
    }

    @Override
    public void updateImageOutput(int type, int imageType, Bitmap img) {
        Bitmap bmp = Commons.rotateImage(img, 90);
        List<Integer> lstCameraSize = Commons.getCropSize((Activity)view, type, bmp);
        if(lstCameraSize != null) {
            Bitmap cropBmp = Bitmap.createBitmap(bmp, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));
            view.updateImage(imageType, cropBmp);
        } else {
            view.updateImage(imageType, bmp);
        }
    }

    @Override
    public void updateImageFailed(String err) {
        view.updateImageFailed(err);
    }
}

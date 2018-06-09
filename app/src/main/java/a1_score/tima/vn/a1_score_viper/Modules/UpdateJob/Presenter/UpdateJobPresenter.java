package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Presenter;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interactor.UpdateJobInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interface.UpdateJobInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Wireframe.UpdateJobWireframe;

public class UpdateJobPresenter implements UpdateJobInterface.Presenter, UpdateJobInterface.InteractorOutput {

    private UpdateJobInterface.View view;
    private UpdateJobInterface.InteractorInput interactorInput;
    private UpdateJobInterface.Wireframe wireframe;

    public UpdateJobPresenter(UpdateJobInterface.View view) {
        this.view = view;
        interactorInput = new UpdateJobInteractor(this);
        wireframe = new UpdateJobWireframe();
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
        interactorInput.unRegister();
        interactorInput = null;
        view = null;
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

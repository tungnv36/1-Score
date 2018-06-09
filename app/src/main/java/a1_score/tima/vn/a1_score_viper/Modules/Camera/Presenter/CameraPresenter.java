package a1_score.tima.vn.a1_score_viper.Modules.Camera.Presenter;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.Camera.Interactor.CameraInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.Interface.CameraInterface;

public class CameraPresenter implements CameraInterface.Presenter, CameraInterface.InteractorOutput {

    private CameraInterface.View view;
    private CameraInterface.InteractorInput interactorInput;

    public CameraPresenter(CameraInterface.View view) {
        this.view = view;
        interactorInput = new CameraInteractor(this);
    }

    @Override
    public void saveImage(byte[] data) {
        interactorInput.saveImage((Activity)view, data);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactorInput.unRegister();
        interactorInput = null;
    }

    @Override
    public void saveImageSuccess() {
        view.saveImageSuccess();
    }

    @Override
    public void saveImageFailed() {
        view.saveImageFailed();
    }
}

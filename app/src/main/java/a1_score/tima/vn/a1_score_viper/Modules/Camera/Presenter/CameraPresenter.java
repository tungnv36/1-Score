package a1_score.tima.vn.a1_score_viper.Modules.Camera.Presenter;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.Camera.Interactor.CameraInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.Interface.CameraInterface;

public class CameraPresenter implements CameraInterface.Presenter, CameraInterface.InteractorOutput {

    private CameraInterface.View mView;
    private CameraInterface.InteractorInput mInteractorInput;

    public CameraPresenter(CameraInterface.View view) {
        mView = view;
        mInteractorInput = new CameraInteractor(this);
    }

    @Override
    public void saveImage(byte[] data) {
        mInteractorInput.saveImage((Activity)mView, data);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput.unRegister();
        mInteractorInput = null;
    }

    @Override
    public void saveImageSuccess() {
        mView.saveImageSuccess();
    }

    @Override
    public void saveImageFailed() {
        mView.saveImageFailed();
    }
}

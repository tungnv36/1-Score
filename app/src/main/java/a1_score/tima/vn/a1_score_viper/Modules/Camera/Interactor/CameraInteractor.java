package a1_score.tima.vn.a1_score_viper.Modules.Camera.Interactor;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.Camera.DataStore.CameraDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.Interface.CameraInterface;

public class CameraInteractor implements CameraInterface.InteractorInput {

    private CameraInterface.InteractorOutput mInteractorOutput;
    private CameraInterface.DataStore mDataStore;

    public CameraInteractor(CameraInterface.InteractorOutput interactorOutput) {
        mInteractorOutput = interactorOutput;
        mDataStore = new CameraDataStore();
    }

    @Override
    public void saveImage(Activity activity, byte[] data) {
        boolean result = mDataStore.saveImage(activity, data);
        if(result) {
            mInteractorOutput.saveImageSuccess();
        } else {
            mInteractorOutput.saveImageFailed();
        }
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
        mDataStore = null;
    }

}

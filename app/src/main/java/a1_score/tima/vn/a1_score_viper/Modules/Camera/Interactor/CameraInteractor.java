package a1_score.tima.vn.a1_score_viper.Modules.Camera.Interactor;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Modules.Camera.DataStore.CameraDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Camera.Interface.CameraInterface;

public class CameraInteractor implements CameraInterface.InteractorInput {

    private CameraInterface.InteractorOutput interactorOutput;
    private CameraInterface.DataStore dataStore;

    public CameraInteractor(CameraInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        dataStore = new CameraDataStore();
    }

    @Override
    public void saveImage(Activity activity, byte[] data) {
        boolean result = dataStore.saveImage(activity, data);
        if(result) {
            interactorOutput.saveImageSuccess();
        } else {
            interactorOutput.saveImageFailed();
        }
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
        dataStore = null;
    }

}

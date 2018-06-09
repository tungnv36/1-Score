package a1_score.tima.vn.a1_score_viper.Modules.Camera.Interface;

import android.app.Activity;

public interface CameraInterface {
    //View
    interface View {
        void saveImageSuccess();
        void saveImageFailed();
    }
    //Presenter
    interface Presenter {
        void saveImage(byte[] data);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void saveImage(Activity activity, byte[] data);
        void unRegister();
    }

    interface InteractorOutput {
        void saveImageSuccess();
        void saveImageFailed();
    }
    //DataStore
    interface DataStore {
        boolean saveImage(Activity activity, byte[] data);
    }
}

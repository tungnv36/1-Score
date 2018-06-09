package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Interface;

import android.app.Activity;
import android.graphics.Bitmap;

public interface UpdateFamilyInterface {
    //View
    interface View {
        void updateImage(int imageType, Bitmap img);
        void updateImageFailed(String err);
    }
    //Presenter
    interface Presenter {
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath);
        void unRegister();
    }

    interface InteractorOutput {
        void takePhotoOutput(int type, int imageType);
        void updateImageOutput(int type, int imageType, Bitmap img);
        void updateImageFailed(String err);
    }
    //Wireframe
    interface Wireframe {
        void gotoCamera(Activity activity, int type, int imageType);
    }
}

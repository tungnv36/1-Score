package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface;

import android.app.Activity;
import android.graphics.Bitmap;

public interface UpdatePapersInterface {
    //View
    interface View {
        void updateList(int position, Bitmap img);
        void updateListFailed(String err);
    }
    //Presenter
    interface Presenter {
        void takePhoto(int type);
        void updateList(int type, int position, String filePath);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void takePhoto(int type);
        void updateList(int type, int position, String filePath);
        void unRegister();
    }

    interface InteractorOutput {
        void takePhotoOutput(int type);
        void updateListOutput(int type, int position, Bitmap img);
        void updateListFailed(String err);
    }
    //Wireframe
    interface Wireframe {
        void gotoCamera(Activity activity, int type);
    }
}

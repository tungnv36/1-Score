package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Presenter;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interactor.UpdatePapersInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface.UpdatePapersInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.View.UpdatePapersView;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Wireframe.UpdatePapersWireframe;

public class UpdatePapersPresenter implements UpdatePapersInterface.Presenter, UpdatePapersInterface.InteractorOutput {

    private UpdatePapersInterface.View view;
    private UpdatePapersInterface.InteractorInput interactorInput;
    private UpdatePapersInterface.Wireframe wireframe;

    public UpdatePapersPresenter(UpdatePapersInterface.View view) {
        this.view = view;
        interactorInput = new UpdatePapersInteractor(this);
        wireframe = new UpdatePapersWireframe();
    }

    @Override
    public void takePhoto(int type) {
        interactorInput.takePhoto(type);
    }

    @Override
    public void updateList(int type, int position, String filePath) {
        interactorInput.updateList(type, position, filePath);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactorInput = null;
        wireframe = null;
    }

    @Override
    public void takePhotoOutput(int type) {
        wireframe.gotoCamera((Activity)view, type);
    }

    @Override
    public void updateListOutput(int type, int position, Bitmap img) {
        Bitmap bmp = Commons.rotateImage(img, 90);
        List<Integer> lstCameraSize = Commons.getCropSize((Activity)view, type, bmp);
        if(lstCameraSize != null) {
            Bitmap cropBmp = Bitmap.createBitmap(bmp, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));
            view.updateList(position, cropBmp);
        } else {
            view.updateList(position, bmp);
        }
    }

    @Override
    public void updateListFailed(String err) {
        view.updateListFailed(err);
    }
}

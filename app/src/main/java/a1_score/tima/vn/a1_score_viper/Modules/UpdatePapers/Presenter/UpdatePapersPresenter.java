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

    private UpdatePapersInterface.View mView;
    private UpdatePapersInterface.InteractorInput mInteractorInput;
    private UpdatePapersInterface.Wireframe mWireframe;

    public UpdatePapersPresenter(UpdatePapersInterface.View view) {
        mView = view;
        mInteractorInput = new UpdatePapersInteractor(this);
        mWireframe = new UpdatePapersWireframe();
    }

    @Override
    public void takePhoto(int type) {
        mInteractorInput.takePhoto(type);
    }

    @Override
    public void updateList(int type, int position, String filePath) {
        mInteractorInput.updateList(type, position, filePath);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput = null;
        mWireframe = null;
    }

    @Override
    public void takePhotoOutput(int type) {
        mWireframe.gotoCamera((Activity)mView, type);
    }

    @Override
    public void updateListOutput(int type, int position, Bitmap img) {
        Bitmap bmp = Commons.rotateImage(img, 90);
        List<Integer> lstCameraSize = Commons.getCropSize((Activity)mView, type, bmp);
        if(lstCameraSize != null) {
            Bitmap cropBmp = Bitmap.createBitmap(bmp, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));
            mView.updateList(position, cropBmp);
        } else {
            mView.updateList(position, bmp);
        }
    }

    @Override
    public void updateListFailed(String err) {
        mView.updateListFailed(err);
    }
}

package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interactor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface.UpdatePapersInterface;

public class UpdatePapersInteractor implements UpdatePapersInterface.InteractorInput {

    private UpdatePapersInterface.InteractorOutput mInteractorOutput;

    public UpdatePapersInteractor(UpdatePapersInterface.InteractorOutput interactorOutput) {
        mInteractorOutput = interactorOutput;
    }

    @Override
    public void takePhoto(int type) {
        mInteractorOutput.takePhotoOutput(type);
    }

    @Override
    public void updateList(int type, int position, String filePath) {
        if(position != -1) {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            mInteractorOutput.updateListOutput(type, position, bitmap);
        } else {
            mInteractorOutput.updateListFailed("Tải ảnh lỗi!");
        }
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
    }

}

package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interactor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface.UpdatePapersInterface;

public class UpdatePapersInteractor implements UpdatePapersInterface.InteractorInput {

    private UpdatePapersInterface.InteractorOutput interactorOutput;

    public UpdatePapersInteractor(UpdatePapersInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
    }

    @Override
    public void takePhoto(int type) {
        interactorOutput.takePhotoOutput(type);
    }

    @Override
    public void updateList(int type, int position, String filePath) {
        if(position != -1) {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            interactorOutput.updateListOutput(type, position, bitmap);
        } else {
            interactorOutput.updateListFailed("Tải ảnh lỗi!");
        }
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
    }

}

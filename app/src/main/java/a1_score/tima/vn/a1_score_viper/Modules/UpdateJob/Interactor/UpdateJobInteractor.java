package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interactor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interface.UpdateJobInterface;

public class UpdateJobInteractor implements UpdateJobInterface.InteractorInput {

    private UpdateJobInterface.InteractorOutput interactorOutput;

    public UpdateJobInteractor(UpdateJobInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
    }

    @Override
    public void takePhoto(int type, int imageType) {
        interactorOutput.takePhotoOutput(type, imageType);
    }

    @Override
    public void updateImage(int type, int imageType, String filePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if(bitmap != null) {
            interactorOutput.updateImageOutput(type, imageType, bitmap);
        } else {
            interactorOutput.updateImageFailed("Lỗi tải ảnh");
        }
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
    }
}
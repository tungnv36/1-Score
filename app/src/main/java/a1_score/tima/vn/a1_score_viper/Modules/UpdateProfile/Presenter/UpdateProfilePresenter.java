package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interactor.UpdateProfileInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interface.UpdateProfileInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Wireframe.UpdateProfileWireframe;
import a1_score.tima.vn.a1_score_viper.R;

public class UpdateProfilePresenter implements UpdateProfileInterface.Presenter, UpdateProfileInterface.InteractorOutput {

    private UpdateProfileInterface.View view;
    private UpdateProfileInterface.InteractorInput interactorInput;
    private UpdateProfileInterface.Wireframe wireframe;

    public UpdateProfilePresenter(UpdateProfileInterface.View view) {
        this.view = view;
        interactorInput = new UpdateProfileInteractor(view, this);
        wireframe = new UpdateProfileWireframe();
    }

    @Override
    public void initImage(int type, String name) {
        interactorInput.initImage(type, name);
    }

    @Override
    public void initData() {
        interactorInput.initData();
    }

    @Override
    public void takePhoto(int type, int imageType) {
        interactorInput.takePhoto(type, imageType);
    }

    @Override
    public void updateImage(int type, int imageType, String filePath, String fileName) {
        interactorInput.updateImage(type, imageType, filePath, fileName);
    }

    @Override
    public void updateProfile(String fullname, String date_of_birth, String id_number, String address, String bank_acc_number, String card_term, int sex) {
        interactorInput.updateProfile(fullname, date_of_birth, id_number, address, bank_acc_number, card_term, sex);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactorInput.unRegister();
        interactorInput = null;
    }

    @Override
    public void initImageOutput(int type, Bitmap bitmap) {
        view.initImage(type, bitmap);
    }

    @Override
    public void initDataOutput(ProfileRequest profileRequest) {
        view.initDataSuccess(profileRequest);
    }

    @Override
    public void takePhotoOutput(int type, int imageType) {
        wireframe.gotoCamera((Activity)view, type, imageType);
    }

    @Override
    public void updateImageOutput(int type, int imageType, Bitmap img) {
        view.updateImage(imageType, img);
    }

    @Override
    public void updateImageFailed(String err) {
        view.updateImageFailed(err);
    }

    @Override
    public void updateProfileSuccess(String msg) {
        view.updateProfileSuccess(msg);
    }

    @Override
    public void updateProfileFailed(String err) {
        view.updateProfileFailed(err);
    }

}

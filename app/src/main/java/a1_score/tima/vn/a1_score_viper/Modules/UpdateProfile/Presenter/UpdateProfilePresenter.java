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
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileDictionatyResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interactor.UpdateProfileInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interface.UpdateProfileInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Wireframe.UpdateProfileWireframe;
import a1_score.tima.vn.a1_score_viper.R;

public class UpdateProfilePresenter implements UpdateProfileInterface.Presenter, UpdateProfileInterface.InteractorOutput {

    private UpdateProfileInterface.View mView;
    private UpdateProfileInterface.InteractorInput mInteractorInput;
    private UpdateProfileInterface.Wireframe mWireframe;

    public UpdateProfilePresenter(UpdateProfileInterface.View view) {
        this.mView = view;
        mInteractorInput = new UpdateProfileInteractor(view, this);
        mWireframe = new UpdateProfileWireframe();
    }

    @Override
    public void initImage(int type, String name) {
        mInteractorInput.initImage(type, name);
    }

    @Override
    public void initData() {
        mInteractorInput.initData();
    }

    @Override
    public void getDictionary() {
        mInteractorInput.getDictionary();
    }

    @Override
    public void takePhoto(int type, int imageType) {
        mInteractorInput.takePhoto(type, imageType);
    }

    @Override
    public void updateImage(int type, int imageType, String filePath, String fileName) {
        mInteractorInput.updateImage(type, imageType, filePath, fileName);
    }

    @Override
    public void updateProfile(String fullname, String date_of_birth, String id_number, String address, String bank_acc_number, String card_term, int sex) {
        mInteractorInput.updateProfile(fullname, date_of_birth, id_number, address, bank_acc_number, card_term, sex);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mInteractorInput.unRegister();
        mInteractorInput = null;
    }

    @Override
    public void initImageOutput(int type, Bitmap bitmap) {
        mView.initImage(type, bitmap);
    }

    @Override
    public void initDataOutput(ProfileRequest profileRequest) {
        mView.initDataSuccess(profileRequest);
    }

    @Override
    public void getBankOutput(List<ProfileDictionatyResponse.BanksEntity> banksEntities) {
        mView.initBank(banksEntities);
    }

    @Override
    public void takePhotoOutput(int type, int imageType) {
        mWireframe.gotoCamera((Activity)mView, type, imageType);
    }

    @Override
    public void updateImageOutput(int type, int imageType, Bitmap img) {
        mView.updateImage(imageType, img);
    }

    @Override
    public void updateImageFailed(String err) {
        mView.updateImageFailed(err);
    }

    @Override
    public void updateProfileSuccess(String msg) {
        mView.updateProfileSuccess(msg);
    }

    @Override
    public void updateProfileFailed(String err) {
        mView.updateProfileFailed(err);
    }

}

package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interactor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.DataStore.UpdateProfileDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.HeaderEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UpdateProfileEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UpdateProfileResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interface.UpdateProfileInterface;

public class UpdateProfileInteractor implements UpdateProfileInterface.InteractorInput {

    private UpdateProfileInterface.InteractorOutput interactorOutput;
    private UpdateProfileInterface.DataStore dataStore;
    private UpdateProfileInterface.View view;

    public UpdateProfileInteractor(UpdateProfileInterface.View view, UpdateProfileInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        dataStore = UpdateProfileDataStore.getInstance(view);
        this.view = view;
    }

    @Override
    public void initImage(int type, String name) {
        File fImage = new File(
                Environment.getExternalStorageDirectory()
                        + File.separator + Constant.ROOT_FOLDER + File.separator
                        + Constant.PHOTO_FOLDER + File.separator + dataStore.getUser() + name);
        if(fImage.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(fImage.getPath());
            if(bitmap != null) {
                interactorOutput.initImageOutput(type, bitmap);
            }
        }
    }

    @Override
    public void takePhoto(int type, int imageType) {
        interactorOutput.takePhotoOutput(type, imageType);
    }

    @Override
    public void updateImage(final int type, final int imageType, String filePath, String fileName) {
        final Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if(bitmap != null) {
            dataStore.saveImageToLocal(dataStore.getUser() + fileName, bitmap);

            UploadImageEntity uploadImageEntity = new UploadImageEntity(dataStore.getUser(), Commons.convertBitmapToBase64(bitmap));
            dataStore.uploadImage(new OnResponse<String, UploadImageResultEntity>() {
                @Override
                public void onResponseSuccess(String tag, String rs, UploadImageResultEntity extraData) {
                    if(extraData != null) {
                        interactorOutput.updateImageOutput(type, imageType, bitmap);
                    } else {
                        interactorOutput.updateImageFailed(rs);
                    }
                }

                @Override
                public void onResponseError(String tag, String message) {
                    interactorOutput.updateImageFailed(message);
                }
            }, "Bearer " + dataStore.getToken(), uploadImageEntity);
        } else {
            interactorOutput.updateImageFailed("Lỗi tải ảnh");
        }
    }

    @Override
    public void updateProfile(String username, String fullname, String date_of_birth, String id_number, String address, String id_image_1, String id_image_2, String bank_acc_number, String card_term, String card_image) {
        if(username.isEmpty()) {
            interactorOutput.emptyField("Bạn chưa nhập tài khoản");
            return;
        }
        if(fullname.isEmpty()) {
            interactorOutput.emptyField("Bạn chưa nhập tên");
            return;
        }
        if(date_of_birth.isEmpty()) {
            interactorOutput.emptyField("Bạn chưa nhập ngày sinh");
            return;
        }
        if(id_number.isEmpty()) {
            interactorOutput.emptyField("Bạn chưa nhập số CMND");
            return;
        }
        if(address.isEmpty()) {
            interactorOutput.emptyField("Bạn chưa nhập địa chỉ");
            return;
        }
        if(bank_acc_number.isEmpty()) {
            interactorOutput.emptyField("Bạn chưa nhập số tài khoản");
            return;
        }
        if(card_term.isEmpty()) {
            interactorOutput.emptyField("Bạn chưa nhập thời hạn thẻ");
            return;
        }

        UpdateProfileEntity updateProfileEntity = new UpdateProfileEntity(username, fullname, date_of_birth, id_number, address, id_image_1, id_image_2, bank_acc_number, card_term, card_image);
        dataStore.updateProfile(new OnResponse<String, UpdateProfileResultEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, UpdateProfileResultEntity extraData) {
                if(extraData != null) {
                    interactorOutput.updateProfileOutput();
                } else {
                    interactorOutput.updateImageFailed(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                interactorOutput.updateImageFailed(message);
            }
        },"Bearer " + dataStore.getToken(), updateProfileEntity);
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
        dataStore = null;
    }
}

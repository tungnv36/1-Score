package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interactor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.DataStore.UpdateProfileDataStore;
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
        try {
            File fImage = new File(
                    Environment.getExternalStorageDirectory()
                            + File.separator + Constant.ROOT_FOLDER + File.separator
                            + Constant.PHOTO_FOLDER + File.separator + dataStore.getUser() + name + ".jpg");
            if (fImage.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(fImage.getPath());
                if (bitmap != null) {
                    interactorOutput.initImageOutput(type, bitmap);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initData() {
        UpdateProfileEntity updateProfileEntity = dataStore.getData(dataStore.getUser());
        updateProfileEntity.setFullname(dataStore.getFullName());
        interactorOutput.initDataOutput(updateProfileEntity);
    }

    @Override
    public void takePhoto(int type, int imageType) {
        interactorOutput.takePhotoOutput(type, imageType);
    }

    @Override
    public void updateImage(final int type, final int imageType, String filePath, final String fileName) {
        final Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if(bitmap != null) {
            Bitmap bmp = Commons.rotateImage(bitmap, 90);
            List<Integer> lstCameraSize = Commons.getCropSize((Activity)view, type, bmp);
            if(lstCameraSize != null) {
                final Bitmap bmpCrop = Bitmap.createBitmap(bmp, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));

//                dataStore.saveImageToLocal(dataStore.getUser() + fileName + ".jpg", bmpCrop);
                UploadImageEntity uploadImageEntity = new UploadImageEntity(dataStore.getUser(), Commons.convertBitmapToBase64(bmpCrop), "");
                dataStore.uploadImage(new OnResponse<String, UploadImageResultEntity>() {
                    @Override
                    public void onResponseSuccess(String tag, String rs, UploadImageResultEntity extraData) {
                        if (extraData != null && extraData.getStatuscode() == 200) {
                            dataStore.saveImageToLocal(dataStore.getUser() + fileName + ".jpg", bmpCrop);
                            dataStore.saveImageToDB(extraData, fileName, dataStore.getUser(), getType(imageType));
                            interactorOutput.updateImageOutput(type, imageType, bmpCrop);
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
                interactorOutput.updateImageFailed("Lỗi xử lý ảnh");
            }
        } else {
            interactorOutput.updateImageFailed("Lỗi tải ảnh");
        }
    }

    private String getType(int type) {
        switch (type) {
            case 1:
                return "CMND_FRONT";
            case 2:
                return "CMND_BACK";
            case 3:
                return "ATM_CARD";
            default:
                return "";
        }
    }

    @Override
    public void updateProfile(final String fullname, String date_of_birth, String id_number, String address, String bank_acc_number, String card_term, int sex) {
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

        final UpdateProfileEntity updateProfileEntity = new UpdateProfileEntity();
        updateProfileEntity.setUsername(dataStore.getUser());
        updateProfileEntity.setFullname(fullname);
        updateProfileEntity.setDate_of_birth(date_of_birth);
        updateProfileEntity.setId_number(id_number);
        updateProfileEntity.setAddress(address);
        updateProfileEntity.setId_image_1(dataStore.getImageID(dataStore.getUser(), getType(1)));
        updateProfileEntity.setId_image_2(dataStore.getImageID(dataStore.getUser(), getType(2)));
        updateProfileEntity.setBank_acc_number(bank_acc_number);
        updateProfileEntity.setCard_term(card_term);
        updateProfileEntity.setCard_image(dataStore.getImageID(dataStore.getUser(), getType(3)));
        updateProfileEntity.setSex(sex);
        dataStore.updateProfile(new OnResponse<String, UpdateProfileResultEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, UpdateProfileResultEntity extraData) {
                if(extraData != null) {
                    dataStore.saveProfileToDB(updateProfileEntity);
                    dataStore.updateFullName(fullname);
                    interactorOutput.updateProfileOutput(extraData.getMessage());
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

package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interactor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.DataStore.UpdateProfileDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interface.UpdateProfileInterface;
import a1_score.tima.vn.a1_score_viper.R;

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
        ProfileRequest profileRequest = dataStore.getData(dataStore.getUser());
        profileRequest.setFullname(dataStore.getFullName());
        interactorOutput.initDataOutput(profileRequest);
    }

    @Override
    public void takePhoto(int type, int imageType) {
        interactorOutput.takePhotoOutput(type, imageType);
    }

    @Override
    public void updateImage(final int type, final int imageType, String filePath, final String fileName) {
        final Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if(bitmap != null) {
            Bitmap bmp = Commons.rotateImage(bitmap, 90);//Xoay ảnh sau khi chụp
            List<Integer> lstCameraSize = Commons.getCropSize((Activity)view, type, bmp);//Lấy toạ độ để crop ảnh
            if(lstCameraSize != null) {
                final Bitmap bmpCrop = Bitmap.createBitmap(bmp, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));//Crop ảnh theo khung

                ImageProfileRequest imageProfileRequest = new ImageProfileRequest(
                        dataStore.getUser(),
                        0,
                        Commons.convertBitmapToBase64(bmpCrop),
                        "");
                dataStore.uploadImage(new OnResponse<String, ImageProfileResponse>() {
                    @Override
                    public void onResponseSuccess(String tag, String rs, ImageProfileResponse extraData) {
                        if (extraData != null && extraData.getStatuscode() == 200) {
                            dataStore.saveImageToLocal(dataStore.getUser() + fileName + ".jpg", bmpCrop);//Lưu ảnh vào file manager
                            dataStore.saveImageToDB(extraData, fileName, dataStore.getUser(), getType(imageType));//Lưu thông tin ảnh vào db local
                            interactorOutput.updateImageOutput(type, imageType, bmpCrop);
                        } else {
                            interactorOutput.updateImageFailed(rs);
                        }
                    }

                    @Override
                    public void onResponseError(String tag, String message) {
                        interactorOutput.updateImageFailed(message);
                    }
                }, "Bearer " + dataStore.getToken(), imageProfileRequest);
            } else {
                interactorOutput.updateImageFailed(((Context)view).getString(R.string.err_handle_image));
            }
        } else {
            interactorOutput.updateImageFailed(((Context)view).getString(R.string.err_download_image));
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
            interactorOutput.updateProfileFailed(((Context)view).getString(R.string.err_fullname_empty));
            return;
        }
        if(date_of_birth.isEmpty()) {
            interactorOutput.updateProfileFailed(((Context)view).getString(R.string.err_birthday_empty));
            return;
        }
        if(id_number.isEmpty()) {
            interactorOutput.updateProfileFailed(((Context)view).getString(R.string.err_cmnd_empty));
            return;
        }
        if(address.isEmpty()) {
            interactorOutput.updateProfileFailed(((Context)view).getString(R.string.err_address_empty));
            return;
        }
        if(bank_acc_number.isEmpty()) {
            interactorOutput.updateProfileFailed(((Context)view).getString(R.string.err_number_acc_empty));
            return;
        }
        if(card_term.isEmpty()) {
            interactorOutput.updateProfileFailed(((Context)view).getString(R.string.err_card_term_empty));
            return;
        }

        final ProfileRequest profileRequest = new ProfileRequest();
        profileRequest.setUsername(dataStore.getUser());
        profileRequest.setFullname(fullname);
        profileRequest.setDateOfBirth(date_of_birth);
        profileRequest.setIdNumber(id_number);
        profileRequest.setAddress(address);
        profileRequest.setIdImage1(dataStore.getImageID(dataStore.getUser(), getType(1)));
        profileRequest.setIdImage2(dataStore.getImageID(dataStore.getUser(), getType(2)));
        profileRequest.setBankAccNumber(bank_acc_number);
        profileRequest.setCardTerm(card_term);
        profileRequest.setIdCardImage(dataStore.getImageID(dataStore.getUser(), getType(3)));
        profileRequest.setSex(sex);
        dataStore.updateProfile(new OnResponse<String, ProfileResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, ProfileResponse extraData) {
                if(extraData != null) {
                    dataStore.saveProfileToDB(profileRequest);//Lưu thông tin cá nhân vào db local
                    dataStore.updateFullName(fullname);//Cập nhật lại fullname trong file cấu hình
                    interactorOutput.updateProfileSuccess(extraData.getMessage());
                } else {
                    interactorOutput.updateProfileFailed(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                interactorOutput.updateProfileFailed(message);
            }
        },"Bearer " + dataStore.getToken(), profileRequest);
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
        dataStore = null;
    }
}

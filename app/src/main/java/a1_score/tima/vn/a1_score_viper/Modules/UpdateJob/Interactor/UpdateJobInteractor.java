package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interactor;

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
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.DataStore.UpdateJobDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interface.UpdateJobInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;
import a1_score.tima.vn.a1_score_viper.R;

public class UpdateJobInteractor implements UpdateJobInterface.InteractorInput {

    private UpdateJobInterface.InteractorOutput interactorOutput;
    private UpdateJobInterface.View view;
    private UpdateJobInterface.DataStore dataStore;

    public UpdateJobInteractor(UpdateJobInterface.View view, UpdateJobInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        this.view = view;
        dataStore = UpdateJobDataStore.getInstance(view);
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
    public void takePhoto(int type, int imageType) {
        interactorOutput.takePhotoOutput(type, imageType);
    }

    @Override
    public void updateImage(final int type, final int imageType, String filePath, final String fileName) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if(bitmap != null) {
            final Bitmap bmp = Commons.rotateImage(bitmap, 90);
//            List<Integer> lstCameraSize = Commons.getCropSize((Activity)view, type, bitmap);
//            if(lstCameraSize != null) {
//                final Bitmap bmpCrop = Bitmap.createBitmap(bitmap, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));

                UploadImageEntity uploadImageEntity = new UploadImageEntity(dataStore.getUser(), Commons.convertBitmapToBase64(bmp), "");
                dataStore.uploadImage(new OnResponse<String, UploadImageResultEntity>() {
                    @Override
                    public void onResponseSuccess(String tag, String rs, UploadImageResultEntity extraData) {
                        if (extraData != null && extraData.getStatuscode() == 200) {
                            dataStore.saveImageToLocal(dataStore.getUser() + fileName + ".jpg", bmp);
                            dataStore.saveImageToDB(extraData, fileName, dataStore.getUser(), getType(imageType));
                            interactorOutput.updateImageOutput(type, imageType, bmp);
                        } else {
                            interactorOutput.updateImageFailed(rs);
                        }
                    }

                    @Override
                    public void onResponseError(String tag, String message) {
                        interactorOutput.updateImageFailed(message);
                    }
                }, "Bearer " + dataStore.getToken(), uploadImageEntity);
//            } else {
//                interactorOutput.updateImageFailed("Lỗi xử lý ảnh");
//            }
        } else {
            interactorOutput.updateImageFailed("Lỗi tải ảnh");
        }
    }

    @Override
    public void updateJob(int jobID, String companyName, String companyAddress, int positionID, int salaryID, List<ColleagueEntity> colleagueEntities) {
        if(companyName.isEmpty()) {
            interactorOutput.updateJobFailed(((Context)view).getString(R.string.err_company_name_empty));
            return;
        }
        if(companyAddress.isEmpty()) {
            interactorOutput.updateJobFailed(((Context)view).getString(R.string.err_company_address_empty));
            return;
        }
        if(colleagueEntities.size() == 0) {
            interactorOutput.updateJobFailed(((Context)view).getString(R.string.err_company_colleague_empty));
            return;
        }
    }

    private String getType(int type) {
        switch (type) {
            case 1:
                return "CV";
            case 2:
                return "CONTRACT";
            case 3:
                return "SALARY_BOARD";
            default:
                return "";
        }
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
        view = null;
        dataStore = null;
    }
}

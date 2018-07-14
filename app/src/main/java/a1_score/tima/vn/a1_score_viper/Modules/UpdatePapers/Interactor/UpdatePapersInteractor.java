package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interactor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.DataStore.UpdatePapersDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interface.UpdatePapersInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import a1_score.tima.vn.a1_score_viper.R;

public class UpdatePapersInteractor implements UpdatePapersInterface.InteractorInput {

    private UpdatePapersInterface.InteractorOutput mInteractorOutput;
    private UpdatePapersInterface.DataStore mDataStore;
    private UpdatePapersInterface.View mView;

    public UpdatePapersInteractor(UpdatePapersInterface.View view, UpdatePapersInterface.InteractorOutput interactorOutput) {
        mInteractorOutput = interactorOutput;
        mView = view;
        mDataStore = UpdatePapersDataStore.getInstance(view);
    }

    @Override
    public void takePhoto(int type) {
        mInteractorOutput.takePhotoOutput(type);
    }

    @Override
    public void updateImage(final int type, final int position, final int id, String filePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if(position != -1 || bitmap != null) {
            if(type == 1) {//Ảnh chụp thẻ
                Bitmap bmp = Commons.rotateImage(bitmap, 90);//Xoay ảnh sau khi chụp
                List<Integer> lstCameraSize = Commons.getCropSize((Activity)mView, type, bmp);//Lấy toạ độ để crop ảnh
                if(lstCameraSize != null) {
                    final Bitmap bmpCrop = Bitmap.createBitmap(bmp, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));//Crop ảnh theo khung

                    ImageProfileRequest imageProfileRequest = new ImageProfileRequest(mDataStore.getUser(), Commons.convertBitmapToBase64(bmpCrop), "");
                    mDataStore.uploadImage(new OnResponse<String, ImageProfileResponse>() {
                        @Override
                        public void onResponseSuccess(String tag, String rs, ImageProfileResponse extraData) {
                            if (extraData != null && extraData.getStatuscode() == 200) {
                                mDataStore.saveImageToLocal(mDataStore.getUser() + "_" + id + ".jpg", bmpCrop);//Lưu ảnh vào file manager
                                mDataStore.saveImageToDB(extraData, mDataStore.getUser() + "_" + id, mDataStore.getUser(), String.valueOf(id));//Lưu thông tin ảnh vào db local
                                mInteractorOutput.updateImageOutput(type, position, id, bmpCrop);
                            } else {
                                mInteractorOutput.updateImageFailed(rs);
                            }
                        }

                        @Override
                        public void onResponseError(String tag, String message) {
                            mInteractorOutput.updateImageFailed(message);
                        }
                    }, "Bearer " + mDataStore.getToken(), imageProfileRequest);
                } else {
                    mInteractorOutput.updateImageFailed(((Context)mView).getString(R.string.err_handle_image));
                }
            } else {//Ảnh chụp giấy tờ
                final Bitmap bmp = Commons.rotateImage(bitmap, 90);
                ImageProfileRequest imageProfileRequest = new ImageProfileRequest(mDataStore.getUser(), Commons.convertBitmapToBase64(bmp), "");
                mDataStore.uploadImage(new OnResponse<String, ImageProfileResponse>() {
                    @Override
                    public void onResponseSuccess(String tag, String rs, ImageProfileResponse extraData) {
                        if (extraData != null && extraData.getStatuscode() == 200) {
                            mDataStore.saveImageToLocal(mDataStore.getUser() + "_" + id + ".jpg", bmp);//Lưu ảnh vào file manager
                            mDataStore.saveImageToDB(extraData, mDataStore.getUser() + "_" + id, mDataStore.getUser(), String.valueOf(id));//Lưu thông tin ảnh vào db local
                            mInteractorOutput.updateImageOutput(type, position, id, bmp);
                        } else {
                            mInteractorOutput.updateImageFailed(rs);
                        }
                    }

                    @Override
                    public void onResponseError(String tag, String message) {
                        mInteractorOutput.updateImageFailed(message);
                    }
                }, "Bearer " + mDataStore.getToken(), imageProfileRequest);
            }

//            final Bitmap bmp = Commons.rotateImage(bitmap, 90);
////            List<Integer> lstCameraSize = Commons.getCropSize((Activity)view, type, bitmap);
////            if(lstCameraSize != null) {
////                final Bitmap bmpCrop = Bitmap.createBitmap(bitmap, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));
//
//            ImageProfileRequest imageProfileRequest = new ImageProfileRequest(mDataStore.getUser(), Commons.convertBitmapToBase64(bmp), "");
//            mDataStore.uploadImage(new OnResponse<String, ImageProfileResponse>() {
//                @Override
//                public void onResponseSuccess(String tag, String rs, ImageProfileResponse extraData) {
//                    if (extraData != null && extraData.getStatuscode() == 200) {
//                        mDataStore.saveImageToLocal(mDataStore.getUser() + imageType + ".jpg", bmp);
//                        mDataStore.saveImageToDB(extraData, mDataStore.getUser() + imageType, mDataStore.getUser(), String.valueOf(imageType));
//                        mInteractorOutput.updateImageOutput(type, position, imageType, bmp);
//                    } else {
//                        mInteractorOutput.updateImageFailed(rs);
//                    }
//                }
//
//                @Override
//                public void onResponseError(String tag, String message) {
//                    mInteractorOutput.updateImageFailed(message);
//                }
//            }, "Bearer " + mDataStore.getToken(), imageProfileRequest);
        } else {
            mInteractorOutput.updateImageFailed("Tải ảnh lỗi!");
        }
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
    }

}

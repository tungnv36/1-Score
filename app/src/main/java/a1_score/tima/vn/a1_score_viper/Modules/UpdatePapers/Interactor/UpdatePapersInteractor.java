package a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Interactor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.DataStore.UpdatePapersDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.PapersEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.PapersResponse;
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
    public void getImageType() {
        final List<PapersEntity> papersEntities = mDataStore.getImageType();
        if(papersEntities != null && papersEntities.size() > 0) {
            mInteractorOutput.getImageTypeSuccess(papersEntities);
        } else {
            mDataStore.getImageType(new OnResponse<String, PapersResponse>() {
                @Override
                public void onResponseSuccess(String tag, String rs, PapersResponse extraData) {
                    if (extraData != null && extraData.getStatuscode() == 200) {
                        mDataStore.deleteAllImageType();
                        List<PapersEntity> papersEntities1 = new ArrayList<>();
                        for(PapersResponse.ImageTypesEntity imageTypesEntity : extraData.getImagetypes()) {
                            mDataStore.updateImageTypeToDB(imageTypesEntity, mDataStore.getUser(), false);
                            PapersEntity papersEntity = new PapersEntity();
                            papersEntity.setUsername(mDataStore.getUser());
                            papersEntity.setTypeid(imageTypesEntity.getTypeid());
                            papersEntity.setTypename(imageTypesEntity.getTypename());
                            papersEntity.setImagesize(imageTypesEntity.getImagesize());
                            papersEntity.setDone(false);
                            papersEntities1.add(papersEntity);
                        }
                        mInteractorOutput.getImageTypeSuccess(papersEntities1);
                    } else {
                        mInteractorOutput.updateImageFailed(extraData.getMessage());
                    }
                }

                @Override
                public void onResponseError(String tag, String message) {
                    mInteractorOutput.updateImageFailed(message);
                }
            }, String.format("Bearer %s", mDataStore.getToken()));
        }
    }

    @Override
    public void getImages() {
        mInteractorOutput.getImagesSeccess(mDataStore.getImages(mDataStore.getUser()));
    }

    @Override
    public void takePhoto(int type) {
        mInteractorOutput.takePhotoOutput(type);
    }

    @Override
    public void updateImage(final int type, final int position, String filePath, final int typeId) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if(position != -1 || bitmap != null) {
            if(type == 1) {//Ảnh chụp thẻ
                Bitmap bmp = Commons.rotateImage(bitmap, 90);//Xoay ảnh sau khi chụp
                List<Integer> lstCameraSize = Commons.getCropSize((Activity)mView, type, bmp);//Lấy toạ độ để crop ảnh
                if(lstCameraSize != null) {
                    final Bitmap bmpCrop = Bitmap.createBitmap(bmp, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));//Crop ảnh theo khung

                    ImageProfileRequest imageProfileRequest = new ImageProfileRequest(
                            mDataStore.getUser(),
                            typeId,
                            Commons.convertBitmapToBase64(bmpCrop),
                            "");
                    mDataStore.uploadImage(new OnResponse<String, ImageProfileResponse>() {
                        @Override
                        public void onResponseSuccess(String tag, String rs, ImageProfileResponse extraData) {
                            if (extraData != null && extraData.getStatuscode() == 200) {
                                long imageName= System.currentTimeMillis();
                                mDataStore.saveImageToLocal(imageName + ".jpg", bmpCrop);//Lưu ảnh vào file manager
                                if(mDataStore.saveImageToDB(extraData, String.valueOf(imageName), mDataStore.getUser(), String.valueOf(typeId)) > 0) {
                                    mDataStore.updateImageTypeState(mDataStore.getUser(), typeId, true);
                                }
                                mInteractorOutput.updateImageOutput(type, position, typeId, bmpCrop);
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
                ImageProfileRequest imageProfileRequest = new ImageProfileRequest(
                        mDataStore.getUser(),
                        typeId,
                        Commons.convertBitmapToBase64(bmp),
                        "");
                mDataStore.uploadImage(new OnResponse<String, ImageProfileResponse>() {
                    @Override
                    public void onResponseSuccess(String tag, String rs, ImageProfileResponse extraData) {
                        if (extraData != null && extraData.getStatuscode() == 200) {
                            long imageName= System.currentTimeMillis();
                            mDataStore.saveImageToLocal(imageName + ".jpg", bmp);//Lưu ảnh vào file manager
                            if(mDataStore.saveImageToDB(extraData, String.valueOf(imageName), mDataStore.getUser(), String.valueOf(typeId)) > 0) {
                                mDataStore.updateImageTypeState(mDataStore.getUser(), typeId, true);
                            }
                            mInteractorOutput.updateImageOutput(type, position, typeId, bmp);
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

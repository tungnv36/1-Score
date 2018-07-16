package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Interactor;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.DataStore.UpdateFamilyDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyMembersRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyMembersResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.RelationshipDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.RelationshipResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Interface.UpdateFamilyInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import a1_score.tima.vn.a1_score_viper.R;

public class UpdateFamilyInteractor implements UpdateFamilyInterface.InteractorInput {

    private UpdateFamilyInterface.InteractorOutput mInteractorOutput;
    private UpdateFamilyInterface.View mView;
    private UpdateFamilyInterface.DataStore mDataStore;

    public UpdateFamilyInteractor(UpdateFamilyInterface.View view, UpdateFamilyInterface.InteractorOutput interactorOutput) {
        this.mInteractorOutput = interactorOutput;
        mView = view;
        mDataStore = UpdateFamilyDataStore.getInstance(view);
    }

    @Override
    public void initImage(int type, String name) {
        try {
            File fImage = new File(
                    Environment.getExternalStorageDirectory()
                            + File.separator + Constant.ROOT_FOLDER + File.separator
                            + Constant.PHOTO_FOLDER + File.separator + mDataStore.getUser() + name + ".jpg");
            if (fImage.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(fImage.getPath());
                if (bitmap != null) {
                    mInteractorOutput.initImageOutput(type, bitmap);
                }
            } else {
                mInteractorOutput.initImageOutput(type, null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void getRelationshipDictionary() {
        mDataStore.getRelationshipDictionary(new OnResponse<String, RelationshipDictionaryResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, RelationshipDictionaryResponse extraData) {
                if(extraData != null && extraData.getStatuscode() == 200) {
                    mInteractorOutput.getRelationshipDictionary(extraData.getRelationshiptype());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {

            }
        }, String.format("Bearer %s", mDataStore.getToken()));
    }

    @Override
    public void initRelationship() {
        List<FamilyMembersResponse.RelationshipEntity> relationshipEntities = mDataStore.getFamilyMember(mDataStore.getUser());
        if(relationshipEntities != null && relationshipEntities.size() > 0) {
            mInteractorOutput.initRelationship(mDataStore.getUser(), relationshipEntities);
        }
    }

    @Override
    public void getFamily() {
        mInteractorOutput.getFamilyOutput(mDataStore.getFamily(mDataStore.getUser()));
    }

    @Override
    public void takePhoto(int type, int imageType) {
        mInteractorOutput.takePhotoOutput(type, imageType);
    }

    @Override
    public void updateImage(final int type, final int imageType, String filePath, final String fileName) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if(bitmap != null) {
            if(imageType == 3) {
                Bitmap bmp = Commons.rotateImage(bitmap, 90);//Xoay ảnh sau khi chụp
                List<Integer> lstCameraSize = Commons.getCropSize((Activity)mView, type, bmp);//Lấy toạ độ để crop ảnh
                if(lstCameraSize != null) {
                    final Bitmap bmpCrop = Bitmap.createBitmap(bmp, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));//Crop ảnh theo khung

                    ImageProfileRequest imageProfileRequest = new ImageProfileRequest(
                            mDataStore.getUser(),
                            0,
                            Commons.convertBitmapToBase64(bmpCrop),
                            "");
                    mDataStore.uploadImage(new OnResponse<String, ImageProfileResponse>() {
                        @Override
                        public void onResponseSuccess(String tag, String rs, ImageProfileResponse extraData) {
                            if (extraData != null && extraData.getStatuscode() == 200) {
                                mDataStore.saveImageToLocal(mDataStore.getUser() + fileName + ".jpg", bmpCrop);//Lưu ảnh vào file manager
                                mDataStore.saveImageToDB(extraData, fileName, mDataStore.getUser(), getType(imageType, fileName));//Lưu thông tin ảnh vào db local
                                mInteractorOutput.updateImageOutput(type, imageType, bmpCrop);
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
            } else {
                final Bitmap bmp = Commons.rotateImage(bitmap, 90);
                ImageProfileRequest imageProfileRequest = new ImageProfileRequest(
                        mDataStore.getUser(),
                        0,
                        Commons.convertBitmapToBase64(bmp),
                        "");
                mDataStore.uploadImage(new OnResponse<String, ImageProfileResponse>() {
                    @Override
                    public void onResponseSuccess(String tag, String rs, ImageProfileResponse extraData) {
                        if (extraData != null && extraData.getStatuscode() == 200) {
                            mDataStore.saveImageToLocal(mDataStore.getUser() + fileName + ".jpg", bmp);
                            mDataStore.saveImageToDB(extraData, fileName, mDataStore.getUser(), getType(imageType, fileName));
                            mInteractorOutput.updateImageOutput(type, imageType, bmp);
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
        } else {
            mInteractorOutput.updateImageFailed("Lỗi tải ảnh");
        }
    }

    @Override
    public void updateFamilyMembers(final Dialog dialog, int relationshipTypeId, String name, String phone, String sbcName, String scName) {
        final String username = mDataStore.getUser();
        final FamilyMembersRequest familyMembersRequest = new FamilyMembersRequest();
        familyMembersRequest.setUsername(username);
        familyMembersRequest.setRelationshipTypeId(relationshipTypeId);
        familyMembersRequest.setRelationshipName(name);
        familyMembersRequest.setRelationshipPhone(phone);
        familyMembersRequest.setBirthCertificateId(mDataStore.getImageID(username, getType(2, sbcName)));
        familyMembersRequest.setStudentCardId(mDataStore.getImageID(username, getType(3, scName)));

        mDataStore.updateFamilyMembers(new OnResponse<String, FamilyMembersResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, FamilyMembersResponse extraData) {
                if(extraData != null && extraData.getStatuscode() == 200) {
                    mDataStore.updateFamilyMembersToDB(username, extraData.getRelationship());
                    mInteractorOutput.updateFamilyMembersSuccess(dialog, extraData.getMessage(), familyMembersRequest);
                } else {
                    mInteractorOutput.updateFamilyMembersFailed(dialog, extraData.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mInteractorOutput.updateFamilyMembersFailed(dialog, message);
            }
        }, String.format("Bearer %s", mDataStore.getToken()), familyMembersRequest);
    }

    @Override
    public void updateFamily(boolean isFamily, String nameVC, String phoneVC, int numberOfSon) {
        final String username = mDataStore.getUser();
        FamilyRequest familyRequest = new FamilyRequest();
        familyRequest.setUsername(username);
        familyRequest.setMarriageStatus(isFamily?1:-1);
        familyRequest.setFamilyName(nameVC);
        familyRequest.setFamilyPhone(phoneVC);
        familyRequest.setChildrenNumber(numberOfSon);
        familyRequest.setMarriageRegistrationId(mDataStore.getImageID(username, getType(1, "")));

        mDataStore.updateFamily(new OnResponse<String, FamilyResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, FamilyResponse extraData) {
                if(extraData != null && extraData.getStatuscode() == 200) {
                    mDataStore.updateFamilyToDB(username, extraData.getFamily());
                    mInteractorOutput.updateFamilySuccess(extraData.getMessage());
                } else {
                    mInteractorOutput.updateFamilyFailed(extraData.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mInteractorOutput.updateFamilyFailed(message);
            }
        }, String.format("Bearer %s", mDataStore.getToken()), familyRequest);
    }

    private String getType(int type, String fileName) {
        switch (type) {
            case 1:
                return "MR";//Đăng ký kết hôn
            case 2:
                return "SBC" + fileName.substring(fileName.length() - 1);//Giấy khai sinh
            case 3:
                return "SC" + fileName.substring(fileName.length() - 1);//Thẻ học sinh
            default:
                return "";
        }
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
        mView = null;
        mDataStore = null;
    }

}

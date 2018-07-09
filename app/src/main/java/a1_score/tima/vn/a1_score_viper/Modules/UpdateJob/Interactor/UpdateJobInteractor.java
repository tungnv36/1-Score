package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interactor;

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
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interface.UpdateJobInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import a1_score.tima.vn.a1_score_viper.R;

public class UpdateJobInteractor implements UpdateJobInterface.InteractorInput {

    private UpdateJobInterface.InteractorOutput mInteractorOutput;
    private UpdateJobInterface.View mView;
    private UpdateJobInterface.DataStore mDataStore;

    public UpdateJobInteractor(UpdateJobInterface.View view, UpdateJobInterface.InteractorOutput mInteractorOutput) {
        this.mInteractorOutput = mInteractorOutput;
        mView = view;
        mDataStore = UpdateJobDataStore.getInstance(view);
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
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void getJobDictionary() {
        if(mDataStore.checkJobsDicExist()) {
            mInteractorOutput.getJobsOutput(mDataStore.getJobsDic());
            mInteractorOutput.getPositionsOutput(mDataStore.getPositionsDic());
            mInteractorOutput.getSalaryLevelOutput(mDataStore.getSalariesDic());
        } else {
            mDataStore.getJobDictionary(new OnResponse<String, JobDictionaryResponse>() {
                @Override
                public void onResponseSuccess(String tag, String rs, JobDictionaryResponse extraData) {
                    if(extraData != null && extraData.getStatuscode() == 200) {
                        if(mDataStore.updateJobDicToDB(extraData) > 0) {
                            mInteractorOutput.getJobsOutput(mDataStore.getJobsDic());
                            mInteractorOutput.getPositionsOutput(mDataStore.getPositionsDic());
                            mInteractorOutput.getSalaryLevelOutput(mDataStore.getSalariesDic());
                        }
                    }
                }

                @Override
                public void onResponseError(String tag, String message) {

                }
            }, String.format("Bearer %s", mDataStore.getToken()));
        }
    }

    @Override
    public void getJob() {
        JobResponse.JobEntity jobEntity = mDataStore.getJob(mDataStore.getUser());
        if(jobEntity != null) {
            mInteractorOutput.initJobOutput(jobEntity);
        }
    }

    @Override
    public void getColleague() {
        List<ColleagueResponse.ColleagueEntity> colleagueEntities = mDataStore.getColleague(mDataStore.getUser());
        if(colleagueEntities != null) {
            mInteractorOutput.initColleagueOutput(colleagueEntities);
        }
    }

    @Override
    public void takePhoto(int type, int imageType) {
        mInteractorOutput.takePhotoOutput(type, imageType);
    }

    @Override
    public void updateImage(final int type, final int imageType, String filePath, final String fileName) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if(bitmap != null) {
            final Bitmap bmp = Commons.rotateImage(bitmap, 90);
//            List<Integer> lstCameraSize = Commons.getCropSize((Activity)view, type, bitmap);
//            if(lstCameraSize != null) {
//                final Bitmap bmpCrop = Bitmap.createBitmap(bitmap, lstCameraSize.get(0), lstCameraSize.get(1), lstCameraSize.get(2), lstCameraSize.get(3));

                ImageProfileRequest imageProfileRequest = new ImageProfileRequest(mDataStore.getUser(), Commons.convertBitmapToBase64(bmp), "");
                mDataStore.uploadImage(new OnResponse<String, ImageProfileResponse>() {
                    @Override
                    public void onResponseSuccess(String tag, String rs, ImageProfileResponse extraData) {
                        if (extraData != null && extraData.getStatuscode() == 200) {
                            mDataStore.saveImageToLocal(mDataStore.getUser() + fileName + ".jpg", bmp);
                            mDataStore.saveImageToDB(extraData, fileName, mDataStore.getUser(), getType(imageType));
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
//            } else {
//                mInteractorOutput.updateImageFailed("Lỗi xử lý ảnh");
//            }
        } else {
            mInteractorOutput.updateImageFailed("Lỗi tải ảnh");
        }
    }

    @Override
    public void updateJob(int jobID, String companyName, String companyAddress, int positionID, int salaryID, List<ColleagueRequest.ColleagueEntity> colleagueEntities) {
        if(companyName.isEmpty()) {
            mInteractorOutput.updateJobFailed(((Context)mView).getString(R.string.err_company_name_empty));
            return;
        }
        if(companyAddress.isEmpty()) {
            mInteractorOutput.updateJobFailed(((Context)mView).getString(R.string.err_company_address_empty));
            return;
        }
        if(colleagueEntities.size() == 0) {
            mInteractorOutput.updateJobFailed(((Context)mView).getString(R.string.err_company_colleague_empty));
            return;
        }

        String username = mDataStore.getUser();

        final ColleagueRequest colleagueRequest = new ColleagueRequest();
        colleagueRequest.setUsername(mDataStore.getUser());
        colleagueRequest.setColleagues(colleagueEntities);

        JobRequest jobRequest = new JobRequest();
        jobRequest.setJobId(jobID);
        jobRequest.setCompanyName(companyName);
        jobRequest.setCompanyAddress(companyAddress);
        jobRequest.setPositionId(positionID);
        jobRequest.setSalaryId(salaryID);
        jobRequest.setUsername(username);
        jobRequest.setCvId(mDataStore.getImageID(username, getType(1)));
        jobRequest.setContractId(mDataStore.getImageID(username, getType(2)));
        jobRequest.setSalaryBoardId(mDataStore.getImageID(username, getType(3)));

        mDataStore.updateJob(new OnResponse<String, JobResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, JobResponse extraData) {
                if(extraData != null && extraData.getStatuscode() == 200) {
                    mDataStore.addJob(extraData.getJob());
                    mDataStore.updateColleague(new OnResponse<String, ColleagueResponse>() {
                        @Override
                        public void onResponseSuccess(String tag, String rs, ColleagueResponse extraData) {
                            if(extraData != null && extraData.getStatuscode() == 200) {
                                for (ColleagueResponse.ColleagueEntity colleagueEntity : extraData.getColleagues()) {
                                    mDataStore.addColleague(mDataStore.getUser(), colleagueEntity);
                                }
                                mInteractorOutput.updateJobSuccess(extraData.getMessage());
                            } else {
                                mInteractorOutput.updateJobFailed(extraData.getMessage());
                            }
                        }

                        @Override
                        public void onResponseError(String tag, String message) {
                            mInteractorOutput.updateJobFailed(message);
                        }
                    }, String.format("Bearer %s", mDataStore.getToken()), colleagueRequest);
                } else {
                    mInteractorOutput.updateJobFailed(extraData.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mInteractorOutput.updateJobFailed(message);
            }
        }, String.format("Bearer %s", mDataStore.getToken()), jobRequest);
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
        mInteractorOutput = null;
        mView = null;
        mDataStore = null;
    }
}

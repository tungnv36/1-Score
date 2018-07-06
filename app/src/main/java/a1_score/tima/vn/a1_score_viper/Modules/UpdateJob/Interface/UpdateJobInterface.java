package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interface;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.UpdateColleagueEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.UpdateColleagueResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobDictionaryResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.UpdateJobEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.UpdateJobResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;

public interface UpdateJobInterface {
    //View
    interface View {
        void initImage(int type, Bitmap bitmap);//type = 1: cv, type = 2: hop dong, type = 3: bang luong

        void initJobs(List<JobDictionaryResultEntity.JobsEntity> jobsEntities);
        void initPosition(List<JobDictionaryResultEntity.PositionsEntity> positionsEntities);
        void iniSalaryLevel(List<JobDictionaryResultEntity.SalaryLevelsEntity> salaryLevelsEntities);
        void updateImage(int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateJobSuccess(String msg);
        void updateJobFailed(String err);
    }
    //Presenter
    interface Presenter {
        void initImage(int type, String name);

        void getJobDictionary();
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath, String fileName);
        void updateJob(int jobID, String companyName, String companyAddress, int positionID, int salaryID, List<UpdateColleagueEntity.ColleagueEntity> colleagueEntities);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void initImage(int type, String name);

        void getJobDictionary();
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath, String fileName);
        void updateJob(int jobID, String companyName, String companyAddress, int positionID, int salaryID, List<UpdateColleagueEntity.ColleagueEntity> colleagueEntities);

        void unRegister();
    }

    interface InteractorOutput {
        void initImageOutput(int type, Bitmap bitmap);

        void getJobsOutput(List<JobDictionaryResultEntity.JobsEntity> jobsEntities);
        void getPositionsOutput(List<JobDictionaryResultEntity.PositionsEntity> positionsEntities);
        void getSalaryLevelOutput(List<JobDictionaryResultEntity.SalaryLevelsEntity> salaryLevelsEntities);
        void takePhotoOutput(int type, int imageType);
        void updateImageOutput(int type, int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateJobSuccess(String msg);
        void updateJobFailed(String err);
    }
    //Wireframe
    interface Wireframe {
        void gotoCamera(Activity activity, int type, int imageType);
    }
    //DataStore
    interface DataStore {
        String getUser();
        String getFullName();
        String getToken();
        int getImageID(String phone, String type);
        boolean checkJobsDicExist();
        List<JobDictionaryResultEntity.JobsEntity> getJobsDic();
        List<JobDictionaryResultEntity.PositionsEntity> getPositionsDic();
        List<JobDictionaryResultEntity.SalaryLevelsEntity> getSalariesDic();

        void updateFullName(String fullname);
        void saveImageToLocal(String fineName, Bitmap bmp);
        void saveImageToDB(UploadImageResultEntity uploadImageResultEntity, String imageName, String username, String type);
        void uploadImage(final OnResponse<String, UploadImageResultEntity> m_Response, String token, UploadImageEntity uploadImageEntity);
        void updateJob(final OnResponse<String, UpdateJobResultEntity> m_Response, String token, UpdateJobEntity updateJobEntity);
        void updateColleague(final OnResponse<String, UpdateColleagueResultEntity> m_Response, String token, UpdateColleagueEntity colleagueEntity);
        long updateJobDicToDB(JobDictionaryResultEntity jobDictionaryResultEntity);
        void getJobDictionary(final OnResponse<String, JobDictionaryResultEntity> m_Response, String token);
        long addJob(UpdateJobResultEntity.JobEntity jobEntity);
        long addColleague(String username, UpdateColleagueResultEntity.ColleagueEntity colleagueEntity);
    }
}

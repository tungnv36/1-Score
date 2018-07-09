package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interface;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;

public interface UpdateJobInterface {
    //View
    interface View {
        void initImage(int type, Bitmap bitmap);//type = 1: cv, type = 2: hop dong, type = 3: bang luong

        void initJobs(List<JobDictionaryResponse.JobsEntity> jobsEntities);
        void initPosition(List<JobDictionaryResponse.PositionsEntity> positionsEntities);
        void iniSalaryLevel(List<JobDictionaryResponse.SalaryLevelsEntity> salaryLevelsEntities);
        void initJob(JobResponse.JobEntity jobEntity);
        void initColleague(List<ColleagueResponse.ColleagueEntity> colleagueEntities);

        void updateImage(int imageType, Bitmap img);
        void updateImageFailed(String err);
        void updateJobSuccess(String msg);
        void updateJobFailed(String err);
    }
    //Presenter
    interface Presenter {
        void initImage(int type, String name);

        void getJobDictionary();
        void getJob();
        void getColleague();

        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath, String fileName);
        void updateJob(int jobID, String companyName, String companyAddress, int positionID, int salaryID, List<ColleagueRequest.ColleagueEntity> colleagueEntities);
        void onDestroy();
    }
    //Interactor
    interface InteractorInput {
        void initImage(int type, String name);

        void getJobDictionary();
        void getJob();
        void getColleague();
        void takePhoto(int type, int imageType);
        void updateImage(int type, int imageType, String filePath, String fileName);
        void updateJob(int jobID, String companyName, String companyAddress, int positionID, int salaryID, List<ColleagueRequest.ColleagueEntity> colleagueEntities);

        void unRegister();
    }

    interface InteractorOutput {
        void initImageOutput(int type, Bitmap bitmap);

        void getJobsOutput(List<JobDictionaryResponse.JobsEntity> jobsEntities);
        void getPositionsOutput(List<JobDictionaryResponse.PositionsEntity> positionsEntities);
        void getSalaryLevelOutput(List<JobDictionaryResponse.SalaryLevelsEntity> salaryLevelsEntities);
        void initJobOutput(JobResponse.JobEntity jobEntity);
        void initColleagueOutput(List<ColleagueResponse.ColleagueEntity> colleagueEntities);

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
        List<JobDictionaryResponse.JobsEntity> getJobsDic();
        List<JobDictionaryResponse.PositionsEntity> getPositionsDic();
        List<JobDictionaryResponse.SalaryLevelsEntity> getSalariesDic();
        JobResponse.JobEntity getJob(String username);
        List<ColleagueResponse.ColleagueEntity> getColleague(String username);

        void updateFullName(String fullname);
        void saveImageToLocal(String fineName, Bitmap bmp);
        void saveImageToDB(ImageProfileResponse imageProfileResponse, String imageName, String username, String type);
        void uploadImage(final OnResponse<String, ImageProfileResponse> m_Response, String token, ImageProfileRequest imageProfileRequest);
        void updateJob(final OnResponse<String, JobResponse> m_Response, String token, JobRequest jobRequest);
        void updateColleague(final OnResponse<String, ColleagueResponse> m_Response, String token, ColleagueRequest colleagueEntity);
        long updateJobDicToDB(JobDictionaryResponse jobDictionaryResponse);
        void getJobDictionary(final OnResponse<String, JobDictionaryResponse> m_Response, String token);
        long addJob(JobResponse.JobEntity jobEntity);
        long addColleague(String username, ColleagueResponse.ColleagueEntity colleagueEntity);
    }
}

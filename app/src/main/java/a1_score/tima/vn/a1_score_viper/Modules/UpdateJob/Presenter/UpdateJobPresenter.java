package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Presenter;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interactor.UpdateJobInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interface.UpdateJobInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Wireframe.UpdateJobWireframe;

public class UpdateJobPresenter implements UpdateJobInterface.Presenter, UpdateJobInterface.InteractorOutput {

    private UpdateJobInterface.View mView;
    private UpdateJobInterface.InteractorInput mInteractorInput;
    private UpdateJobInterface.Wireframe mWireframe;

    public UpdateJobPresenter(UpdateJobInterface.View view) {
        mView = view;
        mInteractorInput = new UpdateJobInteractor(view, this);
        mWireframe = new UpdateJobWireframe();
    }

    @Override
    public void initImage(int type, String name) {
        mInteractorInput.initImage(type, name);
    }

    @Override
    public void getJobDictionary() {
        mInteractorInput.getJobDictionary();
    }

    @Override
    public void getJob() {
        mInteractorInput.getJob();
    }

    @Override
    public void getColleague() {
        mInteractorInput.getColleague();
    }

    @Override
    public void takePhoto(int type, int imageType) {
        mInteractorInput.takePhoto(type, imageType);
    }

    @Override
    public void updateImage(int type, int imageType, String filePath, String fileName) {
        mInteractorInput.updateImage(type, imageType, filePath, fileName);
    }

    @Override
    public void updateJob(int jobID, String companyName, String companyAddress, int positionID, int salaryID, List<ColleagueRequest.ColleagueEntity> colleagueEntities) {
        mInteractorInput.updateJob(jobID, companyName, companyAddress, positionID, salaryID, colleagueEntities);
    }

    @Override
    public void onDestroy() {
        mInteractorInput.unRegister();
        mInteractorInput = null;
        mView = null;
    }

    @Override
    public void initImageOutput(int type, Bitmap bitmap) {
        mView.initImage(type, bitmap);
    }

    @Override
    public void getJobsOutput(List<JobDictionaryResponse.JobsEntity> jobsEntities) {
        mView.initJobs(jobsEntities);
    }

    @Override
    public void getPositionsOutput(List<JobDictionaryResponse.PositionsEntity> positionsEntities) {
        mView.initPosition(positionsEntities);
    }

    @Override
    public void getSalaryLevelOutput(List<JobDictionaryResponse.SalaryLevelsEntity> salaryLevelsEntities) {
        mView.iniSalaryLevel(salaryLevelsEntities);
    }

    @Override
    public void initJobOutput(JobResponse.JobEntity jobEntity) {
        mView.initJob(jobEntity);
    }

    @Override
    public void initColleagueOutput(List<ColleagueResponse.ColleagueEntity> colleagueEntities) {
        mView.initColleague(colleagueEntities);
    }

    @Override
    public void takePhotoOutput(int type, int imageType) {
        mWireframe.gotoCamera((Activity)mView, type, imageType);
    }

    @Override
    public void updateImageOutput(int type, int imageType, Bitmap img) {
        mView.updateImage(imageType, img);
    }

    @Override
    public void updateImageFailed(String err) {
        mView.updateImageFailed(err);
    }

    @Override
    public void updateJobSuccess(String msg) {
        mView.updateJobSuccess(msg);
    }

    @Override
    public void updateJobFailed(String err) {
        mView.updateImageFailed(err);
    }
}

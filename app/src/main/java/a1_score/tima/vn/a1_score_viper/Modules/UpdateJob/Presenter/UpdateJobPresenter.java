package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Presenter;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.UpdateColleagueEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobDictionaryResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interactor.UpdateJobInteractor;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interface.UpdateJobInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Wireframe.UpdateJobWireframe;

public class UpdateJobPresenter implements UpdateJobInterface.Presenter, UpdateJobInterface.InteractorOutput {

    private UpdateJobInterface.View view;
    private UpdateJobInterface.InteractorInput interactorInput;
    private UpdateJobInterface.Wireframe wireframe;

    public UpdateJobPresenter(UpdateJobInterface.View view) {
        this.view = view;
        interactorInput = new UpdateJobInteractor(view, this);
        wireframe = new UpdateJobWireframe();
    }

    @Override
    public void initImage(int type, String name) {
        interactorInput.initImage(type, name);
    }

    @Override
    public void getJobDictionary() {
        interactorInput.getJobDictionary();
    }

    @Override
    public void takePhoto(int type, int imageType) {
        interactorInput.takePhoto(type, imageType);
    }

    @Override
    public void updateImage(int type, int imageType, String filePath, String fileName) {
        interactorInput.updateImage(type, imageType, filePath, fileName);
    }

    @Override
    public void updateJob(int jobID, String companyName, String companyAddress, int positionID, int salaryID, List<UpdateColleagueEntity.ColleagueEntity> colleagueEntities) {
        interactorInput.updateJob(jobID, companyName, companyAddress, positionID, salaryID, colleagueEntities);
    }

    @Override
    public void onDestroy() {
        interactorInput.unRegister();
        interactorInput = null;
        view = null;
    }

    @Override
    public void initImageOutput(int type, Bitmap bitmap) {
        view.initImage(type, bitmap);
    }

    @Override
    public void getJobsOutput(List<JobDictionaryResultEntity.JobsEntity> jobsEntities) {
        view.initJobs(jobsEntities);
    }

    @Override
    public void getPositionsOutput(List<JobDictionaryResultEntity.PositionsEntity> positionsEntities) {
        view.initPosition(positionsEntities);
    }

    @Override
    public void getSalaryLevelOutput(List<JobDictionaryResultEntity.SalaryLevelsEntity> salaryLevelsEntities) {
        view.iniSalaryLevel(salaryLevelsEntities);
    }

    @Override
    public void takePhotoOutput(int type, int imageType) {
        wireframe.gotoCamera((Activity)view, type, imageType);
    }

    @Override
    public void updateImageOutput(int type, int imageType, Bitmap img) {
        view.updateImage(imageType, img);
    }

    @Override
    public void updateImageFailed(String err) {
        view.updateImageFailed(err);
    }

    @Override
    public void updateJobSuccess(String msg) {
        view.updateJobSuccess(msg);
    }

    @Override
    public void updateJobFailed(String err) {
        view.updateImageFailed(err);
    }
}

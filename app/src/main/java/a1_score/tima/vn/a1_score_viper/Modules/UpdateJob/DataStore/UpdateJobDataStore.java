package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.DataStore;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Common.DB.SQliteDatabase;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.UpdateColleagueEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.UpdateColleagueResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobDictionaryResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.UpdateJobEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.UpdateJobResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interface.UpdateJobInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateJobDataStore extends ApiRequest implements UpdateJobInterface.DataStore {

    private UpdateJobInterface.View view;

    public static UpdateJobDataStore mInstance;
    private static SQliteDatabase sQliteDatabase;

    public static UpdateJobDataStore getInstance(UpdateJobInterface.View view) {
        if (mInstance == null) {
            initApi();
            sQliteDatabase = SQliteDatabase.getInstance((Context)view);
            mInstance = new UpdateJobDataStore(view);
        }
        return mInstance;
    }

    private UpdateJobDataStore(UpdateJobInterface.View view) {
        this.view = view;
    }

    @Override
    public String getUser() {
        SharedPreferences pref = ((Context)view).getSharedPreferences(Constant.PREFS_NAME, ((Context)view).MODE_PRIVATE);
        return pref.getString("username", "");
    }

    @Override
    public String getFullName() {
        SharedPreferences pref = ((Context)view).getSharedPreferences(Constant.PREFS_NAME, ((Context)view).MODE_PRIVATE);
        return pref.getString("fullname", "");
    }

    @Override
    public String getToken() {
        SharedPreferences pref = ((Context)view).getSharedPreferences(Constant.PREFS_NAME, ((Context)view).MODE_PRIVATE);
        return pref.getString("token", "");
    }

    @Override
    public void updateFullName(String fullname) {
        SharedPreferences.Editor editor = ((Context)view).getSharedPreferences(Constant.PREFS_NAME, ((Context)view).MODE_PRIVATE).edit();
        editor.putString("fullname", fullname);
        editor.apply();
        sQliteDatabase.updateFullName(getUser(), fullname);
    }

    @Override
    public int getImageID(String phone, String type) {
        return sQliteDatabase.getImageID(phone, type);
    }

    @Override
    public boolean checkJobsDicExist() {
        if(sQliteDatabase.checkJobsDicExist() > 0 && sQliteDatabase.checkSalariesDicExist() > 0 && sQliteDatabase.checkPositionsDicExist() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<JobDictionaryResultEntity.JobsEntity> getJobsDic() {
        return sQliteDatabase.getJobsDic();
    }

    @Override
    public List<JobDictionaryResultEntity.PositionsEntity> getPositionsDic() {
        return sQliteDatabase.getPositionsDic();
    }

    @Override
    public List<JobDictionaryResultEntity.SalaryLevelsEntity> getSalariesDic() {
        return sQliteDatabase.getSalariesDic();
    }

    @Override
    public void saveImageToLocal(String fineName, Bitmap bmp) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(Environment.getExternalStorageDirectory()
                    + File.separator + Constant.ROOT_FOLDER + File.separator
                    + Constant.PHOTO_FOLDER + File.separator + fineName);
            bmp.compress(Bitmap.CompressFormat.JPEG, 80, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveImageToDB(UploadImageResultEntity uploadImageResultEntity, String imageName, String username, String type) {
        sQliteDatabase.addImage(uploadImageResultEntity, imageName, username, type);
    }

    @Override
    public void uploadImage(final OnResponse<String, UploadImageResultEntity> m_Response, String token, UploadImageEntity uploadImageEntity) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.uploadImage(token, uploadImageEntity);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            UploadImageResultEntity uploadImageResultEntity = gson.fromJson(jsonObject.toString(), UploadImageResultEntity.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), uploadImageResultEntity);
                        } catch (Exception e) {
                            e.printStackTrace();
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), null);
                        }
                    } else {
                        m_Response.onResponseError(TAG, String.valueOf(response.message()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                m_Response.onFinish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                m_Response.onResponseError(TAG, t.getMessage());
                m_Response.onFinish();
            }
        });
    }

    @Override
    public void updateJob(OnResponse<String, UpdateJobResultEntity> m_Response, String token, UpdateJobEntity updateJobEntity) {

    }

    @Override
    public void updateColleague(OnResponse<String, UpdateColleagueResultEntity> m_Response, String token, UpdateColleagueEntity colleagueEntity) {

    }

    @Override
    public long updateJobDicToDB(JobDictionaryResultEntity jobDictionaryResultEntity) {
        long result = 0;
        List<JobDictionaryResultEntity.JobsEntity> jobsEntities = jobDictionaryResultEntity.getJobs();
        List<JobDictionaryResultEntity.PositionsEntity> positionsEntities = jobDictionaryResultEntity.getPositions();
        List<JobDictionaryResultEntity.SalaryLevelsEntity> salaryLevelsEntities = jobDictionaryResultEntity.getSalarylevels();

        for (JobDictionaryResultEntity.JobsEntity jobsEntity : jobsEntities) {
            result += sQliteDatabase.addJobDic(jobsEntity);
        }
        for (JobDictionaryResultEntity.PositionsEntity positionsEntity : positionsEntities) {
            result += sQliteDatabase.addPositionDic(positionsEntity);
        }
        for (JobDictionaryResultEntity.SalaryLevelsEntity salaryLevelsEntity : salaryLevelsEntities) {
            result += sQliteDatabase.addSalaryDic(salaryLevelsEntity);
        }

        return result;
    }

    @Override
    public void getJobDictionary(final OnResponse<String, JobDictionaryResultEntity> m_Response, String token) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.getJobDictionary(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            JobDictionaryResultEntity jobDictionaryResultEntity = gson.fromJson(jsonObject.toString(), JobDictionaryResultEntity.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), jobDictionaryResultEntity);
                        } catch (Exception e) {
                            e.printStackTrace();
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), null);
                        }
                    } else {
                        m_Response.onResponseError(TAG, String.valueOf(response.message()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                m_Response.onFinish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                m_Response.onResponseError(TAG, t.getMessage());
                m_Response.onFinish();
            }
        });
    }

    @Override
    public long addJob(UpdateJobResultEntity.JobEntity jobEntity) {
        return sQliteDatabase.addJob(jobEntity);
    }

    @Override
    public long addColleague(String username, UpdateColleagueResultEntity.ColleagueEntity colleagueEntity) {
        return sQliteDatabase.addColleague(username, colleagueEntity);
    }

}

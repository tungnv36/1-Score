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
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interface.UpdateJobInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateJobDataStore extends ApiRequest implements UpdateJobInterface.DataStore {

    private UpdateJobInterface.View mView;

    public static UpdateJobDataStore sInstance;
    private static SQliteDatabase sQliteDatabase;

    public static UpdateJobDataStore getInstance(UpdateJobInterface.View view) {
        if (sInstance == null) {
            initApi();
            sQliteDatabase = SQliteDatabase.getInstance((Context)view);
            sInstance = new UpdateJobDataStore(view);
        }
        return sInstance;
    }

    private UpdateJobDataStore(UpdateJobInterface.View view) {
        mView = view;
    }

    @Override
    public String getUser() {
        SharedPreferences pref = ((Context)mView).getSharedPreferences(Constant.PREFS_NAME, ((Context)mView).MODE_PRIVATE);
        return pref.getString("username", "");
    }

    @Override
    public String getFullName() {
        SharedPreferences pref = ((Context)mView).getSharedPreferences(Constant.PREFS_NAME, ((Context)mView).MODE_PRIVATE);
        return pref.getString("fullname", "");
    }

    @Override
    public String getToken() {
        SharedPreferences pref = ((Context)mView).getSharedPreferences(Constant.PREFS_NAME, ((Context)mView).MODE_PRIVATE);
        return pref.getString("token", "");
    }

    @Override
    public void updateFullName(String fullname) {
        SharedPreferences.Editor editor = ((Context)mView).getSharedPreferences(Constant.PREFS_NAME, ((Context)mView).MODE_PRIVATE).edit();
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
    public List<JobDictionaryResponse.JobsEntity> getJobsDic() {
        return sQliteDatabase.getJobsDic();
    }

    @Override
    public List<JobDictionaryResponse.PositionsEntity> getPositionsDic() {
        return sQliteDatabase.getPositionsDic();
    }

    @Override
    public List<JobDictionaryResponse.SalaryLevelsEntity> getSalariesDic() {
        return sQliteDatabase.getSalariesDic();
    }

    @Override
    public JobResponse.JobEntity getJob(String username) {
        return sQliteDatabase.getJob(username);
    }

    @Override
    public List<ColleagueResponse.ColleagueEntity> getColleague(String username) {
        return sQliteDatabase.getColleague(username);
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
    public void saveImageToDB(ImageProfileResponse imageProfileResponse, String imageName, String username, String type) {
        sQliteDatabase.addImage(imageProfileResponse, imageName, username, type);
    }

    @Override
    public void uploadImage(final OnResponse<String, ImageProfileResponse> m_Response, String token, ImageProfileRequest imageProfileRequest) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.uploadImage(token, imageProfileRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            ImageProfileResponse imageProfileResponse = gson.fromJson(jsonObject.toString(), ImageProfileResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), imageProfileResponse);
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
    public void updateJob(final OnResponse<String, JobResponse> m_Response, String token, JobRequest jobRequest) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.updateJob(token, jobRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            JobResponse jobResponse = gson.fromJson(jsonObject.toString(), JobResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), jobResponse);
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
    public void updateColleague(final OnResponse<String, ColleagueResponse> m_Response, String token, ColleagueRequest colleagueEntity) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.updateColleague(token, colleagueEntity);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            ColleagueResponse colleagueResponse = gson.fromJson(jsonObject.toString(), ColleagueResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), colleagueResponse);
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
    public long updateJobDicToDB(JobDictionaryResponse jobDictionaryResponse) {
        long result = 0;
        List<JobDictionaryResponse.JobsEntity> jobsEntities = jobDictionaryResponse.getJobs();
        List<JobDictionaryResponse.PositionsEntity> positionsEntities = jobDictionaryResponse.getPositions();
        List<JobDictionaryResponse.SalaryLevelsEntity> salaryLevelsEntities = jobDictionaryResponse.getSalarylevels();

        for (JobDictionaryResponse.JobsEntity jobsEntity : jobsEntities) {
            result += sQliteDatabase.addJobDic(jobsEntity);
        }
        for (JobDictionaryResponse.PositionsEntity positionsEntity : positionsEntities) {
            result += sQliteDatabase.addPositionDic(positionsEntity);
        }
        for (JobDictionaryResponse.SalaryLevelsEntity salaryLevelsEntity : salaryLevelsEntities) {
            result += sQliteDatabase.addSalaryDic(salaryLevelsEntity);
        }

        return result;
    }

    @Override
    public void getJobDictionary(final OnResponse<String, JobDictionaryResponse> m_Response, String token) {
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
                            JobDictionaryResponse jobDictionaryResponse = gson.fromJson(jsonObject.toString(), JobDictionaryResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), jobDictionaryResponse);
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
    public long addJob(JobResponse.JobEntity jobEntity) {
        sQliteDatabase.deleteJobByUsername(jobEntity.getUsername());
        return sQliteDatabase.addJob(jobEntity);
    }

    @Override
    public long addColleague(String username, ColleagueResponse.ColleagueEntity colleagueEntity) {
        sQliteDatabase.deleteColleagueByUsername(username, colleagueEntity.getColleaguephone());
        return sQliteDatabase.addColleague(username, colleagueEntity);
    }

}

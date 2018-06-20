package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.DataStore;

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

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Common.DB.SQliteDatabase;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UpdateProfileEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UpdateProfileResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interface.UpdateProfileInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileDataStore extends ApiRequest implements UpdateProfileInterface.DataStore {

    private UpdateProfileInterface.View view;

    public static UpdateProfileDataStore mInstance;
    private static SQliteDatabase sQliteDatabase;

    public static UpdateProfileDataStore getInstance(UpdateProfileInterface.View view) {
        if (mInstance == null) {
            initApi();
            sQliteDatabase = SQliteDatabase.getInstance((Context)view);
            mInstance = new UpdateProfileDataStore(view);
        }
        return mInstance;
    }

    private UpdateProfileDataStore(UpdateProfileInterface.View view) {
        this.view = view;
    }

    @Override
    public String getUser() {
        SharedPreferences pref = ((Context)view).getSharedPreferences(Constant.PREFS_NAME, ((Context)view).MODE_PRIVATE);
        return pref.getString("username", "");
    }

    @Override
    public String getToken() {
        SharedPreferences pref = ((Context)view).getSharedPreferences(Constant.PREFS_NAME, ((Context)view).MODE_PRIVATE);
        return pref.getString("token", "");
    }

    @Override
    public int getImageID(String phone, String type) {
        sQliteDatabase.getImageByPhone(phone, type);
        return 0;
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
    public void saveProfileToDB(UpdateProfileEntity updateProfileEntity) {
        sQliteDatabase.addProfile(updateProfileEntity);
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
                        m_Response.onResponseError(TAG, String.valueOf(response.code()));
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
    public void updateProfile(final OnResponse<String, UpdateProfileResultEntity> m_Response, String token, UpdateProfileEntity updateProfileEntity) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.updateProfile(token, updateProfileEntity);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            UpdateProfileResultEntity updateProfileResultEntity = gson.fromJson(jsonObject.toString(), UpdateProfileResultEntity.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), updateProfileResultEntity);
                        } catch (Exception e) {
                            e.printStackTrace();
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), null);
                        }
                    } else {
                        m_Response.onResponseError(TAG, String.valueOf(response.code()));
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

}

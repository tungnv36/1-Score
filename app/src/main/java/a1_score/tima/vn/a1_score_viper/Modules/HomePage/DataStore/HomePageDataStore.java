package a1_score.tima.vn.a1_score_viper.Modules.HomePage.DataStore;

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
import a1_score.tima.vn.a1_score_viper.Modules.HomePage.Interface.HomePageInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageDataStore extends ApiRequest implements HomePageInterface.DataStore {

    private HomePageInterface.View view;

    public static HomePageDataStore mInstance;
    private static SQliteDatabase sQliteDatabase;

    public static HomePageDataStore getInstance(HomePageInterface.View view) {
        if (mInstance == null) {
            initApi();
            sQliteDatabase = SQliteDatabase.getInstance((Context)view);
            mInstance = new HomePageDataStore(view);
        }
        return mInstance;
    }

    private HomePageDataStore(HomePageInterface.View view) {
        this.view = view;
    }

    @Override
    public LoginResultEntity.UserEntity getUser() {
        return sQliteDatabase.getUser();
    }

    @Override
    public String getUserName() {
        SharedPreferences pref = ((Context)view).getSharedPreferences(Constant.PREFS_NAME, ((Context)view).MODE_PRIVATE);
        return pref.getString("username", "");
    }

    @Override
    public String getToken() {
        SharedPreferences pref = ((Context)view).getSharedPreferences(Constant.PREFS_NAME, ((Context)view).MODE_PRIVATE);
        return pref.getString("token", "");
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
        sQliteDatabase.deleteImageBy(username, type);
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

}

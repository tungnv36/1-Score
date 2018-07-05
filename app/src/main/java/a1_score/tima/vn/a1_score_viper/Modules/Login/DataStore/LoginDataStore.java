package a1_score.tima.vn.a1_score_viper.Modules.Login.DataStore;

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
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Common.DB.SQliteDatabase;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginDataStore extends ApiRequest implements LoginInterface.DataStore {

    private LoginInterface.View view;

    public static LoginDataStore mInstance;
    private static SQliteDatabase sQliteDatabase;

    public static LoginDataStore getInstance(LoginInterface.View view) {
        if (mInstance == null) {
            initApi();
            sQliteDatabase = SQliteDatabase.getInstance((Context)view);
            mInstance = new LoginDataStore(view);
        }
        return mInstance;
    }

    private LoginDataStore(LoginInterface.View view) {
        this.view = view;
    }

    @Override
    public void callLogin(final OnResponse<String, LoginResultEntity> m_Response, LoginEntity loginEntity) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.callLogin(loginEntity);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            LoginResultEntity loginResultEntity = gson.fromJson(jsonObject.toString(), LoginResultEntity.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), loginResultEntity);
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
    public void setUser(Context context, LoginResultEntity user) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.PREFS_NAME, context.MODE_PRIVATE).edit();
        editor.putString("token", user.getToken());
        editor.putString("username", Commons.changePhone0(user.getUser().getUsername()));
        editor.putString("fullname", user.getUser().getFullname());
        editor.apply();
    }

    @Override
    public void saveUser(LoginResultEntity user) {
        sQliteDatabase.deleteUser();
        sQliteDatabase.addUser(user);
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
    public int getImageID(String username, String type) {
        return sQliteDatabase.getImageID(username, type);
    }

    @Override
    public void createFolder() {
        File rootFolder = new File(Environment.getExternalStorageDirectory() + File.separator + Constant.ROOT_FOLDER);
        if(!rootFolder.exists()) {
            rootFolder.mkdir();
        }
        File photoFolder = new File(Environment.getExternalStorageDirectory() + File.separator + Constant.ROOT_FOLDER + File.separator + Constant.PHOTO_FOLDER);
        if(!photoFolder.exists()) {
            photoFolder.mkdir();
        }
    }
}

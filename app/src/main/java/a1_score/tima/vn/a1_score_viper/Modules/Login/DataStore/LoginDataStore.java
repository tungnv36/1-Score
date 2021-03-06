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
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginRequest;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginDataStore extends ApiRequest implements LoginInterface.DataStore {

    private LoginInterface.View mView;

    public static LoginDataStore sInstance;
    private static SQliteDatabase sQliteDatabase;

    public static LoginDataStore getInstance(LoginInterface.View view) {
        if (sInstance == null) {
            initApi();
            sQliteDatabase = SQliteDatabase.getInstance((Context)view);
            sInstance = new LoginDataStore(view);
        }
        return sInstance;
    }

    private LoginDataStore(LoginInterface.View view) {
        mView = view;
    }

    @Override
    public void callLogin(final OnResponse<String, LoginResponse> m_Response, LoginRequest loginRequest) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.callLogin(loginRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            LoginResponse loginResponse = gson.fromJson(jsonObject.toString(), LoginResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), loginResponse);
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
    public void setUser(Context context, LoginResponse user) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.PREFS_NAME, context.MODE_PRIVATE).edit();
        editor.putString("token", user.getToken());
        editor.putString("username", Commons.changePhone0(user.getUser().getUsername()));
        editor.putString("fullname", user.getUser().getFullname());
        editor.putLong("score", user.getUser().getScored());
        editor.putInt("level", user.getUser().getLevel());
        editor.apply();
    }

    @Override
    public void saveUser(LoginResponse user) {
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
    public void saveImageToDB(ImageProfileResponse imageProfileResponse, String imageName, String username, String type) {
        sQliteDatabase.deleteImageBy(username, type);
        sQliteDatabase.addImage(imageProfileResponse, imageName, username, type);
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

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
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileDictionatyResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interface.UpdateProfileInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileDataStore extends ApiRequest implements UpdateProfileInterface.DataStore {

    private UpdateProfileInterface.View mView;

    public static UpdateProfileDataStore sInstance;
    private static SQliteDatabase sQliteDatabase;

    public static UpdateProfileDataStore getInstance(UpdateProfileInterface.View view) {
        if (sInstance == null) {
            initApi();
            sQliteDatabase = SQliteDatabase.getInstance((Context)view);
            sInstance = new UpdateProfileDataStore(view);
        }
        return sInstance;
    }

    private UpdateProfileDataStore(UpdateProfileInterface.View view) {
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
    public ProfileRequest getData(String userName) {
        return sQliteDatabase.getProfileByPhone(userName);
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
    public void saveProfileToDB(ProfileRequest profileRequest) {
        sQliteDatabase.addProfile(profileRequest);
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
    public void updateProfile(final OnResponse<String, ProfileResponse> m_Response, String token, ProfileRequest profileRequest) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.updateProfile(token, profileRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            ProfileResponse profileResponse = gson.fromJson(jsonObject.toString(), ProfileResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), profileResponse);
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
    public void getDictionary(final OnResponse<String, ProfileDictionatyResponse> m_Response, String token) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.getProfileDictionary(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            ProfileDictionatyResponse profileDictionatyResponse = gson.fromJson(jsonObject.toString(), ProfileDictionatyResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), profileDictionatyResponse);
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

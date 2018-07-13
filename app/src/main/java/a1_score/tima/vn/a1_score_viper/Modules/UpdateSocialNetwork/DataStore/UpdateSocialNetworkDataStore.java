package a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.DataStore;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Common.DB.SQliteDatabase;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.DataStore.UpdateProfileDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interface.UpdateProfileInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity.FacebookRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity.FacebookResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Interface.UpdateSocialNetworkInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateSocialNetworkDataStore extends ApiRequest implements UpdateSocialNetworkInterface.DataStore {

    private UpdateSocialNetworkInterface.View mView;

    public static UpdateSocialNetworkDataStore sInstance;
    private static SQliteDatabase sQliteDatabase;

    public static UpdateSocialNetworkDataStore getInstance(UpdateSocialNetworkInterface.View view) {
        if (sInstance == null) {
            initApi();
            sQliteDatabase = SQliteDatabase.getInstance((Context)view);
            sInstance = new UpdateSocialNetworkDataStore(view);
        }
        return sInstance;
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

    private UpdateSocialNetworkDataStore(UpdateSocialNetworkInterface.View view) {
        mView = view;
    }

    @Override
    public void updateFacebookInfoToDB(String username, FacebookResponse facebookResponse) {
        sQliteDatabase.deleteFacebookByUsername(username);
        sQliteDatabase.addFacebook(username, facebookResponse.getFacebookprofile());
    }

    @Override
    public void allowFacebook(final OnResponse<String, FacebookResponse> m_Response, String token, FacebookRequest facebookRequest) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.updateUserFacebook(token, facebookRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            FacebookResponse facebookResponse = gson.fromJson(jsonObject.toString(), FacebookResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), facebookResponse);
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

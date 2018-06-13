package a1_score.tima.vn.a1_score_viper.Modules.Login.DataStore;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginDataStore extends ApiRequest implements LoginInterface.DataStore {

    private LoginInterface.View view;

    public static LoginDataStore mInstance;

    public static LoginDataStore getInstance(LoginInterface.View view) {
        if (mInstance == null) {
            initApi();
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
        editor.apply();
    }
}

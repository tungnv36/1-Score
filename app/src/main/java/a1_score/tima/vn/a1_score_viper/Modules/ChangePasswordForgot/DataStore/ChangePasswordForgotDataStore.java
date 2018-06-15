package a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.DataStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Entity.ChangePasswordForgotEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Entity.ChangePasswordForgotResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Interface.ChangePasswordForgotInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordForgotDataStore extends ApiRequest implements ChangePasswordForgotInterface.DataStore {

    private ChangePasswordForgotInterface.View view;

    public static ChangePasswordForgotDataStore mInstance;

    public static ChangePasswordForgotDataStore getInstance(ChangePasswordForgotInterface.View view) {
        if (mInstance == null) {
            initApi();
            mInstance = new ChangePasswordForgotDataStore(view);
        }
        return mInstance;
    }

    private ChangePasswordForgotDataStore(ChangePasswordForgotInterface.View view) {
        this.view = view;
    }

    @Override
    public void callChangePass(final OnResponse<String, ChangePasswordForgotResultEntity> m_Response, String Token, ChangePasswordForgotEntity changePasswordForgotEntity) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.changePassword(Token, changePasswordForgotEntity);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            ChangePasswordForgotResultEntity changePasswordForgotResultEntity = gson.fromJson(jsonObject.toString(), ChangePasswordForgotResultEntity.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), changePasswordForgotResultEntity);
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

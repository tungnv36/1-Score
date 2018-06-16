package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.DataStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.ChangePhoneEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.ChangePhoneResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Interface.ChangePhoneInterface;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity.ForgotPasswordResultEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePhoneDataStore extends ApiRequest implements ChangePhoneInterface.DataStore {

    private ChangePhoneInterface.View view;

    public static ChangePhoneDataStore mInstance;

    public static ChangePhoneDataStore getInstance(ChangePhoneInterface.View view) {
        if (mInstance == null) {
            initApi();
            mInstance = new ChangePhoneDataStore(view);
        }
        return mInstance;
    }

    private ChangePhoneDataStore(ChangePhoneInterface.View view) {
        this.view = view;
    }
    @Override
    public void changePhone(final OnResponse<String, ChangePhoneResultEntity> m_Response, String token, ChangePhoneEntity changePhoneEntity) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.changePhone(token, changePhoneEntity);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            ChangePhoneResultEntity changePhoneResultEntity = gson.fromJson(jsonObject.toString(), ChangePhoneResultEntity.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), changePhoneResultEntity);
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

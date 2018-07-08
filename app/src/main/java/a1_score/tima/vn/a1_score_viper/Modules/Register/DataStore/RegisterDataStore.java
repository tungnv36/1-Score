package a1_score.tima.vn.a1_score_viper.Modules.Register.DataStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterRequest;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Interface.RegisterInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDataStore extends ApiRequest implements RegisterInterface.DataStore {

    private RegisterInterface.View mView;

    public static RegisterDataStore sInstance;

    public static RegisterDataStore getInstance(RegisterInterface.View view) {
        if (sInstance == null) {
            initApi();
            sInstance = new RegisterDataStore(view);
        }
        return sInstance;
    }

    private RegisterDataStore(RegisterInterface.View view) {
        mView = view;
    }

    @Override
    public void callRegister(final OnResponse<String, RegisterResponse> m_Response, RegisterRequest registerRequest) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.callRegister(registerRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            RegisterResponse registerResponse = gson.fromJson(jsonObject.toString(), RegisterResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), registerResponse);
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

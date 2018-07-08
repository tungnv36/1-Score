package a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.DataStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Entity.ForgotPasswordRequest;
import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Entity.ForgotPasswordResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Interface.ResetPasswordInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordDataStore extends ApiRequest implements ResetPasswordInterface.DataStore {

    private ResetPasswordInterface.View mView;

    public static ResetPasswordDataStore mInstance;

    public static ResetPasswordDataStore getInstance(ResetPasswordInterface.View view) {
        if (mInstance == null) {
            initApi();
            mInstance = new ResetPasswordDataStore(view);
        }
        return mInstance;
    }

    private ResetPasswordDataStore(ResetPasswordInterface.View view) {
        mView = view;
    }

    @Override
    public void callChangePass(final OnResponse<String, ForgotPasswordResponse> m_Response, String Token, ForgotPasswordRequest forgotPasswordRequest) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.forgotPassword(Token, forgotPasswordRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            ForgotPasswordResponse forgotPasswordResponse = gson.fromJson(jsonObject.toString(), ForgotPasswordResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), forgotPasswordResponse);
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

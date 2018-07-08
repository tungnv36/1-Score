package a1_score.tima.vn.a1_score_viper.Modules.Otp.DataStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity.OtpRequest;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity.OtpResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Interface.OtpInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpDataStore extends ApiRequest implements OtpInterface.DataStore {

    private OtpInterface.View mView;

    private static OtpDataStore sInstance;

    public static OtpDataStore getInstance(OtpInterface.View view) {
        if (sInstance == null) {
            initApi();
            sInstance = new OtpDataStore(view);
        }
        return sInstance;
    }

    private OtpDataStore(OtpInterface.View view) {
        mView = view;
    }

    @Override
    public void compareOtp(final OnResponse<String, OtpResultEntity> m_Response, OtpRequest otpRequest) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.callCompareOtp(otpRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            OtpResultEntity otpResultEntity = gson.fromJson(jsonObject.toString(), OtpResultEntity.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), otpResultEntity);
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

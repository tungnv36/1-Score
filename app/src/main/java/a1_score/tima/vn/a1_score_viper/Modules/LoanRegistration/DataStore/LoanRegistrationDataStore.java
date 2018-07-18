package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.DataStore;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Common.DB.SQliteDatabase;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Interface.LoanRegistrationInterface;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.DataStore.LoanRequestDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interface.LoanRequestInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobDictionaryResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanRegistrationDataStore extends ApiRequest implements LoanRegistrationInterface.DataStore {

    private LoanRegistrationInterface.View mView;

    public static LoanRegistrationDataStore sInstance;
    private static SQliteDatabase sQliteDatabase;

    public static LoanRegistrationDataStore getInstance(LoanRegistrationInterface.View view) {
        if (sInstance == null) {
            initApi();
            sQliteDatabase = SQliteDatabase.getInstance((Context)view);
            sInstance = new LoanRegistrationDataStore(view);
        }
        return sInstance;
    }

    private LoanRegistrationDataStore(LoanRegistrationInterface.View view) {
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
    public boolean checkLoanDicExist() {
        if(sQliteDatabase.checkPurposeDicExist() > 0 && sQliteDatabase.checkPaymentMethodDicExist() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<LoanDictionaryResponse.PurposeEntity> getPurposeDic() {
        return sQliteDatabase.getPurposeDic();
    }

    @Override
    public List<LoanDictionaryResponse.PaymentMethodEntity> getPaymentMethodDic() {
        return sQliteDatabase.getPaymentMethodDic();
    }

    @Override
    public long updateLoanDicToDB(LoanDictionaryResponse loanDictionaryResponse) {
        long result = 0;
        List<LoanDictionaryResponse.PurposeEntity> purposeEntities = loanDictionaryResponse.getPurpose();
        List<LoanDictionaryResponse.PaymentMethodEntity> paymentMethodEntities = loanDictionaryResponse.getPaymentmethod();

        for (LoanDictionaryResponse.PurposeEntity purposeEntity : purposeEntities) {
            result += sQliteDatabase.addPurposeDic(purposeEntity);
        }
        for (LoanDictionaryResponse.PaymentMethodEntity paymentMethodEntity : paymentMethodEntities) {
            result += sQliteDatabase.addPaymentMethodDic(paymentMethodEntity);
        }

        return result;
    }

    @Override
    public void getLoanDictionary(final OnResponse<String, LoanDictionaryResponse> m_Response, String token) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.getLoanDictionary(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            LoanDictionaryResponse loanDictionaryResponse = gson.fromJson(jsonObject.toString(), LoanDictionaryResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), loanDictionaryResponse);
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

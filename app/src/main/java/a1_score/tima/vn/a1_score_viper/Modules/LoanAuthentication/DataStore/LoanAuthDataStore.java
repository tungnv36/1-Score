package a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.DataStore;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.File;

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Common.DB.SQliteDatabase;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Entity.LoanAuthRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Entity.LoanAuthResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Interface.LoanAuthInterface;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanAuthDataStore extends ApiRequest implements LoanAuthInterface.DataStore {

    private LoanAuthInterface.View mView;

    public static LoanAuthDataStore sInstance;
    private static SQliteDatabase sQliteDatabase;

    public static LoanAuthDataStore getInstance(LoanAuthInterface.View view) {
        if (sInstance == null) {
            initApi();
            sQliteDatabase = SQliteDatabase.getInstance((Context)view);
            sInstance = new LoanAuthDataStore(view);
        }
        return sInstance;
    }

    private LoanAuthDataStore(LoanAuthInterface.View view) {
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
    public void uploadVideo(final OnResponse<String, LoanAuthResponse> m_Response, String token, LoanAuthRequest loanAuthRequest) {
        m_Response.onStart();

        File originalFile = new File(Environment.getExternalStorageDirectory() + File.separator + Constant.VIDEO_AUTH_FILE_NAME);
        if(originalFile.exists()) {
            RequestBody filePart = RequestBody.create(
                    MediaType.parse("video/mp4"),
                    originalFile
            );
            MultipartBody.Part file = MultipartBody.Part.createFormData("file", originalFile.getName(), filePart);

            RequestBody usernamePart = RequestBody.create(MultipartBody.FORM, loanAuthRequest.getUsername());
            RequestBody desPart = RequestBody.create(MultipartBody.FORM, loanAuthRequest.getDescription());

            Call<ResponseBody> call = m_Service.uploadVideo(token, usernamePart, desPart, file);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        if (response.code() == 200) {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            try {
                                final GsonBuilder gsonBuilder = new GsonBuilder();
                                final Gson gson = gsonBuilder.create();
                                LoanAuthResponse loanAuthResponse = gson.fromJson(jsonObject.toString(), LoanAuthResponse.class);
                                m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), loanAuthResponse);
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
        } else {

        }
    }

    @Override
    public void registerLoanCredit(final OnResponse<String, LoanResponse> m_Response, String token, LoanRequest loanRequest) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.registerLoanCredit(token, loanRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            LoanResponse loanResponse = gson.fromJson(jsonObject.toString(), LoanResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), loanResponse);
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
    public void updateRegisterLoanCreditToDB(LoanResponse.LoancreditEntity loancreditEntity, String username) {
        sQliteDatabase.addLoanRegistration(loancreditEntity, username);
    }

}

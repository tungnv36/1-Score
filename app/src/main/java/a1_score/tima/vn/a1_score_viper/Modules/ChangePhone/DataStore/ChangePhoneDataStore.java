package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.DataStore;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Common.DB.SQliteDatabase;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.UserPhone;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.UserPhoneResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Interface.ChangePhoneInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePhoneDataStore extends ApiRequest implements ChangePhoneInterface.DataStore {

    private ChangePhoneInterface.View mView;

    public static ChangePhoneDataStore sInstance;
    public static SQliteDatabase sqLiteDatabase;

    public static ChangePhoneDataStore getInstance(ChangePhoneInterface.View view) {
        if (sInstance == null) {
            initApi();
            sInstance = new ChangePhoneDataStore(view);
            sqLiteDatabase = SQliteDatabase.getInstance((Context)view);
        }
        return sInstance;
    }

    @Override
    public String getUser() {
        SharedPreferences pref = ((Context)mView).getSharedPreferences(Constant.PREFS_NAME, ((Context)mView).MODE_PRIVATE);
        return pref.getString("username", "");
    }

    @Override
    public void updateUser(Context context, String oldPhone, String userName) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.PREFS_NAME, context.MODE_PRIVATE).edit();
        editor.putString("username", userName);
        editor.apply();
        sqLiteDatabase.updateUser(oldPhone, userName);
    }

    private ChangePhoneDataStore(ChangePhoneInterface.View view) {
        this.mView = view;
    }
    @Override
    public void changePhone(final OnResponse<String, UserPhoneResponse> m_Response, String token, UserPhone userPhone) {
        m_Response.onStart();
        Call<ResponseBody> call = m_Service.changePhone(token, userPhone);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            final Gson gson = gsonBuilder.create();
                            UserPhoneResponse userPhoneResponse = gson.fromJson(jsonObject.toString(), UserPhoneResponse.class);
                            m_Response.onResponseSuccess(TAG, jsonObject.get(Constant.TAG_MESSAGE).toString(), userPhoneResponse);
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

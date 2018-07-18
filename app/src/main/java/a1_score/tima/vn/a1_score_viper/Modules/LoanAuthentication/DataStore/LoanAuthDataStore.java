package a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.DataStore;

import android.content.Context;
import android.content.SharedPreferences;

import a1_score.tima.vn.a1_score_viper.Common.API.ApiRequest;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Common.DB.SQliteDatabase;
import a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Interface.LoanAuthInterface;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.DataStore.LoanRegistrationDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Interface.LoanRegistrationInterface;

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

}

package a1_score.tima.vn.a1_score_viper.Modules.Introduction.DataStore;

import android.content.Context;
import android.content.SharedPreferences;

import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Entity.IntroductionEntity;

public class IntroductionDataStore {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Context mContext;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "intro_slider-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public IntroductionDataStore(Context context) {
        this.mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mSharedPreferences.edit();
    }

    public void setFirstTimeLaunch(IntroductionEntity introductionEntity) {
        mEditor.putBoolean(IS_FIRST_TIME_LAUNCH, introductionEntity.isFirstTime());
        mEditor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return mSharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}

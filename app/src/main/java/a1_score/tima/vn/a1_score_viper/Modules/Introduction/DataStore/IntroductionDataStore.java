package a1_score.tima.vn.a1_score_viper.Modules.Introduction.DataStore;

import android.content.Context;
import android.content.SharedPreferences;

import a1_score.tima.vn.a1_score_viper.Modules.Introduction.Entity.IntroductionEntity;

public class IntroductionDataStore {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "intro_slider-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public IntroductionDataStore(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(IntroductionEntity introductionEntity) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, introductionEntity.isFirstTime());
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}

package a1_score.tima.vn.a1_score_viper.Modules.Setting.Interface;

import android.app.Activity;

public interface SettingInterface {
    interface View {

    }

    interface Presenter {
        void onDestroy();
        void goToChangePhone();
    }

    interface InteractorInput {
        void unRegister();
        void goToChangePhone();
    }

    interface InteractorOutput {
        void goToChangePhoneOutput();
    }

    interface Wireframe {
        void goToChangePhone(Activity activity);
    }

    interface DataStore {

    }

}

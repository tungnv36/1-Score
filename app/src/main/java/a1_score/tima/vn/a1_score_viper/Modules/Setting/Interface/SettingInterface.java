package a1_score.tima.vn.a1_score_viper.Modules.Setting.Interface;

import android.app.Activity;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Entity.LogoutResponseEntity;

public interface SettingInterface {
    interface View {
        void logout();
        void logoutFailed(String msg);
    }

    interface Presenter {
        void onDestroy();
        void goToChangePhone();
        void logout();
    }

    interface InteractorInput {
        void unRegister();
        void goToChangePhone();
        void logout();
    }

    interface InteractorOutput {
        void goToChangePhoneOutput();
        void logoutOutput();
        void logoutFailed(String msg);
    }

    interface Wireframe {
        void goToChangePhone(Activity activity);
    }

    interface DataStore {
        String getToken();
        void logout(final OnResponse<String, LogoutResponseEntity> m_Response, String token);
    }

}

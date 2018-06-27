package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Interface;

import android.content.Context;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.ChangePhoneEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.ChangePhoneResultEntity;

public interface ChangePhoneInterface {

    interface View {
        void initPhone(String phone);
        void changePhoneSuccess(String msg);
        void changePhoneFailed(String err);
    }

    interface Presenter {
        void initPhone();
        void changePhone(String oldPhone, String newPhone, String password);
        void onDestroy();
    }

    interface InteractorInput {
        void initPhone();
        void changePhone(String oldPhone, String newPhone, String password);
        void unRegister();
    }

    interface InteractorOutput {
        void initPhoneOutput(String phone);
        void changePhoneSuccess(String msg);
        void changePhoneFailed(String err);
    }

    interface Wireframe {

    }

    interface DataStore {
        String getUser();
        void updateUser(Context context, String oldPhone, String userName);
        void changePhone(final OnResponse<String, ChangePhoneResultEntity> m_Response, String token, ChangePhoneEntity changePhoneEntity);
    }
}

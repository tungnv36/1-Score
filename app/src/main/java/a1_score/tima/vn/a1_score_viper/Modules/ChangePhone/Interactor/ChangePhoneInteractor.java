package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Interactor;

import android.content.Context;
import android.content.SharedPreferences;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.DataStore.ChangePhoneDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.ChangePhoneEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.ChangePhoneResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Interface.ChangePhoneInterface;

public class ChangePhoneInteractor implements ChangePhoneInterface.InteractorInput {

    private ChangePhoneInterface.InteractorOutput interactorOutput;
    private ChangePhoneInterface.DataStore dataStore;
    private ChangePhoneInterface.View view;

    public ChangePhoneInteractor(ChangePhoneInterface.View view, ChangePhoneInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        dataStore = ChangePhoneDataStore.getInstance(view);
        this.view = view;
    }

    @Override
    public void initPhone() {
        interactorOutput.initPhoneOutput(dataStore.getUser());
    }

    @Override
    public void changePhone(final String oldPhone, final String newPhone, String password) {
        if(oldPhone.isEmpty()) {
            interactorOutput.changePhoneFailed("Bạn chưa nhập số điện thoại cũ");
            return;
        }
        if(newPhone.isEmpty()) {
            interactorOutput.changePhoneFailed("Bạn chưa nhập số điện thoại mới");
            return;
        }
        if(password.isEmpty()) {
            interactorOutput.changePhoneFailed("Bạn chưa nhập mật khẩu");
            return;
        }
        SharedPreferences pref = ((Context)view).getSharedPreferences(Constant.PREFS_NAME, ((Context)view).MODE_PRIVATE);
        String token = "Bearer " + pref.getString("token", "");
        ChangePhoneEntity changePhoneEntity = new ChangePhoneEntity(oldPhone, newPhone, password);
        dataStore.changePhone(new OnResponse<String, ChangePhoneResultEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, ChangePhoneResultEntity extraData) {
                if(extraData == null || extraData.getStatusCode() != 200) {
                    interactorOutput.changePhoneFailed(rs);
                } else {
                    dataStore.updateUser((Context)view, oldPhone, newPhone);
                    interactorOutput.changePhoneSuccess(extraData.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                interactorOutput.changePhoneFailed(message);
            }
        }, token, changePhoneEntity);
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
        dataStore = null;
    }
}

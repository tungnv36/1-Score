package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Interactor;

import android.content.Context;
import android.content.SharedPreferences;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.DataStore.ChangePhoneDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.UserPhone;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.UserPhoneResponse;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Interface.ChangePhoneInterface;
import a1_score.tima.vn.a1_score_viper.R;

public class ChangePhoneInteractor implements ChangePhoneInterface.InteractorInput {

    private ChangePhoneInterface.InteractorOutput mInteractorOutput;
    private ChangePhoneInterface.DataStore mDataStore;
    private ChangePhoneInterface.View mView;

    public ChangePhoneInteractor(ChangePhoneInterface.View view, ChangePhoneInterface.InteractorOutput interactorOutput) {
        this.mInteractorOutput = interactorOutput;
        mDataStore = ChangePhoneDataStore.getInstance(view);
        mView = view;
    }

    @Override
    public void initPhone() {
        mInteractorOutput.initPhoneOutput(mDataStore.getUser());
    }

    @Override
    public void changePhone(final String oldPhone, final String newPhone, String password) {
        if(oldPhone.isEmpty()) {
            mInteractorOutput.changePhoneFailed(((Context)mView).getString(R.string.err_old_phone_empty));
            return;
        }
        if(newPhone.isEmpty()) {
            mInteractorOutput.changePhoneFailed(((Context)mView).getString(R.string.err_new_phone_empty));
            return;
        }
        if(password.isEmpty()) {
            mInteractorOutput.changePhoneFailed(((Context)mView).getString(R.string.err_pass_empty));
            return;
        }
        SharedPreferences pref = ((Context)mView).getSharedPreferences(Constant.PREFS_NAME, ((Context)mView).MODE_PRIVATE);
        String token = String.format("Bearer %s", pref.getString("token", ""));
        UserPhone userPhone = new UserPhone(oldPhone, newPhone, password);
        mDataStore.changePhone(new OnResponse<String, UserPhoneResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, UserPhoneResponse extraData) {
                if(extraData == null || extraData.getStatusCode() != 200) {
                    mInteractorOutput.changePhoneFailed(rs);
                } else {
                    mDataStore.updateUser((Context)mView, oldPhone, newPhone);
                    mInteractorOutput.changePhoneSuccess(extraData.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mInteractorOutput.changePhoneFailed(message);
            }
        }, token, userPhone);
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
        mDataStore = null;
    }
}

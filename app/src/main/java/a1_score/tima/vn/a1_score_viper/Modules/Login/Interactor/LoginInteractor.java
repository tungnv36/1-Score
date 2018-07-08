package a1_score.tima.vn.a1_score_viper.Modules.Login.Interactor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;

import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Login.DataStore.LoginDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginRequest;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import a1_score.tima.vn.a1_score_viper.R;

public class LoginInteractor implements LoginInterface.InteractorInput {

    private LoginInterface.InteractorOutput mInteractorOutput;
    private LoginInterface.DataStore mDataStore;
    private LoginInterface.View mView;

    public LoginInteractor(LoginInterface.View view, LoginInterface.InteractorOutput interactorOutput) {
        this.mInteractorOutput = interactorOutput;
        mDataStore = LoginDataStore.getInstance(view);
        mView = view;
    }

    @Override
    public void createFolder() {
        mDataStore.createFolder();
    }

    @Override
    public void changeHeightBanner(int height, int margin) {
        mInteractorOutput.changeHeightBannerOutput(height, margin);
    }

    @Override
    public void login(final ProgressDialog mProgress, final String username, String password) {
        if(!Commons.isNetworkConnected((Context)mView)) {
            mInteractorOutput.loginFailed(((Activity)mView).getString(R.string.err_internet_connection));
            return;
        }
        if(username.isEmpty()) {
            mInteractorOutput.usernameEmpty(((Activity)mView).getString(R.string.err_user_empty));
            return;
        }
        if(password.isEmpty()) {
            mInteractorOutput.passwordEmpty(((Activity)mView).getString(R.string.err_pass_empty));
            return;
        }
        LoginRequest loginRequest = new LoginRequest(username, password);
        mDataStore.callLogin(new OnResponse<String, LoginResponse>() {
            @Override
            public void onResponseSuccess(String tag, String rs, final LoginResponse extraData) {
                try {
                    if (extraData == null || extraData.getStatuscode() != 200) {
                        if (extraData.getStatuscode() == 621) {//User chưa active
                            mInteractorOutput.loginFailedLostOtp(username, rs);
                        } else {
                            mInteractorOutput.loginFailed(rs);
                        }
                    } else {
                        mDataStore.setUser((Context) mView, extraData);
                        mDataStore.saveUser(extraData);

                        if (extraData.getUser().getUrlavatar() != null && !extraData.getUser().getUrlavatar().isEmpty()) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Bitmap bmp = Commons.getBitmapFromURL(extraData.getUser().getUrlavatar());
                                    mDataStore.saveImageToLocal(String.format("%s_avatar.jpg", username), bmp);
                                    ((Activity) mView).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mInteractorOutput.loginSuccess(mProgress);
                                        }
                                    });
                                }
                            }).start();
                        } else {
                            mInteractorOutput.loginSuccess(mProgress);
                        }
                    }
                } catch (Exception ex) {
                    mInteractorOutput.loginFailed(ex.getMessage());
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                mInteractorOutput.loginFailed(message);
            }
        }, loginRequest);
    }

    @Override
    public void goToForgotPassword() {
        mInteractorOutput.goToForgotPasswordOutput();
    }

    @Override
    public void goToOtp(String phoneNumber, String msg) {
        mInteractorOutput.goToOtpOutput(phoneNumber, msg);
    }

    @Override
    public void unRegister() {
        mInteractorOutput = null;
        mDataStore = null;
    }

}

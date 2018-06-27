package a1_score.tima.vn.a1_score_viper.Modules.Login.Interactor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import a1_score.tima.vn.a1_score_viper.Common.API.Config;
import a1_score.tima.vn.a1_score_viper.Common.API.OnResponse;
import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.Login.DataStore.LoginDataStore;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;
import a1_score.tima.vn.a1_score_viper.R;

public class LoginInteractor implements LoginInterface.InteractorInput {

    private LoginInterface.InteractorOutput interactorOutput;
    private LoginInterface.DataStore dataStore;
    private LoginInterface.View view;

    public LoginInteractor(LoginInterface.View view, LoginInterface.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
        dataStore = LoginDataStore.getInstance(view);
        this.view = view;
    }

    @Override
    public void createFolder() {
        dataStore.createFolder();
    }

    @Override
    public void changeHeightBanner(int height, int margin) {
        interactorOutput.changeHeightBannerOutput(height, margin);
    }

    @Override
    public void login(final ProgressDialog mProgress, final String username, String password) {
        if(username.isEmpty()) {
            interactorOutput.usernameEmpty(((Activity)view).getString(R.string.err_user_empty));
            return;
        }
        if(password.isEmpty()) {
            interactorOutput.passwordEmpty(((Activity)view).getString(R.string.err_pass_empty));
            return;
        }
        LoginEntity loginEntity = new LoginEntity(username, password);
        dataStore.callLogin(new OnResponse<String, LoginResultEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, LoginResultEntity extraData) {
                if(extraData == null || extraData.getStatuscode() != 200) {
                    if(extraData.getStatuscode() == 621) {//User chưa active
                        interactorOutput.loginFailedLostOtp(username, rs);
                    } else {
                        interactorOutput.loginFailed(rs);
                    }
                } else {
                    dataStore.setUser((Context) view, extraData);
                    dataStore.saveUser(extraData);
                    if(extraData.getUser().getIdavatar() != null && !extraData.getUser().getIdavatar().isEmpty()) {
                        final int idAvatar = Integer.parseInt(extraData.getUser().getIdavatar());
                        int idAvatarLocal = dataStore.getImageID(username, "AVATAR");
                        if (idAvatarLocal != idAvatar) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Bitmap bmp = Commons.getBitmapFromURL(String.format("%s%s%s", Config.BASE_SERVER_URL, Config.BASE_IMAGE_URL, String.valueOf(idAvatar)));
                                    dataStore.saveImageToLocal(String.format("%s_avatar.jpg", username), bmp);
                                    UploadImageResultEntity uploadImageResultEntity = new UploadImageResultEntity();
                                    uploadImageResultEntity.setStatuscode(200);
                                    uploadImageResultEntity.setMessage("Successfuly");
                                    UploadImageResultEntity.ImageEntity imageEntity = new UploadImageResultEntity.ImageEntity();
                                    imageEntity.setId(idAvatar);
                                    imageEntity.setUrl("");
                                    imageEntity.setImagetype("AVATAR");
                                    uploadImageResultEntity.setImage(imageEntity);
                                    dataStore.saveImageToDB(uploadImageResultEntity, String.format("%s_avatar", username), username, "AVATAR");
                                    ((Activity)view).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            interactorOutput.loginSuccess(mProgress);
                                        }
                                    });
                                }
                            }).start();
                        } else {
                            interactorOutput.loginSuccess(mProgress);
                        }
                    } else {
                        interactorOutput.loginSuccess(mProgress);
                    }
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                interactorOutput.loginFailed(message);
            }
        }, loginEntity);
    }

    @Override
    public void goToForgotPassword() {
        interactorOutput.goToForgotPasswordOutput();
    }

    @Override
    public void goToOtp(String phoneNumber, String msg) {
        interactorOutput.goToOtpOutput(phoneNumber, msg);
    }

    @Override
    public void unRegister() {
        interactorOutput = null;
        dataStore = null;
    }

}

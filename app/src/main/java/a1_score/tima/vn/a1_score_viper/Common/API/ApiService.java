package a1_score.tima.vn.a1_score_viper.Common.API;

import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Entity.ChangePasswordForgotEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.ChangePhoneEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity.ForgotPasswordEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity.OtpEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.UpdateColleagueEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.UpdateJobEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UpdateProfileEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by hoangngoc on 8/15/16.
 */

public interface ApiService {

    String API_LOGIN = "authorize/login";
    String API_LOGOUT = "authorize/logout";
    String API_REGISTER = "user/register";
    String API_OTP = "otp/confirm";
    String API_SEND_OTP = "otp/send-otp";
    String API_FORGOT_PASS = "user/forgot-password";
    String API_CHANGE_PHONE = "user/change-phone-number";

    String API_UPLOAD_IMAGE = "image/upload";
    String API_UPDATE_PROFILE = "profile";

    String API_GET_JOB_DICTIONARY = "job/dictionary";
    String API_UPDATE_JOB = "job";
    String API_UPDATE_COLLEAGUE = "colleague";

    @POST(API_LOGIN)
    Call<ResponseBody> callLogin(@Body LoginEntity loginEntity);

    @POST(API_REGISTER)
    Call<ResponseBody> callRegister(@Body RegisterEntity registerEntity);

    @POST(API_OTP)
    Call<ResponseBody> callCompareOtp(@Body OtpEntity otpEntity);

    @POST(API_SEND_OTP)
    Call<ResponseBody> sendOtp(@Body ForgotPasswordEntity forgotPasswordEntity);

    @POST(API_FORGOT_PASS)
    Call<ResponseBody> forgotPassword(@Header("Authorization") String token, @Body ChangePasswordForgotEntity changePasswordForgotEntity);

    @POST(API_CHANGE_PHONE)
    Call<ResponseBody> changePhone(@Header("Authorization") String token, @Body ChangePhoneEntity changePhoneEntity);

    @POST(API_UPLOAD_IMAGE)
    Call<ResponseBody> uploadImage(@Header("Authorization") String token, @Body UploadImageEntity uploadImageEntity);

    @POST(API_UPDATE_PROFILE)
    Call<ResponseBody> updateProfile(@Header("Authorization") String token, @Body UpdateProfileEntity updateProfileEntity);

    @POST(API_LOGOUT)
    Call<ResponseBody> callLogout(@Header("Authorization") String token);

    @GET(API_GET_JOB_DICTIONARY)
    Call<ResponseBody> getJobDictionary(@Header("Authorization") String token);

    @POST(API_UPDATE_JOB)
    Call<ResponseBody> updateJob(@Header("Authorization") String token, @Body UpdateJobEntity updateJobEntity);

    @POST(API_UPDATE_COLLEAGUE)
    Call<ResponseBody> updateColleague(@Header("Authorization") String token, @Body UpdateColleagueEntity colleagueEntity);

}

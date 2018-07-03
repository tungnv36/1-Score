package a1_score.tima.vn.a1_score_viper.Common.API;

import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Entity.ChangePasswordForgotEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.ChangePhoneEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity.ForgotPasswordEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity.OtpEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UpdateProfileEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by hoangngoc on 8/15/16.
 */

public interface ApiService {

    String API_LOGIN = "/api/v1.0/authorize/login";
    String API_REGISTER = "/api/v1.0/users/register";
    String API_OTP = "/api/v1.0/otp/compare-otp";
    String API_SEND_OTP = "/api/v1.0/otp/send-otp";
    String API_FORGOT_PASS = "/api/v1.0/users/forgot-password";
    String API_CHANGE_PHONE = "/api/v1.0/users/change-phone-number";
    String API_UPLOAD_IMAGE = "/api/v1.0/images/upload";
    String API_UPDATE_PROFILE = "/api/v1.0/profile";

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

}

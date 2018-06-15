package a1_score.tima.vn.a1_score_viper.Common.API;

import a1_score.tima.vn.a1_score_viper.Modules.ChangePasswordForgot.Entity.ChangePasswordForgotEntity;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity.ForgotPasswordEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity.OtpEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterEntity;
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

    String API_LOGIN = "/api/v1.0/users/token";
    String API_REGISTER = "/api/v1.0/users/register";
    String API_OTP = "/api/v1.0/otp/compare-otp";
    String API_SEND_OTP = "/api/v1.0/otp/send-otp";
    String API_CHANGE_PASS = "/api/v1.0/users/forgot-password";

    @POST(API_LOGIN)
    Call<ResponseBody> callLogin(@Body LoginEntity loginEntity);

    @POST(API_REGISTER)
    Call<ResponseBody> callRegister(@Body RegisterEntity registerEntity);

    @POST(API_OTP)
    Call<ResponseBody> callCompareOtp(@Body OtpEntity otpEntity);

    @POST(API_SEND_OTP)
    Call<ResponseBody> sendOtp(@Body ForgotPasswordEntity forgotPasswordEntity);

    @POST(API_CHANGE_PASS)
    Call<ResponseBody> changePassword(@Header("Authorization") String token, @Body ChangePasswordForgotEntity changePasswordForgotEntity);

}

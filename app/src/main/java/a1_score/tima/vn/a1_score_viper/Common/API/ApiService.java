package a1_score.tima.vn.a1_score_viper.Common.API;

import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity.UserPhone;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Entity.ForgotPasswordRequest;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginRequest;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Entity.OtpRequest;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyMembersRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity.FacebookRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
    String API_UPDATE_FAMILY_MEMBERS = "relationship";
    String API_UPDATE_FAMILY = "family";
    String API_GET_RELATIONSHIP_DICTIONARY = "relationship/dictionary";
    String API_GET_RELATIONSHIP = "relationship/";

    String API_UPDATE_USER_FACEBOOK = "facebook";

    @POST(API_LOGIN)
    Call<ResponseBody> callLogin(@Body LoginRequest loginRequest);

    @POST(API_REGISTER)
    Call<ResponseBody> callRegister(@Body RegisterRequest registerRequest);

    @POST(API_OTP)
    Call<ResponseBody> callCompareOtp(@Body OtpRequest otpRequest);

    @POST(API_SEND_OTP)
    Call<ResponseBody> sendOtp(@Body ForgotPasswordRequest forgotPasswordRequest);

    @POST(API_FORGOT_PASS)
    Call<ResponseBody> forgotPassword(@Header("Authorization") String token, @Body a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Entity.ForgotPasswordRequest forgotPasswordRequest);

    @POST(API_CHANGE_PHONE)
    Call<ResponseBody> changePhone(@Header("Authorization") String token, @Body UserPhone userPhone);

    @POST(API_UPLOAD_IMAGE)
    Call<ResponseBody> uploadImage(@Header("Authorization") String token, @Body ImageProfileRequest imageProfileRequest);

    @POST(API_UPDATE_PROFILE)
    Call<ResponseBody> updateProfile(@Header("Authorization") String token, @Body ProfileRequest profileRequest);

    @POST(API_LOGOUT)
    Call<ResponseBody> callLogout(@Header("Authorization") String token);

    @GET(API_GET_JOB_DICTIONARY)
    Call<ResponseBody> getJobDictionary(@Header("Authorization") String token);

    @POST(API_UPDATE_JOB)
    Call<ResponseBody> updateJob(@Header("Authorization") String token, @Body JobRequest jobRequest);

    @POST(API_UPDATE_COLLEAGUE)
    Call<ResponseBody> updateColleague(@Header("Authorization") String token, @Body ColleagueRequest colleagueEntity);

    @POST(API_UPDATE_FAMILY_MEMBERS)
    Call<ResponseBody> updateFamilyMembers(@Header("Authorization") String token, @Body FamilyMembersRequest familyMembersRequest);

    @POST(API_UPDATE_FAMILY)
    Call<ResponseBody> updateFamily(@Header("Authorization") String token, @Body FamilyRequest familyRequest);

    @GET(API_GET_RELATIONSHIP_DICTIONARY)
    Call<ResponseBody> getRelationshipDictionary(@Header("Authorization") String token);

    @GET(API_GET_RELATIONSHIP)
    Call<ResponseBody> getRelationship(@Header("Authorization") String token, @Query("username") String username);

    @POST(API_UPDATE_USER_FACEBOOK)
    Call<ResponseBody> updateUserFacebook(@Header("Authorization") String token, @Body FacebookRequest facebookRequest);

}

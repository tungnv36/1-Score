package a1_score.tima.vn.a1_score_viper.Common.API;

import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Entity.RegisterEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by hoangngoc on 8/15/16.
 */

public interface ApiService {

    String API_LOGIN = "/api/v1.0/users/token";
    String API_REGISTER = "/api/v1.0/users/register";

    @POST(API_LOGIN)
    Call<ResponseBody> callLogin(@Body LoginEntity loginEntity);

    @POST(API_REGISTER)
    Call<ResponseBody> callRegister(@Body RegisterEntity registerEntity);

}

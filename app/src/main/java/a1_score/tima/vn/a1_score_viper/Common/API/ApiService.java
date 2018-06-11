package a1_score.tima.vn.a1_score_viper.Common.API;

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

    String API_LOGIN = "/api/Counselor/MessageMarkRead";

    @GET(API_LOGIN)
    Call<ResponseBody> getListQuestion(@Query("token") String token,
                                       @Query("loanCreditId") String loanCreditId,
                                       @Query("idType") String idType,
                                       @Query("productId") String productId);

}

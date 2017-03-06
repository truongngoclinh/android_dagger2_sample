package samples.linhtruong.com.dagger2sample.network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import samples.linhtruong.com.base.BaseResponse;
import samples.linhtruong.com.dagger2sample.network.request.LoginRequest;
import samples.linhtruong.com.dagger2sample.network.request.LogoutRequest;
import samples.linhtruong.com.dagger2sample.network.request.UserInfoRequest;
import samples.linhtruong.com.dagger2sample.network.request.UserTransactionListRequest;

/**
 * Simple http url request
 *
 * @author linhtruong
 * @date 3/4/17 - 00:34.
 * @organization VED
 */

public interface APIService {

    @FormUrlEncoded
    @POST("me/login")
    Call<LoginRequest.LoginResponse> login(@FieldMap Map<String, String> data);

    @GET("me/logout")
    Call<BaseResponse> logout(@FieldMap Map<String, String> data);

    @GET("me/info")
    Call<UserInfoRequest.UserInfoResponse> getUserInfo(@FieldMap Map<String, String> data);

    @GET("me/top_ups")
    Call<UserTransactionListRequest.UserTransactionListResponse> getTopUpList(@Field("uid") String uid);
}

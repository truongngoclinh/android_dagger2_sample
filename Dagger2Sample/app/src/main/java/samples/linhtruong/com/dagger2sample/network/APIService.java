package samples.linhtruong.com.dagger2sample.network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import samples.linhtruong.com.dagger2sample.network.request.LoginRequest;

/**
 * Simple http url request
 *
 * @author linhtruong
 * @date 3/4/17 - 00:34.
 * @organization VED
 */

public interface APIService {

    @FormUrlEncoded
    @POST("me/login/")
    Call<LoginRequest.LoginResponse> login(@FieldMap Map<String, String> data);

}

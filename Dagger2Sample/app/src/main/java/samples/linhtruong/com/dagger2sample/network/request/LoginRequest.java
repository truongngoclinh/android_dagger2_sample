package samples.linhtruong.com.dagger2sample.network.request;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import samples.linhtruong.com.dagger2sample.base.BaseHttpRequest;
import samples.linhtruong.com.dagger2sample.base.BaseResponse;
import samples.linhtruong.com.dagger2sample.network.APIConfig;
import samples.linhtruong.com.dagger2sample.network.APIService;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:58.
 * @organization VED
 */

public class LoginRequest extends BaseHttpRequest<LoginRequest.LoginResponse> {

    @Inject
    APIService mService;

    private Map<String, String> mData;

    public LoginRequest() {
    }

    public void initData(String account, String password) {
        mData = new HashMap<>();
        mData.put("account", account);
        mData.put("password", password);
    }

    @Override
    protected Call<LoginRequest.LoginResponse> call() {
        return mService.login(mData);
    }

    @Override
    protected String getURLRequest() {
        return APIConfig.BASE_URL + "me/login";
    }

    public class LoginResponse extends BaseResponse {

        @SerializedName("token")
        public String accessToken;

    }
}

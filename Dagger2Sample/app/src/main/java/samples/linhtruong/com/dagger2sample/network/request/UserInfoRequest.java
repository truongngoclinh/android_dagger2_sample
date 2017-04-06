package samples.linhtruong.com.dagger2sample.network.request;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import samples.linhtruong.com.base.BaseHttpRequest;
import samples.linhtruong.com.base.BaseResponse;
import samples.linhtruong.com.dagger2sample.network.APIConfig;
import samples.linhtruong.com.dagger2sample.network.APIService;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 12:31.
 * @organization VED
 */

public class UserInfoRequest extends BaseHttpRequest<UserInfoRequest.UserInfoResponse> {

    public APIService mService;

    public LoginSession mLoginSession;

    public Map<String, String> mData;

    public UserInfoRequest(APIService service, LoginSession session) {
        mService = service;
        mLoginSession = session;
    }

    public void initData() {
        mData = new HashMap<>();
        mData.put("uid", mLoginSession.getUid());
        mData.put("access_token", mLoginSession.getToken());
    }

    @Deprecated
    @Override
    public UserInfoResponse getMockResponse() {
        UserInfoResponse response = new UserInfoResponse();
        if (mData.get("uid").equals(APIConfig.MOCK_USER1.UID)) {
            response.uid = APIConfig.MOCK_USER1.UID;
            response.name = APIConfig.MOCK_USER1.NAME;
            response.age = APIConfig.MOCK_USER1.AGE;
            response.gender = APIConfig.MOCK_USER1.GENDER;
            response.avatar_url = APIConfig.MOCK_USER1.AVATAR_URL;
        } else {
            response.uid = APIConfig.MOCK_USER2.UID;
            response.name = APIConfig.MOCK_USER2.NAME;
            response.age = APIConfig.MOCK_USER2.AGE;
            response.gender = APIConfig.MOCK_USER2.GENDER;
            response.avatar_url = APIConfig.MOCK_USER2.AVATAR_URL;
        }

        return response;
    }

    @Override
    protected String getURLRequest() {
        return APIConfig.BASE_URL + "me/info";
    }

    @Override
    protected Call<UserInfoResponse> call() {
        return mService.getUserInfo(mData);
    }

    public class UserInfoResponse extends BaseResponse {

        @SerializedName("uid")
        public String uid;

        @SerializedName("name")
        public String name;

        @SerializedName("age")
        public int age;

        @SerializedName("gender")
        public String gender;

        @SerializedName("avatar_url")
        public String avatar_url;
    }
}

package samples.linhtruong.com.dagger2sample.di.module.mock;

import samples.linhtruong.com.dagger2sample.network.APIConfig;
import samples.linhtruong.com.dagger2sample.network.APIService;
import samples.linhtruong.com.dagger2sample.network.request.UserInfoRequest;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 4/6/17 - 13:48.
 * @organization VED
 */

public class MockUserInfoRequest extends UserInfoRequest {

    public MockUserInfoRequest(APIService service, LoginSession session) {
        super(service, session);
    }

    @Override
    public UserInfoResponse getResponse() {
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
}

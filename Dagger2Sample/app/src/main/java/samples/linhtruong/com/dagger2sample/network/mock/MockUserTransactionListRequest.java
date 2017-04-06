package samples.linhtruong.com.dagger2sample.network.mock;

import samples.linhtruong.com.dagger2sample.network.APIConfig;
import samples.linhtruong.com.dagger2sample.network.APIService;
import samples.linhtruong.com.dagger2sample.network.request.UserTransactionListRequest;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 4/6/17 - 13:48.
 * @organization VED
 */

public class MockUserTransactionListRequest extends UserTransactionListRequest {

    public MockUserTransactionListRequest(APIService service, LoginSession session) {
        super(service, session);
    }

    @Override
    public UserTransactionListResponse getResponse() {
        UserTransactionListResponse response = new UserTransactionListResponse();
        response.error = APIConfig.MOCK.ERROR_NONE;
        response.result = APIConfig.MOCK.RESULT_OK;
        if (mLoginSession.getUid().contains("1")) {
            response.transactions.addAll(APIConfig.MOCK_USER_1_TRANSACTIONS.transactions);
        } else {
            response.transactions.addAll(APIConfig.MOCK_USER_2_TRANSACTIONS.transactions);
        }

        return response;
    }
}

package samples.linhtruong.com.dagger2sample.network.request;

import retrofit2.Call;
import samples.linhtruong.com.base.BaseHttpRequest;
import samples.linhtruong.com.base.BaseResponse;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 13:08.
 * @organization VED
 */

public class UserTransactionListRequest extends BaseHttpRequest<UserTransactionListRequest.UserTransactionListResponse> {

    @Override
    public UserTransactionListResponse getMockResponse() {
        return null;
    }

    @Override
    protected String getURLRequest() {
        return null;
    }

    @Override
    protected Call<UserTransactionListResponse> call() {
        return null;
    }

    public class UserTransactionListResponse extends BaseResponse {

    }
}

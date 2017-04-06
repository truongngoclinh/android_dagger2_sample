package samples.linhtruong.com.dagger2sample.network.request;

import com.google.gson.annotations.SerializedName;
import retrofit2.Call;
import samples.linhtruong.com.base.BaseHttpRequest;
import samples.linhtruong.com.base.BaseResponse;
import samples.linhtruong.com.dagger2sample.network.APIConfig;
import samples.linhtruong.com.dagger2sample.network.APIService;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 13:08.
 * @organization VED
 */

public class UserTransactionListRequest extends BaseHttpRequest<UserTransactionListRequest.UserTransactionListResponse> {

    public APIService mService;

    public LoginSession mLoginSession;

    public Map<String, String> mData;

    public UserTransactionListRequest(APIService service, LoginSession session) {
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
    public UserTransactionListResponse getMockResponse() {
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

    @Override
    protected String getURLRequest() {
        return APIConfig.BASE_URL + "me/transactions";
    }

    @Override
    protected Call<UserTransactionListResponse> call() {
        return mService.getTransactions(mData);
    }

    public static class UserTransactionListResponse extends BaseResponse {

        public UserTransactionListResponse() {
            transactions = new ArrayList<>();
        }

        @SerializedName("transactions")
        public ArrayList<Transaction> transactions;

        public void addMockTransaction(double amount, String branch) {
            transactions.add(new Transaction(amount, branch));
        }

        public class Transaction {

            public Transaction(double amount, String branch) {
                this.amount = amount;
                this.branch = branch;
            }

            @SerializedName("topup_amount")
            public double amount;

            @SerializedName("topup_branch")
            public String branch;
        }

    }
}

package samples.linhtruong.com.dagger2sample.home.tabs;

import bolts.Continuation;
import bolts.Task;
import samples.linhtruong.com.dagger2sample.home.HomeTabActivity;
import samples.linhtruong.com.dagger2sample.di.MockMode;
import samples.linhtruong.com.dagger2sample.network.request.UserTransactionListRequest;
import samples.linhtruong.com.dagger2sample.storage.DbManager;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;
import samples.linhtruong.com.dagger2sample.utils.base.BaseActionPresenter;
import samples.linhtruong.com.schema.Transaction;
import samples.linhtruong.com.utils.LogUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 02:42.
 * @organization VED
 */

public class HomeReportPresenter extends BaseActionPresenter<HomeReportView> {

    @Inject
    @MockMode("mock")
    UserTransactionListRequest mUserTransactionListRequest;

    @Inject
    DbManager mDbManger;

    @Inject
    LoginSession mLoginSession;

    @Inject
    TransactionView mTransactionView;

    private HomeTabActivity mActivity;

    public HomeReportPresenter(HomeTabActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onLoad() {
         // checking scope
        if (mDbManger == null) {
            LogUtils.d("[test scope] HomeReport DbManager NULL!");
        } else {
            LogUtils.d("[test scope] HomeReport DbManager is OK!");
        }

        mUserTransactionListRequest.initData();
        Task.callInBackground(new Callable<UserTransactionListRequest.UserTransactionListResponse>() {
            @Override
            public UserTransactionListRequest.UserTransactionListResponse call() throws Exception {
                return mUserTransactionListRequest.getResponse();
            }
        }).continueWith(new Continuation<UserTransactionListRequest.UserTransactionListResponse, ArrayList<Transaction>>() {
            @Override
            public ArrayList<Transaction> then(Task<UserTransactionListRequest.UserTransactionListResponse> task) throws Exception {
                ArrayList<Transaction> transactions = new ArrayList<>();
                if (!task.isFaulted()) {
                    UserTransactionListRequest.UserTransactionListResponse response = task.getResult();
                    for (UserTransactionListRequest.UserTransactionListResponse.Transaction transaction : response.transactions) {
                        transactions.add(new Transaction(transaction.amount, transaction.branch));
                    }

                    mDbManger.updateTransactionInfo(transactions, Integer.parseInt(mLoginSession.getUid()));
                }

                return transactions;
            }
        }, Task.BACKGROUND_EXECUTOR).continueWith(new Continuation<ArrayList<Transaction>, Void>() {
            @Override
            public Void then(Task<ArrayList<Transaction>> task) throws Exception {
                ArrayList<Transaction> transactions = task.getResult();
                if (transactions != null || transactions.size() > 0) {
                    updateTransactionView(transactions);
                }

                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    private void updateTransactionView(List<Transaction> transaction) {
        LogUtils.d("Update transactions: " + transaction.size());
        mTransactionView.loadData(transaction);
        getView().addView(mTransactionView);
    }

    @Override
    public HomeTabActivity getActivity() {
        return mActivity;
    }
}

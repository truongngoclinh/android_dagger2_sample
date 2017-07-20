package samples.linhtruong.com.dagger2sample.login;

import android.os.Handler;
import android.os.Looper;
import android.support.design.internal.ParcelableSparseArray;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import bolts.Continuation;
import bolts.Task;
import samples.linhtruong.com.base.BasePresenter;
import samples.linhtruong.com.dagger2sample.di.MockMode;
import samples.linhtruong.com.dagger2sample.network.APIConfig;
import samples.linhtruong.com.dagger2sample.network.request.LoginRequest;
import samples.linhtruong.com.dagger2sample.di.scope.LoginScope;
import samples.linhtruong.com.dagger2sample.network.request.UserTransactionListRequest;
import samples.linhtruong.com.dagger2sample.storage.DbManager;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;
import samples.linhtruong.com.dagger2sample.utils.Navigator;
import samples.linhtruong.com.dagger2sample.utils.base.BaseActionPresenter;
import samples.linhtruong.com.utils.LogUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:01.
 * @organization VED
 */

public class LoginPresenter extends BaseActionPresenter<LoginView> {

    public static final int FAKE_LOADING_TIME = 2000;

    @Inject
    @MockMode("mock")
    LoginRequest mLoginRequest;

    @Inject
    LoginSession mLoginSession;

    @Inject
    DbManager mDbManager;

    private LoginActivity mActivity;

    @Override
    public void onLoad() {
        getView().mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        executeLoginRequest();
                    }
                }, FAKE_LOADING_TIME); // just fake data loading time
            }
        });
    }

    public LoginPresenter(LoginActivity activity) {
        mActivity = activity;
    }

    private boolean isInputValid() {
        return (!TextUtils.isEmpty(getView().mEdtAccount.getText().toString())
                && !TextUtils.isEmpty(getView().mEdtPassword.getText().toString()));
    }

    private void executeLoginRequest() {
        if (isInputValid()) {
            mLoginRequest.initData(getView().mEdtAccount.getText().toString(),
                    getView().mEdtPassword.getText().toString());
            Task.callInBackground(new Callable<LoginRequest.LoginResponse>() {
                @Override
                public LoginRequest.LoginResponse call() throws Exception {
                    return mLoginRequest.getResponse();
                }
            }).continueWith(new Continuation<LoginRequest.LoginResponse, Void>() {
                @Override
                public Void then(Task<LoginRequest.LoginResponse> task) throws Exception {
                    hide();
                    if (!task.isFaulted()) {
                        LoginRequest.LoginResponse response = task.getResult();
                        mLoginSession.syncSession(response.uid, response.accessToken);

                        Navigator.navigateHomeActivity(mActivity);
                    } else {
                        // never drop here because we dont have server side implementation
                        // get & save token, navigate to HomeTabActivity
                    }

                    return null;
                }
            }, Task.UI_THREAD_EXECUTOR);
        } else {
            hide();
            Snackbar.make(mActivity.findViewById(android.R.id.content),
                    "Please input your info! try \"user1\" or \"user 2\"", Snackbar.LENGTH_LONG).show();

        }

    }

    @Override
    public LoginActivity getActivity() {
        return mActivity;
    }
}

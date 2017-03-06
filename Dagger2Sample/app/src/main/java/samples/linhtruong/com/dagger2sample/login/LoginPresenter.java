package samples.linhtruong.com.dagger2sample.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import bolts.Continuation;
import bolts.Task;
import samples.linhtruong.com.base.BasePresenter;
import samples.linhtruong.com.dagger2sample.R;
import samples.linhtruong.com.dagger2sample.home.HomeTabActivity_;
import samples.linhtruong.com.dagger2sample.network.request.LoginRequest;
import samples.linhtruong.com.dagger2sample.scope.LoginScope;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;
import samples.linhtruong.com.dagger2sample.utils.Navigator;
import samples.linhtruong.com.utils.LogUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:01.
 * @organization VED
 */

@LoginScope
public class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    LoginRequest mLoginRequest;

    @Inject
    LoginSession mLoginSession;

    private LoginActivity mActivity;

    @Override
    public void onLoad() {
        LogUtils.d("loaded LoginView");
        getView().mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInputValid()) {
                    mLoginRequest.initData(getView().mEdtAccount.getText().toString(),
                            getView().mEdtPassword.getText().toString());
                    // show spinner
                    Task.callInBackground(new Callable<LoginRequest.LoginResponse>() {
                        @Override
                        public LoginRequest.LoginResponse call() throws Exception {
                            return mLoginRequest.getResponse();
                        }
                    }).continueWith(new Continuation<LoginRequest.LoginResponse, Void>() {
                        @Override
                        public Void then(Task<LoginRequest.LoginResponse> task) throws Exception {
                            // hide spinner
                            if (task.getResult() == null) {
                                LogUtils.d("continue with mock response");
                                // show error code
                                // but we will use mock data here
                                LoginRequest.LoginResponse response = mLoginRequest.getMockResponse();
                                mLoginSession.syncSession(response.uid, response.accessToken);

                                Navigator.navigateHomeActivity(mActivity);
                            } else {
                                // never drop here because we dont have server side implementation
                                // get & save token, navigate to HomeTabActivity
                            }

                            return null;
                        }
                    });
                } else {
                    Snackbar.make(mActivity.findViewById(android.R.id.content),
                            "Please input your info! try \"user1\" or \"user 2\"", Snackbar.LENGTH_LONG).show();

                }
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

}

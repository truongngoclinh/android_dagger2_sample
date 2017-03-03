package samples.linhtruong.com.dagger2sample.login;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import bolts.Continuation;
import bolts.Task;
import samples.linhtruong.com.dagger2sample.base.BasePresenter;
import samples.linhtruong.com.dagger2sample.network.request.LoginRequest;
import samples.linhtruong.com.dagger2sample.scope.LoginScope;

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

    private LoginActivity mActivity;

    @Override
    protected void onLoad() {
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
                            if (task.isFaulted()) {
                                // show error code
                            } else {
                                // get & save token, navigate to HomeActivity
                            }

                            return null;
                        }
                    });
                } else {
                    Snackbar.make(mActivity.findViewById(android.R.id.content),
                            "Please input your info!", Snackbar.LENGTH_LONG).show();

                }
            }
        });
    }

    public LoginPresenter(LoginActivity activity) {
        mActivity = activity;
    }

    private boolean isInputValid() {
        return (!TextUtils.isEmpty(getView().mEdtAccount.toString())
                && !TextUtils.isEmpty(getView().mEdtPassword.toString()));
    }

}

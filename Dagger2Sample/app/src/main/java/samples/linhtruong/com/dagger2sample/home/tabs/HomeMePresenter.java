package samples.linhtruong.com.dagger2sample.home.tabs;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import bolts.Continuation;
import bolts.Task;
import samples.linhtruong.com.base.BasePresenter;
import samples.linhtruong.com.base.BaseResponse;
import samples.linhtruong.com.dagger2sample.home.HomeTabActivity;
import samples.linhtruong.com.dagger2sample.home.utils.CircleTransform;
import samples.linhtruong.com.dagger2sample.di.MockMode;
import samples.linhtruong.com.dagger2sample.network.request.LogoutRequest;
import samples.linhtruong.com.dagger2sample.network.request.UserInfoRequest;
import samples.linhtruong.com.dagger2sample.network.request.UserTransactionListRequest;
import samples.linhtruong.com.dagger2sample.storage.DbManager;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;
import samples.linhtruong.com.dagger2sample.storage.UserStore;
import samples.linhtruong.com.dagger2sample.utils.Navigator;
import samples.linhtruong.com.dagger2sample.utils.base.BaseActionPresenter;
import samples.linhtruong.com.schema.User;
import samples.linhtruong.com.utils.LogUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 02:41.
 * @organization VED
 */

public class HomeMePresenter extends BaseActionPresenter<HomeMeView> {

    @Inject
    @MockMode("mock")
    UserInfoRequest mUserInfoRequest;

    @Inject
    @MockMode("mock")
    LogoutRequest mLogoutRequest;

    @Inject
    UserStore mUserStore;

    @Inject
    UserStore mUserStore2;

    @Inject
    LoginSession mLoginSession;

    @Inject
    DbManager mDManager;

    private HomeTabActivity mActivity;

    public HomeMePresenter(HomeTabActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onLoad() {
        initLogoutRequest();

        // checking scope
        getView().mBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if (mDManager == null) {
            LogUtils.d("[test scope] HomeMe DbManager NULL!");
        } else {
            LogUtils.d("[test scope] HomeMe DbManager is OK!");
        }

        mUserInfoRequest.initData();
        Task.callInBackground(new Callable<UserInfoRequest.UserInfoResponse>() {
            @Override
            public UserInfoRequest.UserInfoResponse call() throws Exception {
                return mUserInfoRequest.getResponse();
            }
        }).continueWith(new Continuation<UserInfoRequest.UserInfoResponse, User>() {
            @Override
            public User then(Task<UserInfoRequest.UserInfoResponse> task) throws Exception {
                User user = null;
                if (!task.isFaulted()) {
                    UserInfoRequest.UserInfoResponse response = task.getResult();
                    user = new User(response.age, response.uid, response.gender, response.name, response.avatar_url);

                    // sync sharepreferences
                    mUserStore.syncUserInfo(user);

                    // sync db
                    mDManager.initUserDB(user.uid);
                    mDManager.updateUserInfo(user);
                }

                return user;
            }
        }, Task.BACKGROUND_EXECUTOR).continueWith(new Continuation<User, Void>() {
            @Override
            public Void then(Task<User> task) throws Exception {
                LogUtils.d("continue with update views");
                if (task.getResult() != null) {
                    // update ui
                    updateInfo(task.getResult());
                } else {
                    LogUtils.d("user is null");
                }

                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    private void initLogoutRequest() {
        getView().mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        executeLogoutRequest();
                    }
                }, 2000); // just fake data loading time
            }
        });
    }

    private void executeLogoutRequest() {
        mLogoutRequest.initData();
        Task.callInBackground(new Callable<BaseResponse>() {
            @Override
            public BaseResponse call() throws Exception {
                return mLogoutRequest.getResponse();
            }
        }).continueWith(new Continuation<BaseResponse, Void>() {
            @Override
            public Void then(Task<BaseResponse> task) throws Exception {
                hide();
                if (!task.isFaulted()) {
                    mLoginSession.clearSession();
                    Navigator.navigateLoginActivity(mActivity);
                }

                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    private void updateInfo(User user) {
        if (user != null) {
            LogUtils.d("update user view");
            getView().mTxtName.setText(user.name);
            Picasso.with(mActivity).load(user.avatar_url).transform(new CircleTransform()).into(getView().mImgAvatar);
        }
    }

    @Override
    public HomeTabActivity getActivity() {
        return mActivity;
    }
}


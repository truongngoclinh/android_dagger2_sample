package samples.linhtruong.com.dagger2sample.home.tabs;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import bolts.Task;
import samples.linhtruong.com.base.BasePresenter;
import samples.linhtruong.com.dagger2sample.home.HomeTabActivity;
import samples.linhtruong.com.dagger2sample.network.request.UserInfoRequest;
import samples.linhtruong.com.dagger2sample.storage.UserStore;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 02:41.
 * @organization VED
 */

public class HomeMePresenter extends BasePresenter<HomeMeView> {

    @Inject
    UserInfoRequest mUserInfoRequest;

    @Inject
    UserStore mUserStore;

    private HomeTabActivity mActivity;

    public HomeMePresenter(HomeTabActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onLoad() {
        mUserInfoRequest.initData();
        Task.callInBackground(new Callable<UserInfoRequest.UserInfoResponse>() {
            @Override
            public UserInfoRequest.UserInfoResponse call() throws Exception {
                return null;
            }
        });
    }
}

package samples.linhtruong.com.dagger2sample.home;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import samples.linhtruong.com.dagger2sample.app.App;
import samples.linhtruong.com.dagger2sample.scope.UserScope;
import samples.linhtruong.com.dagger2sample.component.UserComponent;
import samples.linhtruong.com.utils.LogUtils;

/**
 * Created by linhtruong on 12/2/16 - 10:53.
 * Description: this activity represents usage of tab with viewpager
 */

@EActivity
public class HomeTabActivity extends FragmentActivity {

    @UserScope
    UserComponent mUserComponent;

    @Extra
    String access_token;

    @Extra
    String uid;

    private HomeTabPresenter mPresenter;
    private HomeTabView mView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDenpdency();

        mView = HomeTabView_.build(this);
        setContentView(mView);
        mPresenter = new HomeTabPresenter(this);
        mPresenter.takeView(mView);
        mUserComponent.inject(mPresenter);
    }

    private void initDenpdency() {
        LogUtils.d("uid = " + uid + " - token: " + access_token);
        mUserComponent = UserComponent.Initializer.init(App.getAppcomponent(), this, uid);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.dropView(mView);
        mView = null;
    }
}

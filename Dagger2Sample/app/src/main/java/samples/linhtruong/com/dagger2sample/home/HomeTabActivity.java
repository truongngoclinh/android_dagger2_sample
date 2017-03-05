package samples.linhtruong.com.dagger2sample.home;

import android.os.Bundle;
import android.view.View;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import javax.inject.Inject;

import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.base.BasePresenter;
import samples.linhtruong.com.dagger2sample.app.App;
import samples.linhtruong.com.dagger2sample.scope.UserScope;
import samples.linhtruong.com.dagger2sample.component.UserComponent;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;
import samples.linhtruong.com.utils.LogUtils;

/**
 * Created by linhtruong on 12/2/16 - 10:53.
 * Description: this activity represents usage of tab with viewpager
 */

@EActivity
public class HomeTabActivity extends BaseActivity {

    @UserScope
    UserComponent mUserComponent;

    @Inject
    LoginSession mLoginSession;

    private HomeTabPresenter mPresenter;
    private HomeTabView mView;

    @Override
    protected void onCreateUI(Bundle bundle) {
        mView = HomeTabView_.build(this, mUserComponent);
        setContentView(mView);
        mPresenter = new HomeTabPresenter(this);
        mPresenter.takeView(mView);
    }

    @Override
    protected void initDependency() {
        mUserComponent = UserComponent.Initializer.init(App.getAppcomponent(), this);
        mUserComponent.inject(this);
        mUserComponent.inject(mUserComponent.exposeUserInfoRequest());
        mUserComponent.inject(mUserComponent.exposeUserTransactionListRequest());

        LogUtils.d("uid = " + mLoginSession.getUid() + " - token: " + mLoginSession.getToken());
    }

    @Override
    protected BasePresenter<? extends View> presenter() {
        return mPresenter;
    }

    @Override
    protected boolean isValid() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.dropView(mView);
        mView = null;
    }
}

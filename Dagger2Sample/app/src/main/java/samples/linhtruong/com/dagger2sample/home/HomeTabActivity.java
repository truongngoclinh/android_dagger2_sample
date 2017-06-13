package samples.linhtruong.com.dagger2sample.home;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import org.androidannotations.annotations.EActivity;

import javax.inject.Inject;

import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.base.BasePresenter;
import samples.linhtruong.com.dagger2sample.R;
import samples.linhtruong.com.dagger2sample.app.App;
import samples.linhtruong.com.dagger2sample.home.tabs.HomeMeView;
import samples.linhtruong.com.dagger2sample.di.scope.UserScope;
import samples.linhtruong.com.dagger2sample.di.component.UserComponent;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;
import samples.linhtruong.com.utils.LogUtils;

/**
 * Created by linhtruong on 12/2/16 - 10:53.
 * Description: this activity represents usage of tab with custom tab view
 */

@EActivity
public class HomeTabActivity extends BaseActivity {

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

    @Override
    public void onBackPressed() {
        if (mView.getCurrentView() instanceof HomeMeView) {
            super.onBackPressed();
        } else {
            mView.setSelectedTab(HomeTabView.ME_INDEX, null);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}

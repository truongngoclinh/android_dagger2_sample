package samples.linhtruong.com.dagger2sample.login;

import android.os.Bundle;
import android.view.View;

import org.androidannotations.annotations.EActivity;

import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.base.BasePresenter;
import samples.linhtruong.com.dagger2sample.app.App;
import samples.linhtruong.com.dagger2sample.di.component.LoginComponent;
import samples.linhtruong.com.dagger2sample.di.scope.LoginScope;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:01.
 * @organization VED
 */

@EActivity
public class LoginActivity extends BaseActivity {

    private LoginView mView;
    private LoginPresenter mPresenter;

    @LoginScope
    private LoginComponent mLoginComponent;

    @Override
    protected void onCreateUI(Bundle bundle) {
        mView = LoginView_.build(this);
        setContentView(mView);

        mPresenter = new LoginPresenter(this);
        mLoginComponent.inject(mPresenter);
        mPresenter.takeView(mView);
    }

    @Override
    public void initDependency() {
        mLoginComponent = LoginComponent.Initializer.init(App.getAppcomponent());
    }

    @Override
    protected boolean isValid() {
        return true;
    }

    @Override
    protected BasePresenter<? extends View> presenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.dropView(mView);
        mView = null;
    }
}

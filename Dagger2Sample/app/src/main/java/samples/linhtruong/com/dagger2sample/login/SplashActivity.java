package samples.linhtruong.com.dagger2sample.login;

import android.os.Bundle;
import android.os.Handler;

import org.androidannotations.annotations.EActivity;

import javax.inject.Inject;

import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.dagger2sample.R;
import samples.linhtruong.com.dagger2sample.app.App;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;
import samples.linhtruong.com.dagger2sample.utils.Navigator;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/6/17 - 11:48.
 * @organization VED
 */

@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    @Inject
    LoginSession mLoginSession;

    @Override
    protected void onCreateUI(Bundle bundle) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mLoginSession.isLogged()) {
                    // user already logged, navigate directly to HomeTabActivity
                    Navigator.navigateHomeActivity(SplashActivity.this);
                } else {
                    // not logged yet, request login
                    Navigator.navigateLoginActivity(SplashActivity.this);
                }
            }
        }, 2000);
    }

    @Override
    protected void initDependency() {
        App.getAppcomponent().inject(this);
    }

    @Override
    protected boolean isValid() {
        return true;
    }
}

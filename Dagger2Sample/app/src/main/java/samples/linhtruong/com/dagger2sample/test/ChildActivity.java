package samples.linhtruong.com.dagger2sample.test;

import android.os.Bundle;
import dagger.Provides;
import samples.linhtruong.com.dagger2sample.di.MockMode;
import samples.linhtruong.com.dagger2sample.network.request.LoginRequest;
import samples.linhtruong.com.dagger2sample.storage.DbManager;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;
import samples.linhtruong.com.dagger2sample.storage.UserStore;
import samples.linhtruong.com.dagger2sample.utils.base.BaseActionActivity;

import javax.inject.Inject;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 5/26/17 - 15:15.
 * @organization VED
 */

public class ChildActivity extends BaseActionActivity {

    @Inject
    @MockMode("mock")
    UserStore mUserStore;

    @Inject
    @MockMode("mock")
    DbManager mDbManager;

    @Inject
    @MockMode("mock")
    LoginRequest mLoginRequest;

    @Override
    protected void initDependency() {

    }

    @Override
    protected void onCreateContent(Bundle bundle) {

    }
}

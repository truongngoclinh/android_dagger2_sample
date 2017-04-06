package samples.linhtruong.com.dagger2sample.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import samples.linhtruong.com.dagger2sample.di.MockMode;
import samples.linhtruong.com.dagger2sample.home.tabs.TransactionView;
import samples.linhtruong.com.dagger2sample.home.tabs.TransactionView_;
import samples.linhtruong.com.dagger2sample.network.APIService;
import samples.linhtruong.com.dagger2sample.network.mock.MockLogoutRequest;
import samples.linhtruong.com.dagger2sample.network.mock.MockUserInfoRequest;
import samples.linhtruong.com.dagger2sample.network.mock.MockUserTransactionListRequest;
import samples.linhtruong.com.dagger2sample.network.request.LogoutRequest;
import samples.linhtruong.com.dagger2sample.network.request.UserInfoRequest;
import samples.linhtruong.com.dagger2sample.network.request.UserTransactionListRequest;
import samples.linhtruong.com.dagger2sample.di.scope.UserScope;
import samples.linhtruong.com.dagger2sample.storage.LoginSession;
import samples.linhtruong.com.dagger2sample.storage.UserStore;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 00:07.
 * @organization VED
 */

@Module
public class UserModule {

    private final Context mContext;

    public UserModule(Context context) {
        mContext = context;
    }

    @UserScope
    @Provides
    UserStore provideUserStore() {
        return new UserStore(mContext);
    }

    @Provides
    @MockMode("prod")
    UserInfoRequest provideUserRequest(APIService service, LoginSession session) {
        return new UserInfoRequest(service, session);
    }

    @Provides
    @MockMode("mock")
    UserInfoRequest provideMockUserRequest(APIService service, LoginSession session) {
        return new MockUserInfoRequest(service, session);
    }

    @Provides
    @MockMode("prod")
    UserTransactionListRequest provideTransactionListRequest(APIService service, LoginSession session) {
        return new UserTransactionListRequest(service, session);
    }

    @Provides
    @MockMode("mock")
    UserTransactionListRequest provideMockTransactionListRequest(APIService service, LoginSession session) {
        return new MockUserTransactionListRequest(service, session);
    }

    @Provides
    @MockMode("prod")
    LogoutRequest provideLogoutRequest(APIService service, LoginSession session) {
        return new LogoutRequest(service, session);
    }

    @Provides
    @MockMode("mock")
    LogoutRequest provideMockLogoutRequest(APIService service, LoginSession session) {
        return new MockLogoutRequest(service, session);
    }

    @UserScope
    @Provides
    TransactionView provideTransactionView() {
        TransactionView view = TransactionView_.build(mContext);
        view.init();
        return view;
    }
}

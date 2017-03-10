package samples.linhtruong.com.dagger2sample.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import samples.linhtruong.com.dagger2sample.home.tabs.TransactionView;
import samples.linhtruong.com.dagger2sample.home.tabs.TransactionView_;
import samples.linhtruong.com.dagger2sample.network.request.LogoutRequest;
import samples.linhtruong.com.dagger2sample.network.request.UserInfoRequest;
import samples.linhtruong.com.dagger2sample.network.request.UserTransactionListRequest;
import samples.linhtruong.com.dagger2sample.scope.UserScope;
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

    @UserScope
    @Provides
    UserInfoRequest provideUserRequest() {
        return new UserInfoRequest();
    }

    @UserScope
    @Provides
    UserTransactionListRequest provideTransactionListRequest() {
        return new UserTransactionListRequest();
    }

    @UserScope
    @Provides
    LogoutRequest provideLogoutRequest() {
        return new LogoutRequest();
    }

    @UserScope
    @Provides
    TransactionView provideTransactionView() {
        TransactionView view = TransactionView_.build(mContext);
        view.init();
        return view;
    }
}

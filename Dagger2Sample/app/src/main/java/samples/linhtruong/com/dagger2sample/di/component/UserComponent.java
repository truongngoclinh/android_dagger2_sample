package samples.linhtruong.com.dagger2sample.di.component;

import android.content.Context;

import dagger.Component;
import samples.linhtruong.com.dagger2sample.home.HomeTabActivity;
import samples.linhtruong.com.dagger2sample.home.tabs.HomeMePresenter;
import samples.linhtruong.com.dagger2sample.home.tabs.HomeReportPresenter;
import samples.linhtruong.com.dagger2sample.di.module.UserModule;
import samples.linhtruong.com.dagger2sample.di.scope.UserScope;
import samples.linhtruong.com.dagger2sample.network.request.LogoutRequest;
import samples.linhtruong.com.dagger2sample.network.request.UserInfoRequest;
import samples.linhtruong.com.dagger2sample.network.request.UserTransactionListRequest;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 00:09.
 * @organization VED
 */

@UserScope
@Component(
        modules = UserModule.class,
        dependencies = {AppComponent.class}
)
public interface UserComponent {

    final class Initializer {
        public static UserComponent init(AppComponent appComponent, Context context) {
            return DaggerUserComponent.builder().appComponent(appComponent).userModule(new UserModule(context)).build();
        }
    }

    void inject(HomeTabActivity activity);
    void inject(HomeMePresenter presenter);
    void inject(HomeReportPresenter presenter);
    void inject(UserInfoRequest request);
    void inject(UserTransactionListRequest request);
    void inject(LogoutRequest request);
}

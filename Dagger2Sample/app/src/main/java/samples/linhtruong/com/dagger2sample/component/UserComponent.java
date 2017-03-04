package samples.linhtruong.com.dagger2sample.component;

import android.content.Context;

import dagger.Component;
import samples.linhtruong.com.dagger2sample.home.HomeTabActivity;
import samples.linhtruong.com.dagger2sample.home.HomeTabPresenter;
import samples.linhtruong.com.dagger2sample.module.UserModule;
import samples.linhtruong.com.dagger2sample.scope.UserScope;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 00:09.
 * @organization VED
 */

@UserScope
@Component(
        modules = {UserModule.class},
        dependencies = {AppComponent.class}
)
public interface UserComponent {

    final class Initializer {
        public static UserComponent init(AppComponent appComponent, Context context, String uid) {
            return DaggerUserComponent.builder().appComponent(appComponent).userModule(new UserModule(context, uid)).build();
        }
    }

    void inject(HomeTabActivity activity);
    void inject(HomeTabPresenter presenter);
}

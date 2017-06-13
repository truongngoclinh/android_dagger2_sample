package samples.linhtruong.com.dagger2sample.di.component;

import dagger.Component;
import samples.linhtruong.com.dagger2sample.di.scope.UserScope;
import samples.linhtruong.com.dagger2sample.login.LoginActivity;
import samples.linhtruong.com.dagger2sample.login.LoginPresenter;
import samples.linhtruong.com.dagger2sample.di.module.LoginModule;
import samples.linhtruong.com.dagger2sample.network.request.LoginRequest;
import samples.linhtruong.com.dagger2sample.di.scope.LoginScope;
import samples.linhtruong.com.utils.LogUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:50.
 * @organization VED
 */

@LoginScope
@Component(
        modules = LoginModule.class,
        dependencies = AppComponent.class )
public interface LoginComponent {

    final class Initializer {
        public static LoginComponent init(AppComponent appComponent) {
            LogUtils.d("[test scope] init LoginComponent");
            return DaggerLoginComponent.builder().appComponent(appComponent).build();
        }
    }

    void inject(LoginActivity activity);
    void inject(LoginPresenter presenter);
    void inject(LoginRequest request);
}

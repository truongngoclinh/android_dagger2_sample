package samples.linhtruong.com.dagger2sample.component;

import dagger.Component;
import samples.linhtruong.com.dagger2sample.login.LoginActivity;
import samples.linhtruong.com.dagger2sample.login.LoginPresenter;
import samples.linhtruong.com.dagger2sample.module.LoginModule;
import samples.linhtruong.com.dagger2sample.network.request.LoginRequest;
import samples.linhtruong.com.dagger2sample.scope.LoginScope;

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
        dependencies = AppComponent.class)
public interface LoginComponent {

    final class Initializer {
        public static LoginComponent initialize(AppComponent appComponent) {
            return DaggerLoginComponent.builder().appComponent(appComponent).build();
        }
    }

    void inject(LoginPresenter presenter);
    void inject(LoginRequest request);

    LoginRequest exposeLoginRequest();
}

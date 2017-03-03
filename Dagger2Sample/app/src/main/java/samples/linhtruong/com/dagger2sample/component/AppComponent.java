package samples.linhtruong.com.dagger2sample.component;

import dagger.Component;
import samples.linhtruong.com.dagger2sample.app.App;
import samples.linhtruong.com.dagger2sample.module.AppModule;
import samples.linhtruong.com.dagger2sample.module.NetworkModule;
import samples.linhtruong.com.dagger2sample.network.APIService;
import samples.linhtruong.com.dagger2sample.scope.ApplicationScope;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:36.
 * @organization VED
 */

@ApplicationScope
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    final class Initialiazer {
        public static AppComponent initialize(App app) {
            return DaggerAppComponent.builder().appModule(new AppModule(app)).build();
        }
    }

    APIService exposeAPIService();
}

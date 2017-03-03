package samples.linhtruong.com.dagger2sample.module;

import dagger.Module;
import dagger.Provides;
import samples.linhtruong.com.dagger2sample.app.App;
import samples.linhtruong.com.dagger2sample.scope.ApplicationScope;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 00:33.
 * @organization VED
 */

@Module
public class AppModule {

    private final App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @ApplicationScope
    @Provides
    App provideApplicationContext() {
        return mApp;
    }

}

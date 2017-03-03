package samples.linhtruong.com.dagger2sample.app;

import android.app.Application;

import samples.linhtruong.com.dagger2sample.component.AppComponent;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 00:32.
 * @organization VED
 */

public class App extends Application {

    private static volatile AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initDependency();
    }

    private void initDependency() {
        mAppComponent = AppComponent.Initialiazer.initialize(this);
    }

    public static AppComponent getAppcomponent() {
        return mAppComponent;
    }
}

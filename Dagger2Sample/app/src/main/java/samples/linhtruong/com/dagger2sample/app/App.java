package samples.linhtruong.com.dagger2sample.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import io.realm.Realm;
import samples.linhtruong.com.dagger2sample.component.AppComponent;
import samples.linhtruong.com.utils.ScreenUtils;

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
        initUtils();
    }

    private void initDependency() {
        mAppComponent = AppComponent.Initialiazer.init(this);
    }

    private void initUtils() {
        ScreenUtils.init(this);
        Realm.init(this);

        Stetho.initializeWithDefaults(this);
    }

    public static AppComponent getAppcomponent() {
        return mAppComponent;
    }
}

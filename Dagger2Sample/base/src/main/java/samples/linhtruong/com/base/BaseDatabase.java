package samples.linhtruong.com.base;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import samples.linhtruong.com.utils.LogUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 00:47.
 * @organization VED
 */

public abstract class BaseDatabase {

    private RealmConfiguration mConfig;

    public Realm getInstance() {
        LogUtils.d("get realm instance");
        if (mConfig == null) {
            synchronized (this) {
                if (mConfig == null) {
                    mConfig = new RealmConfiguration.Builder()
                            .name(getName())
                            .schemaVersion(getSchemaVersion())
                            .modules(getModule())
                            .build();
                    LogUtils.d("config created with: " + getName());
                }
            }
        }

        return Realm.getInstance(mConfig);
    }

    public abstract String getName();

    public abstract long getSchemaVersion();

    public abstract Object getModule();
}

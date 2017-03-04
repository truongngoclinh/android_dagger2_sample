package samples.linhtruong.com.base;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 00:47.
 * @organization VED
 */

public abstract class BaseDatabase {

    private Context mContext;
    private RealmConfiguration mConfig;

    public BaseDatabase(Context context) {
        mContext = context;
    }

    public Realm getInstance() {
        if (mConfig != null) {
            synchronized (this) {
                if (mConfig != null) {
                    mConfig = new RealmConfiguration.Builder(mContext)
                            .name(getName())
                            .schemaVersion(getSchemaVersion())
                            .modules(getModule())
                            .build();
                }
            }
        }

        return Realm.getInstance(mConfig);
    }

    public abstract String getName();

    public abstract long getSchemaVersion();

    public abstract Object getModule();
}

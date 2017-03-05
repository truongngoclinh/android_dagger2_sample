package samples.linhtruong.com.dagger2sample.storage;

import android.content.Context;

import samples.linhtruong.com.base.BaseDatabase;
import samples.linhtruong.com.dagger2sample.storage.module.UserModule;

/**
 * Each user has its own database
 *
 * @author linhtruong
 * @date 3/5/17 - 00:49.
 * @organization VED
 */

public class UserDatabase extends BaseDatabase {

    public static final String USER_PREF = "user_db";
    public static final int SCHEMA_VERSION = 0;

    private String mUid;

    public UserDatabase(Context context, String uid) {
        super(context);

        mUid = uid;
    }

    @Override
    public String getName() {
        return USER_PREF + mUid;
    }

    @Override
    public long getSchemaVersion() {
       return SCHEMA_VERSION;
    }

    @Override
    public Object getModule() {
        return new UserModule();
    }
}

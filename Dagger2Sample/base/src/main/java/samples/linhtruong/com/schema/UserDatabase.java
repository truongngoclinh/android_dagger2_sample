package samples.linhtruong.com.schema;

import samples.linhtruong.com.base.BaseDatabase;

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

    public UserDatabase(String uid) {
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

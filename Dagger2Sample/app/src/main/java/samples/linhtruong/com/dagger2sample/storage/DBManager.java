package samples.linhtruong.com.dagger2sample.storage;

import android.content.Context;

import io.realm.Realm;
import samples.linhtruong.com.dagger2sample.storage.schema.User;
import samples.linhtruong.com.utils.LogUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 00:59.
 * @organization VED
 */

public class DbManager {

    private Context mContext;
    private UserDatabase mUserDatabase;
    private Realm mRealm;

    public DbManager(Context context) {
        mContext = context;
    }

    public UserDatabase initUserDB(String uid) {
        mUserDatabase = new UserDatabase(mContext, uid);
        mRealm = mUserDatabase.getInstance();

        if (mRealm == null || mUserDatabase == null) {
            LogUtils.d("something null: realm = " + mRealm + " - userDB = " + mUserDatabase);
        }

        return mUserDatabase;
    }

    public void updateUserInfo(User user) {
        LogUtils.d("update user: " + user.uid);
        mRealm.beginTransaction();
        mRealm.copyFromRealm(user);
        mRealm.commitTransaction();
    }

    public User queryUserInfo() {
        User user = mRealm.where(User.class).findFirst();

        if (user == null) {
            LogUtils.d("no user in current user database");
        }

        return user;
    }
}

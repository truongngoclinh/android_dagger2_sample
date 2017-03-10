package samples.linhtruong.com.dagger2sample.storage;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmMigrationNeededException;
import samples.linhtruong.com.schema.Transaction;
import samples.linhtruong.com.schema.User;
import samples.linhtruong.com.schema.UserDatabase;
import samples.linhtruong.com.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

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
        LogUtils.d("init DB: " + uid);
        mUserDatabase = new UserDatabase(uid);
        mRealm = mUserDatabase.getInstance();

        if (mRealm == null || mUserDatabase == null) {
            LogUtils.d("something null: realm = " + mRealm + " - userDB = " + mUserDatabase);
        }

        return mUserDatabase;
    }

    public void updateUserInfo(User user) {
        LogUtils.d("update user: " + user.uid);
        mRealm = mUserDatabase.getInstance();
        try {
            mRealm.beginTransaction();
            mRealm.copyToRealmOrUpdate(user);
            mRealm.commitTransaction();
        } catch (RealmException e) {
            LogUtils.d("update user: " + user.uid + " exception: " + e.toString());
        }
    }

    public void updateTransactionInfo(ArrayList<Transaction> transactions, int userId) {
        LogUtils.d("update transaction of user: " + userId);
        mRealm = mUserDatabase.getInstance();
        try {
            mRealm.beginTransaction();
            LogUtils.d("begin transaction");
            mRealm.copyToRealm(transactions);
            LogUtils.d("update transactions");

            User user = queryUserInfo(userId);
            user.transactions.addAll(transactions);

            mRealm.commitTransaction();
        } catch (RealmMigrationNeededException e) {
            LogUtils.d("exception: " + e.toString());
        } catch (RealmException e) {
            LogUtils.d("exception: " + e.toString());
        }
    }

    public List<Transaction> getTransactions(int userId) {
        User user = queryUserInfo(userId);
        RealmList<Transaction> transactions = user.transactions;

        if (transactions == null || transactions.size() == 0) {
            LogUtils.d("no transaction found for this user");
        }

        return transactions;
    }

    public User queryUserInfo(int userId) {
        User user = mRealm.where(User.class).equalTo("uid", String.valueOf(userId)).findFirst();

        if (user == null) {
            LogUtils.d("no user in current user database");
        }

        return user;
    }
}

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
        LogUtils.d("[test scope] init DbManager()");
        mContext = context;
    }

    public UserDatabase initUserDB(String uid) {
        LogUtils.d("init DB: " + uid);
        mUserDatabase = new UserDatabase(uid);

        if (mRealm == null || mUserDatabase == null) {
            LogUtils.d("something null: realm = " + mRealm + " - userDB = " + mUserDatabase);
        }

        return mUserDatabase;
    }

    public void updateUserInfo(final User user) {
        LogUtils.d("update user: " + user.uid);
        mRealm = mUserDatabase.getInstance();
        try {
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    User newUser = realm.createObject(User.class);
                    newUser.age = user.age;
                    newUser.uid = user.uid;
                    newUser.gender = user.gender;
                    newUser.avatar_url = user.avatar_url;
                    newUser.name = user.name;
                    if (user.transactions == null || user.transactions.size() == 0) {
                        newUser.transactions = new RealmList<>();
                    } else {
                        newUser.transactions.addAll(user.transactions);
                    }
                }
            });
        } catch (Exception e) {
            LogUtils.d("update user: " + user.uid + " exception: " + e.toString());
        }
    }

    public void updateTransactionInfo(final ArrayList<Transaction> transactions, int userId) {
        LogUtils.d("update transaction of user: " + userId);
        mRealm = mUserDatabase.getInstance();
        try {
            LogUtils.d("begin transaction");
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    for (Transaction transaction : transactions) {
                        realm.copyToRealm(transaction);
                        LogUtils.d("update transaction: " + transaction.toString());
                    }
                }
            });

            final User user = mRealm.copyFromRealm(queryUserInfo(userId));
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (user.transactions.size() > 0) {
                        // TODO
                        LogUtils.d("transactions size = " + user.transactions.size());
                    } else {
                        user.transactions.addAll(transactions);
                    }
                }
            });
        } catch (Exception e) {
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

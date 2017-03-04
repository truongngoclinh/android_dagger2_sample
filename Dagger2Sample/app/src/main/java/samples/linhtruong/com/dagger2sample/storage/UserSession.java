package samples.linhtruong.com.dagger2sample.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 23:54.
 * @organization VED
 */

public class UserSession {

    public static final String KEY_USER_ACCESS_TOKEN = "access_token";
    public static final String KEY_USER_LOGIN_STATUS = "login_status";

    private SharedPreferences mSharePreferences;

    public UserSession(Context context, String uid) {
        mSharePreferences = context.getSharedPreferences(uid, Context.MODE_PRIVATE);
    }

    public boolean putString(String key, String value) {
        if (mSharePreferences == null) return false;

        mSharePreferences.edit().putString(key, value).commit();
        return true;
    }

    public boolean putInt(String key, int value) {
        if (mSharePreferences == null) return false;

        mSharePreferences.edit().putInt(key, value).commit();
        return true;
    }

    public boolean putBoolean(String key, boolean value) {
        if (mSharePreferences == null) return false;

        mSharePreferences.edit().putBoolean(key, value).commit();
        return true;
    }

    public String getString(String key) {
        if (mSharePreferences == null) return "";

        return mSharePreferences.getString(key, "");
    }

    public int getInt(String key) {
        if (mSharePreferences == null) return -1;

        return mSharePreferences.getInt(key, -1);
    }

    public boolean getBoolean(String key) {
        if (mSharePreferences == null) return false;

        return mSharePreferences.getBoolean(key, false);
    }
}

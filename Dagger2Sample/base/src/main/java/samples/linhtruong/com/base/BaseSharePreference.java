package samples.linhtruong.com.base;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 13:32.
 * @organization VED
 */

public abstract class BaseSharePreference {

    protected SharedPreferences mSharePreferences;

    public BaseSharePreference(Context context) {
        mSharePreferences = context.getSharedPreferences(getPrefKey(), Context.MODE_PRIVATE);
    }

    protected abstract String getPrefKey();

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

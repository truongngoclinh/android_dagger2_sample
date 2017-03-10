package samples.linhtruong.com.dagger2sample.storage;

import android.content.Context;

import samples.linhtruong.com.base.BaseSharePreference;

/**
 * CLASS DESCRIPTION
 *
I
 * @date 3/4/17 - 23:54.
 * @organization VED
 */

public class LoginSession extends BaseSharePreference {

    private static final String PREF_LOGIN = "pref_login";

    public static final String KEY_USER_ACCESS_TOKEN = "access_token";
    public static final String KEY_USER_LOGIN_STATUS = "login_status";
    public static final String KEY_USER_UID = "user_logged_uid";

    public LoginSession(Context context) {
        super(context);
    }

    @Override
    protected String getPrefKey() {
        return PREF_LOGIN;
    }

    public void syncSession(String uid, String token) {
        putBoolean(KEY_USER_LOGIN_STATUS, true);
        putString(KEY_USER_UID, uid);
        putString(KEY_USER_ACCESS_TOKEN, token);
    }

    public void clearSession() {
        putBoolean(KEY_USER_LOGIN_STATUS, false);
        putString(KEY_USER_UID, "");
        putString(KEY_USER_ACCESS_TOKEN, "");
    }

    public boolean isLogged() {
        return getBoolean(KEY_USER_LOGIN_STATUS);
    }

    public String getUid() {
        return getString(KEY_USER_UID);
    }

    public String getToken() {
        return getString(KEY_USER_ACCESS_TOKEN);
    }
}


package samples.linhtruong.com.dagger2sample.storage;

import android.content.Context;

import samples.linhtruong.com.base.BaseSharePreference;
import samples.linhtruong.com.dagger2sample.storage.schema.User;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 13:32.
 * @organization VED
 */

public class UserStore extends BaseSharePreference {

    private static final String PREF_USER = "user";

    public static final String KEY_UID = "user_uid";
    public static final String KEY_GENDER = "user_gender";
    public static final String KEY_AGE = "user_age";
    public static final String KEY_NAME = "user_name";
    public static final String KEY_AVATAR_URL = "user_avatar_url";

    public UserStore(Context context) {
        super(context);
    }

    @Override
    protected String getPrefKey() {
        return PREF_USER;
    }

    public void syncUserInfo(User user) {
        putString(KEY_UID, user.uid);
        putString(KEY_GENDER, user.gender);
        putString(KEY_NAME, user.name);
        putString(KEY_AVATAR_URL, user.avatar_url);
        putInt(KEY_AGE, user.age);
    }

    public User getUser() {
        return new User(getInt(KEY_AGE),
                getString(KEY_UID),
                getString(KEY_GENDER),
                getString(KEY_NAME),
                getString(KEY_AVATAR_URL));
    }
}

package samples.linhtruong.com.dagger2sample.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import samples.linhtruong.com.dagger2sample.scope.UserScope;
import samples.linhtruong.com.dagger2sample.storage.UserSession;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 00:07.
 * @organization VED
 */

@Module
public class UserModule {

    private final Context mContext;
    private final String mUid;

    public UserModule(Context context, String uid) {
        mContext = context;
        mUid = uid;
    }

    @UserScope
    @Provides
    UserSession provideUserStore() {
        return new UserSession(mContext, mUid);
    }
}

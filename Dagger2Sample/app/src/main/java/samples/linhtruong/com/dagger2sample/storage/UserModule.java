package samples.linhtruong.com.dagger2sample.storage;

import io.realm.annotations.RealmModule;
import samples.linhtruong.com.dagger2sample.storage.schema.User;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 00:51.
 * @organization VED
 */

@RealmModule(classes = {User.class})
public class UserModule {
}

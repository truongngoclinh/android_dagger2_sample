package samples.linhtruong.com.dagger2sample.storage.schema;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 00:42.
 * @organization VED
 */

public class User extends RealmObject {

    @PrimaryKey
    public int id;

    public int age;
    public String uid;
    public String gender;
}

package samples.linhtruong.com.schema;

import io.realm.RealmList;
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
    public String name;
    public String avatar_url;
    public RealmList<Transaction> transactions;

    public User() {

    }

    public User(int age, String uid, String gender, String name, String avatar_url) {
        this.age = age;
        this.uid = uid;
        this.gender = gender;
        this.name = name;
        this.avatar_url = avatar_url;
        this.transactions = new RealmList<>();
    }
}

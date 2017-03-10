package samples.linhtruong.com.schema;

import io.realm.RealmObject;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/10/17 - 11:47.
 * @organization VED
 */

public class Transaction extends RealmObject {

    public double amount;
    public String branch;

    public Transaction() {

    }

    public Transaction(double amount, String branch) {
        this.amount = amount;
        this.branch = branch;
    }
}

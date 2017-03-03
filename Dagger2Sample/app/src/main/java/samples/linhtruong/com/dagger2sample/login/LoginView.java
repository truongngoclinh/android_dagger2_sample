package samples.linhtruong.com.dagger2sample.login;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import samples.linhtruong.com.dagger2sample.R;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:01.
 * @organization VED
 */

@EViewGroup(R.layout.activity_login)
public class LoginView extends LinearLayout {

    @ViewById(R.id.edt_account)
    protected EditText mEdtAccount;

    @ViewById(R.id.edt_password)
    protected EditText mEdtPassword;

    @ViewById(R.id.btn_login)
    protected Button mBtnLogin;

    public LoginView(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }
}

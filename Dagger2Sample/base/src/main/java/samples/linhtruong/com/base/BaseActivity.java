package samples.linhtruong.com.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:31.
 * @organization VED
 */

public abstract class BaseActivity extends Activity {

    protected abstract void onCreateUI(Bundle bundle);

    protected abstract void initDependency();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependency();

        onCreateUI(savedInstanceState);
    }

}

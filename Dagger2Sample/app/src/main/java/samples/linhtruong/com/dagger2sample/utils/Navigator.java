package samples.linhtruong.com.dagger2sample.utils;

import android.content.Intent;

import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.dagger2sample.R;
import samples.linhtruong.com.dagger2sample.home.HomeTabActivity_;
import samples.linhtruong.com.dagger2sample.login.LoginActivity;
import samples.linhtruong.com.dagger2sample.login.LoginActivity_;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/6/17 - 11:42.
 * @organization VED
 */

public class Navigator {

    public static void navigateHomeActivity(BaseActivity activity) {
        HomeTabActivity_.intent(activity).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY).start();
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        activity.finish();
    }

    public static void navigateLoginActivity(BaseActivity activity) {
        LoginActivity_.intent(activity).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY).start();
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        activity.finish();
    }
}

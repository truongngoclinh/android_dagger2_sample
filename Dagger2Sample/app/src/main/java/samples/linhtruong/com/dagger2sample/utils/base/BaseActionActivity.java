package samples.linhtruong.com.dagger2sample.utils.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.dagger2sample.utils.ActionBar;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 11:35.
 * @organization VED
 */

public abstract class BaseActionActivity extends BaseActivity {

    private ActivityBaseActionView mActionView;

    @Override
    protected void onCreateUI(Bundle bundle) {
        mActionView = new ActivityBaseActionView(this);
        super.setContentView(mActionView);
        onCreateContent(bundle);
    }

    public ActionBar actionBar() {
        return mActionView.getActionBar();
    }

    public ImageView background() {
        return mActionView.getBackgroundView();
    }

    protected abstract void onCreateContent(Bundle bundle);

    /**
     * set the content view of the activity
     * @param view the content view
     * */
    protected void setContent(View view) {
        mActionView.setContentView(view);
    }

    /**
     * set the content view of the activity
     * @param view the content view
     * @param isOverlay set this true if content view is overlap with the action bar.
     */
    protected void setContent(View view, boolean isOverlay) {
        mActionView.setContentView(view, isOverlay);
    }

    /**
     * Override this to customize action bar
     *
     * @param builder
     */
    protected void setupActionBar(ActionBar.Builder builder) {
    }

    /**
     * config background view layout params
     * default background view will match_parent for both width and height
     * @param lp
     */
    protected void configBackgroundViewLayoutParams(RelativeLayout.LayoutParams lp) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected boolean isValid() {
        return true;
    }

    private static class ActivityBaseActionView extends BaseActionView {

        public ActivityBaseActionView(Context context) {
            super(context);
        }

        @Override
        protected void customizeActionBar(ActionBar.Builder builder) {
            ((BaseActionActivity) getContext()).setupActionBar(builder);
        }

        @Override
        protected void customizeBackgroundLayout(LayoutParams lp) {
            ((BaseActionActivity) getContext()).configBackgroundViewLayoutParams(lp);
        }
    }
}

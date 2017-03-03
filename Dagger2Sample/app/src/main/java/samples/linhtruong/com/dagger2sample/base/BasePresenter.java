package samples.linhtruong.com.dagger2sample.base;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:12.
 * @organization VED
 */

import android.content.Intent;
import android.view.View;

import samples.linhtruong.com.utils.LogUtils;

public abstract class BasePresenter<V extends View> {

    private boolean onLoaded;
    private V view = null;

    public void takeView(V view) {
        if (view == null) {
            LogUtils.d(new NullPointerException("take null view"));
            return;
        }

        if (this.view != view) {
            if (this.view != null) {
                dropView(this.view);
            }

            this.view = view;
            if (getView() != null && !onLoaded) {
                onLoaded = true;
                onLoad();
            }
        }
    }

    public void dropView(V view) {
        if (view == null) {
            LogUtils.d(new NullPointerException("drop null view"));
            return;
        }

        if (this.view == view) {
            onLoaded = false;
            this.view = null;
            onDestroy();
        }

    }

    protected void onLoad() {

    }

    protected void onResume() {

    }

    protected void onPause() {

    }

    protected void onDestroy() {

    }

    protected void onSaveInstance() {

    }

    protected void onRestoreInstance() {

    }

    protected void onActivityResult(int requestCode, Intent data) {

    }

    protected V getView() {
        return view;
    }

}

package samples.linhtruong.com.base;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:12.
 * @organization VED
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import samples.linhtruong.com.utils.LogUtils;

public abstract class BasePresenter<V extends View> {

    private boolean onLoaded;
    private V view = null;
    private ProgressDialog mDialog;

    protected abstract BaseActivity getActivity();

    public void delayRequest() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
               // just delay to see dialog :D
            }
        }, 1000);
    }

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

    public void onLoad() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onDestroy() {

    }

    public void onSaveInstance(Bundle state) {

    }

    public void onRestoreInstance(Bundle state) {

    }

    public void onActivityResult(int requestCode, Intent data) {

    }

    public V getView() {
        return view;
    }

}

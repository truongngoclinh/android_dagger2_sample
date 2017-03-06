package samples.linhtruong.com.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.lang.ref.WeakReference;

import samples.linhtruong.com.interactor.IScreenView;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 01:31.
 * @organization VED
 */

public abstract class BaseActivity extends Activity {

    private WeakReference<IScreenView> mContentView;
    private boolean mIsActivityVisible;

    protected abstract void onCreateUI(Bundle bundle);

    protected abstract void initDependency();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependency();

        onCreateUI(savedInstanceState);
    }

    public void onBack() {
        finish();
    }

    @Override
    public void setContentView(View view) {
        if (view instanceof IScreenView) {
            IScreenView evt = (IScreenView) view;
            mContentView = new WeakReference<>(evt);
        }
        super.setContentView(view);
    }

    @Override
    protected void onPause() {
        if (mContentView != null && isValid()) {
            IScreenView screenView = mContentView.get();
            if (screenView != null) {
                screenView.onHideView();
            }
        }

        if (presenter() != null) {
            presenter().onPause();
        }

        super.onPause();

        mIsActivityVisible = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mContentView != null && isValid()) {
            IScreenView screenView = mContentView.get();
            if (screenView != null) {
                screenView.onShowView();
            }
        }

        if (presenter() != null) {
            presenter().onResume();
        }

        mIsActivityVisible = true;
    }

    public boolean isActivityVisible() {
        return mIsActivityVisible;
    }

    /**
     * mark the activity state
     *
     * @return true if the activity instance is still functional
     */
    protected abstract boolean isValid();

    /**
     * Override this to initialize the base view presenter
     *
     * @return {@link BasePresenter}
     */
    protected BasePresenter<? extends View> presenter() {
        return null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (presenter() != null) {
            presenter().onSaveInstance(outState);
        }
    }

    @Override
    protected void onDestroy() {
        if (mContentView != null) {
            IScreenView screenView = mContentView.get();
            if (screenView != null) {
                screenView.onDestroy();
            }
            mContentView = null;
        }

        super.onDestroy();
    }
}

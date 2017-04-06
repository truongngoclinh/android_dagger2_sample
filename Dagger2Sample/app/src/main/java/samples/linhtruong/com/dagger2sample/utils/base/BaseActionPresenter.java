package samples.linhtruong.com.dagger2sample.utils.base;

import android.view.View;
import samples.linhtruong.com.base.BasePresenter;
import samples.linhtruong.com.dagger2sample.utils.ProgressDialog;

/**
 * Enable custom progress dialog
 *
 * @author linhtruong
 * @date 4/6/17 - 15:45.
 * @organization VED
 */

public abstract class BaseActionPresenter<V extends View> extends BasePresenter<V> {

    private ProgressDialog mDialog;

    public void show() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(getActivity());
            mDialog.setCancelable(true);
        }

        mDialog.show();
    }

    public void hide() {
        mDialog.hide();
    }
}

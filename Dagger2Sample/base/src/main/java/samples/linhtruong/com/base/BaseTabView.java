package samples.linhtruong.com.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import samples.linhtruong.com.base.BasePresenter;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 02:45.
 * @organization VED
 */

public abstract class BaseTabView extends RelativeLayout {

    protected BaseTabView(Context context) {
        super(context);
    }

    protected abstract void resetView(int currentIndex, Bundle data);

    protected abstract View getCurrentView();

    protected abstract BasePresenter<? extends View> getCurrentPresenter();
}

package samples.linhtruong.com.base;

import android.content.Context;
import android.widget.RelativeLayout;

import samples.linhtruong.com.interactor.IScreenView;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 10:55.
 * @organization VED
 */

public abstract class BaseView extends RelativeLayout implements IScreenView {

    public BaseView(Context context) {
        super(context);
    }
}

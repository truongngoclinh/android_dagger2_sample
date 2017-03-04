package samples.linhtruong.com.dagger2sample.home.tabs;

import android.content.Context;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EViewGroup;

import samples.linhtruong.com.dagger2sample.R;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 02:41.
 * @organization VED
 */

@EViewGroup(R.layout.tab_me_layout)
public class HomeMeView extends LinearLayout {

    public HomeMeView(Context context) {
        super(context);
    }
}

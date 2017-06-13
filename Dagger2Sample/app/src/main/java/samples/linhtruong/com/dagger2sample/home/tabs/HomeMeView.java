package samples.linhtruong.com.dagger2sample.home.tabs;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.devspark.robototextview.widget.RobotoButton;
import com.devspark.robototextview.widget.RobotoTextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

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

    @ViewById(R.id.img_avatar)
    ImageView mImgAvatar;

    @ViewById(R.id.txt_name)
    RobotoTextView mTxtName;

    @ViewById(R.id.btn_logout)
    RobotoButton mBtnLogout;

    @ViewById(R.id.btn_open)
    RobotoButton mBtnOpen;

    public HomeMeView(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }
}

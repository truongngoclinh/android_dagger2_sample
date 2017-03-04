package samples.linhtruong.com.dagger2sample.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewsById;

import java.util.List;

import samples.linhtruong.com.base.BaseTabView;
import samples.linhtruong.com.base.BasePresenter;
import samples.linhtruong.com.base.interactor.IViewStatus;
import samples.linhtruong.com.dagger2sample.R;
import samples.linhtruong.com.dagger2sample.home.tabs.HomeMePresenter;
import samples.linhtruong.com.dagger2sample.home.tabs.HomeMeView;
import samples.linhtruong.com.dagger2sample.home.tabs.HomeMeView_;
import samples.linhtruong.com.dagger2sample.home.tabs.HomeReportPresenter;
import samples.linhtruong.com.dagger2sample.home.tabs.HomeReportView;
import samples.linhtruong.com.dagger2sample.home.tabs.HomeReportView_;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 03:09.
 * @organization VED
 */

@EViewGroup(R.layout.activity_home)
public class HomeTabView extends BaseTabView implements IViewStatus {

    private Context mContext;

    public HomeTabView(Context context) {
        super(context);

        mContext = context;
    }

    private int mCurrenIndex = -1;
    private final int ME_INDEX = 0, REPORT_INDEX = 1;

    private HomeMeView mMeView;
    private HomeReportView mReportView;

    private HomeMePresenter mMePresenter;
    private HomeReportPresenter mReportPresenter;

    @ViewsById({R.id.tab_me, R.id.tab_report})
    protected List<View> mTabs;

    @Click(R.id.tab_me)
    protected void OnClickMeTab() {
        resetView(ME_INDEX, null);
    }

    @Click(R.id.tab_report)
    protected void OnClickReportTab() {
        resetView(REPORT_INDEX, null);
    }

    @Override
    protected void resetView(int currentIndex, Bundle data) {
        if (mCurrenIndex == currentIndex) {
            return;
        }

        for (int i = 0; i < mTabs.size(); i++) {
            mTabs.get(i).setSelected(i == currentIndex);
        }

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ABOVE, R.id.tab);

        BasePresenter from = null, to = null;
        switch (mCurrenIndex) {
            case 0:
                from = mMePresenter;
                break;

            case 1:
                from = mReportPresenter;
                break;
        }

        if (from != null) {
            from.onPause();
            removeView(from.getView());
        }

        switch (currentIndex) {
            case 0:
                if (mMePresenter == null) {
                    mMePresenter = new HomeMePresenter((HomeTabActivity) mContext);
                    mMeView = HomeMeView_.build(mContext);
                }
                to = mMePresenter;
                mMePresenter.takeView(mMeView);
                addView(mMeView, layoutParams);
                break;

            case 1:
                if (mReportPresenter == null) {
                    mReportPresenter = new HomeReportPresenter((HomeTabActivity) mContext);
                    mReportView = HomeReportView_.build(mContext);
                }
                to = mReportPresenter;
                mReportPresenter.takeView(mReportView);
                addView(mReportView, layoutParams);
                break;
        }

        mCurrenIndex = currentIndex;

        if (to != null && from != null) {
            to.onResume();
        }

    }

    @Override
    protected View getCurrentView() {
        switch (mCurrenIndex) {
            case 0:
                return mMeView;

            case 1:
                return mReportView;

            default:
                return null;
        }
    }

    @Override
    public BasePresenter<? extends View> getCurrentPresenter() {
        switch (mCurrenIndex) {
            case 0:
                return mMePresenter;

            case 1:
                return mReportPresenter;

            default:
                return null;
        }
    }

    private void setSelectedTab(int tabIndex, Bundle data) {
        mTabs.get(tabIndex).setSelected(true);
        resetView(tabIndex, data);
    }

    @Override
    public void onShowView() {

    }

    @Override
    public void onHideView() {

    }

    @Override
    public void onDestroy() {

    }
}

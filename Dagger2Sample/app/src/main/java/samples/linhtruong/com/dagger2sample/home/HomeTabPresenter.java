package samples.linhtruong.com.dagger2sample.home;

import samples.linhtruong.com.base.BasePresenter;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 13:13.
 * @organization VED
 */

public class HomeTabPresenter  extends BasePresenter<HomeTabView> {

    HomeTabActivity mActivity;

    public HomeTabPresenter(HomeTabActivity activity) {
       mActivity = activity;
    }

    @Override
    public void onLoad() {
        getView().setSelectedTab(HomeTabView.ME_INDEX, null);
    }
}

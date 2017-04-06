package samples.linhtruong.com.dagger2sample.home;

import samples.linhtruong.com.base.BasePresenter;
import samples.linhtruong.com.dagger2sample.utils.base.BaseActionPresenter;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 13:13.
 * @organization VED
 */

public class HomeTabPresenter extends BaseActionPresenter<HomeTabView> {

    HomeTabActivity mActivity;

    public HomeTabPresenter(HomeTabActivity activity) {
       mActivity = activity;
    }

    @Override
    public void onLoad() {
        getView().setSelectedTab(HomeTabView.ME_INDEX, null);
    }

    @Override
    public HomeTabActivity getActivity() {
        return mActivity;
    }
}

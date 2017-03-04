package samples.linhtruong.com.dagger2sample.home;

import javax.inject.Inject;

import samples.linhtruong.com.base.BasePresenter;
import samples.linhtruong.com.dagger2sample.storage.DbManager;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/4/17 - 03:10.
 * @organization VED
 */

public class HomeTabPresenter extends BasePresenter<HomeTabView> {

    @Inject
    DbManager mDbManager;

    private HomeTabActivity mActivity;

    public HomeTabPresenter(HomeTabActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }
}

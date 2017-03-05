package samples.linhtruong.com.dagger2sample.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.ref.WeakReference;

import samples.linhtruong.com.base.BaseView;
import samples.linhtruong.com.interactor.IScreenView;
import samples.linhtruong.com.utils.ScreenUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 10:57.
 * @organization VED
 */

public abstract class BaseActionView extends BaseView {

    protected Context mContext;
    protected ActionBar mActionBar;

    protected View mContentView; // main content view
    protected ViewGroup mFrontView; // actionbar view
    private ImageView mBackView; // background view

    protected WeakReference<IScreenView> mScreen; // view status interface

    public BaseActionView(Context context) {
        super(context);
        mContext = context;

        RelativeLayout.LayoutParams backViewLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        customizeBackgroundLayout(backViewLP);
        backViewLP.addRule(ALIGN_PARENT_TOP);

        mBackView = new ImageView(context);
        mBackView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(mBackView, backViewLP);
    }

    private ActionBar initActionBar(Context context) {
        ActionBar.Builder builder = new ActionBar.Builder();
        customizeActionBar(builder);
        return builder.build(context);
    }

    public ActionBar getActionBar() {
        return mActionBar;
    }

    public ImageView getBackgroundView() {
        return mBackView;
    }

    protected abstract void customizeActionBar(ActionBar.Builder builder);

    protected abstract void customizeBackgroundLayout(RelativeLayout.LayoutParams lp);

    public void setContentView(View contentView) {
        setContentView(contentView, false);
    }

    public void setContentView(View contentView, boolean isFullScreen) {
        mContentView = contentView;
        if (mContentView instanceof IScreenView) {
            mScreen = new WeakReference<>((IScreenView) mContentView);
        } else {
            mScreen = new WeakReference<>(null);
        }
        if (isFullScreen) {
            //action bar overlay with content view
            mFrontView = new RelativeLayout(mContext);
            addView(mFrontView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mFrontView.addView(mContentView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mActionBar = initActionBar(mContext);
            mFrontView.addView(mActionBar, new LayoutParams(LayoutParams.MATCH_PARENT,
                    ScreenUtils.dp12 * 4));
        } else {
            //content view below action bar
            mFrontView = new LinearLayout(mContext);
            ((LinearLayout) mFrontView).setOrientation(LinearLayout.VERTICAL);
            addView(mFrontView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mActionBar = initActionBar(mContext);
            mFrontView.addView(mActionBar, new LayoutParams(LayoutParams.MATCH_PARENT,
                    ScreenUtils.dp12 * 4));
            mFrontView.addView(mContentView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    public void onShowView() {
        IScreenView screenView = mScreen.get();
        if (screenView != null) {
            screenView.onShowView();
        }
    }

    @Override
    public void onHideView() {
        IScreenView screenView = mScreen.get();
        if (screenView != null) {
            screenView.onHideView();
        }
    }

    @Override
    public void onDestroy() {
        IScreenView screenView = mScreen.get();
        if (screenView != null) {
            screenView.onDestroy();
        }
    }

}

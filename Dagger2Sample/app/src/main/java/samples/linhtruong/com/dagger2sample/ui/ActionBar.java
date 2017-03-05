package samples.linhtruong.com.dagger2sample.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devspark.robototextview.widget.RobotoTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.dagger2sample.R;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/5/17 - 11:01.
 * @organization VED
 */

@EViewGroup(R.layout.action_bar)
public class ActionBar extends LinearLayout implements View.OnClickListener {

    @ViewById(R.id.action_bar_home_btn)
    View mHomeButton;

    @ViewById(R.id.action_bar_home_btn_icon)
    ImageView mHomeButtonIcon;

    @ViewById(R.id.action_bar_title_text)
    RobotoTextView mTitleText;

    @ViewById(R.id.action_container)
    LinearLayout mActionContainer;

    private Builder mConfiguration;
    private LayoutInflater mInflator;
    private Paint mPaint;

    public ActionBar(Context context) {
        super(context);
    }

    public ActionBar(Context context, Builder configuration) {
        super(context);
        mInflator = LayoutInflater.from(context);
        mConfiguration = configuration;
        setWillNotDraw(false);

        if (mConfiguration.isBottomBorderEnabled) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            //use the hairline mode to draw 1 pixel only
            mPaint.setStrokeWidth(0);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(context.getResources().getColor(R.color.divider_common));
        }
    }

    @AfterViews
    public void initView() {
        if (mConfiguration.enableBackButton) {
            mHomeButton.setVisibility(VISIBLE);
            mHomeButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = getContext();
                    if (context instanceof BaseActivity) {
                        ((BaseActivity) context).onBack();
                    }
                }
            });
        } else {
            mHomeButton.setVisibility(INVISIBLE);
            mHomeButtonIcon.setVisibility(INVISIBLE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTitleText.setTextColor(getContext().getColor(mConfiguration.textColorRes));
        } else {
            mTitleText.setTextColor(getContext().getResources().getColor(mConfiguration.textColorRes));
        }

        if (mConfiguration.titleRes != -1) {
            mTitleText.setText(mConfiguration.titleRes);
            mTitleText.setVisibility(View.VISIBLE);
        } else if (!TextUtils.isEmpty(mConfiguration.mTitle)) {
            mTitleText.setText(mConfiguration.mTitle);
            mTitleText.setVisibility(View.VISIBLE);
        }

        if (mConfiguration.backgroundDrawable != -1) {
            //mTitleText.setCompoundDrawablesWithIntrinsicBounds(mConfiguration.backgroundDrawable, 0, 0, 0);
            this.setBackgroundResource(mConfiguration.backgroundDrawable);
        }

        if (mConfiguration.homeDrawable != -1) {
            mHomeButtonIcon.setImageResource(mConfiguration.homeDrawable);
            mHomeButton.setVisibility(VISIBLE);
            mHomeButtonIcon.setVisibility(VISIBLE);
        } else {
            //use black as the default choice
            mHomeButtonIcon.setImageResource(R.drawable.icon_left_arrow_white);
        }

        if (mConfiguration.homeIconBackground != -1) {
            mHomeButtonIcon.setBackgroundResource(mConfiguration.homeIconBackground);
        }

        if (mConfiguration.itemAction != null) {
            addAction(mConfiguration.itemAction);
        }
    }

    public void setTitle(int titleResId) {
        mTitleText.setText(titleResId);
        mTitleText.setVisibility(View.VISIBLE);
    }

    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        mTitleText.setText(title);
        mTitleText.setVisibility(View.VISIBLE);
    }

    public void setTitle(String format, Object... args) {
        setTitle(String.format(format, args));
    }

    public void addAction(IItemAction itemAction) {
        final int index = mActionContainer.getChildCount();
        addAction(itemAction, index);
    }

    public void addAction(IItemAction itemAction, int index) {
        mActionContainer.addView(generateView(itemAction), index);
    }

    public void clearAction() {
        mActionContainer.removeAllViews();
    }

    private View generateView(IItemAction item) {
        View view;
        if (item.getDrawable() != 0) {
            view = mInflator.inflate(R.layout.actionbar_item, mActionContainer, false);
            ImageButton labelView = (ImageButton) view.findViewById(R.id.myactionbar_item_btn);
            labelView.setImageDrawable(ContextCompat.getDrawable(getContext(), item.getDrawable()));
            labelView.setEnabled(item.isEnabled());
        } else {
            //assume a text-based icon
            view = mInflator.inflate(R.layout.actionbar_text_item, mActionContainer, false);
            TextView labelView = (TextView) view.findViewById(R.id.myactionbar_item_text);
            labelView.setText(item.getText());
            labelView.setEnabled(item.isEnabled());
        }
        view.setTag(item);
        view.setOnClickListener(this);
        return view;
    }

    public void showMask(boolean isShow) {
        if (isShow) {
            setBackgroundColor(Color.parseColor("#4c000000"));
            mHomeButtonIcon.setBackgroundResource(R.drawable.bg_mask_circle);
        } else {
            if (mConfiguration.homeIconBackground != -1) {
                mHomeButtonIcon.setBackgroundResource(mConfiguration.homeIconBackground);
            }
            setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mConfiguration.isBottomBorderEnabled) {
            int bottom = this.getBottom();
            canvas.drawLine(0, bottom, this.getRight(), bottom, mPaint);
        }
    }

    @Override
    public void onClick(View v) {
        final Object tag = v.getTag();
        if (tag instanceof IItemAction) {
            final IItemAction action = (IItemAction) tag;
            action.doAction(v);
        }
    }

    public static class Builder {

        public Builder() {
        }

        private int backgroundDrawable = R.color.app_primary_color;
        private int titleRes = -1;
        private int homeDrawable = -1, textColorRes = R.color.white;
        private boolean enableBackButton, isBottomBorderEnabled = false;
        private String mTitle = "";
        private IItemAction itemAction = null;
        private int homeIconBackground = -1;

        public Builder withBackground(int resId) {
            backgroundDrawable = resId;
            return this;
        }

        public Builder withTitle(int resId) {
            titleRes = resId;
            return this;
        }

        public Builder withTitle(String title) {
            mTitle = title;
            return this;
        }

        /**
         * Use the home icon as back button
         * @param enabled indicate whether to enable the button or not
         * @return the builder
         */
        public Builder withBackButton(boolean enabled) {
            enableBackButton = enabled;
            return this;
        }

        public Builder withHomeIcon(int icon) {
            homeDrawable = icon;
            return this;
        }

        public Builder withHomeIconBackground(int background) {
            homeIconBackground = background;
            return this;
        }

        public Builder withAction(IItemAction itemAction) {
            this.itemAction = itemAction;
            return this;
        }

        public Builder withBottomLine(boolean enabled) {
            this.isBottomBorderEnabled = enabled;
            return this;
        }

        public Builder withTextColor(int colorRes) {
            this.textColorRes = colorRes;
            return this;
        }

        public ActionBar build(Context viewContext) {
            return ActionBar_.build(viewContext, this);
        }
    }

    public interface IItemAction {

        int getDrawable();

        int getText();

        void doAction(View view);

        boolean isEnabled();
    }
}

/*package samples.linhtruong.com.dagger2sample.utils;

*//**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 4/6/17 - 15:00.
 * @organization VED
 *//*

public class ProgressSpinner extends ProgressSpinner.CompatLoadingProgressBar {

    private static final long MIN_SHOW_TIME = 500L;
    private static final long MIN_DELAY = 500L;
    private boolean mIsAttachedToWindow = false;
    private boolean mIsShown;
    private long mStartTime = -1L;
    private final Runnable mDelayedHide = new Runnable() {
        public void run() {
            ProgressSpinner.this.setVisibility(8);
            ProgressSpinner.this.mStartTime = -1L;
        }
    };
    private final Runnable mDelayedShow = new Runnable() {
        public void run() {
            ProgressSpinner.this.mStartTime = SystemClock.uptimeMillis();
            ProgressSpinner.this.setVisibility(0);
        }
    };

    public ProgressSpinner(Context context) {
        super(context, (AttributeSet) null, 0);
    }

    public ProgressSpinner(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ProgressSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mIsShown = this.getVisibility() == 0;
        if (VERSION.SDK_INT >= 21) {
            this.setElevation(this.getContext().getResources().getDimension(dimen.compat_progress_bar_elevation));
        }

        if (this.getParent() != null) {
            this.getParent().bringChildToFront(this);
        }

    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttachedToWindow = true;
        if (this.mIsShown && this.getVisibility() != 0) {
            this.postDelayed(this.mDelayedShow, 500L);
        }

    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsAttachedToWindow = false;
        this.removeCallbacks(this.mDelayedHide);
        this.removeCallbacks(this.mDelayedShow);
        if (!this.mIsShown && this.mStartTime != -1L) {
            this.setVisibility(8);
        }

        this.mStartTime = -1L;
    }

    public void hide() {
        if (this.mIsShown) {
            this.mIsShown = false;
            if (this.mIsAttachedToWindow) {
                this.removeCallbacks(this.mDelayedShow);
            }

            long diff = SystemClock.uptimeMillis() - this.mStartTime;
            if (this.mStartTime != -1L && diff < 500L) {
                this.postDelayed(this.mDelayedHide, 500L - diff);
            } else {
                this.setVisibility(8);
                this.mStartTime = -1L;
            }
        }

    }

    public void show() {
        if (!this.mIsShown) {
            this.mIsShown = true;
            if (this.mIsAttachedToWindow) {
                this.removeCallbacks(this.mDelayedHide);
                if (this.mStartTime == -1L) {
                    this.postDelayed(this.mDelayedShow, 500L);
                }
            }
        }

    }
}*/

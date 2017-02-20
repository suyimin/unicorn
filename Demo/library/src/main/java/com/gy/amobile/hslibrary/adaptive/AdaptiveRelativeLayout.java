package com.gy.amobile.hslibrary.adaptive;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.gy.amobile.hslibrary.adaptive.utils.LayoutHelper;

public class AdaptiveRelativeLayout extends RelativeLayout {
    private final LayoutHelper mHelper = new LayoutHelper(this);

    public AdaptiveRelativeLayout(Context context) {
        super(context);
    }

    public AdaptiveRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaptiveRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AdaptiveRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode())
            mHelper.adjustChildren();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    public static class LayoutParams extends RelativeLayout.LayoutParams
            implements LayoutHelper.AutoLayoutParams {
        private AdaptiveLayoutInfo mAdaptiveLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mAdaptiveLayoutInfo = LayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        @Override
        public AdaptiveLayoutInfo getAutoLayoutInfo() {
            return mAdaptiveLayoutInfo;
        }


    }
}

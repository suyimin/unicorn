package com.gy.amobile.hslibrary.adaptive;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gy.amobile.hslibrary.adaptive.utils.LayoutHelper;

public class AdaptiveLinearLayout extends LinearLayout {

    private LayoutHelper mHelper = new LayoutHelper(this);

    public AdaptiveLinearLayout(Context context) {
        super(context);
    }

    public AdaptiveLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AdaptiveLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AdaptiveLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode())
            mHelper.adjustChildren();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AdaptiveLinearLayout.LayoutParams(getContext(), attrs);
    }


    public static class LayoutParams extends LinearLayout.LayoutParams
            implements LayoutHelper.AutoLayoutParams {
        private AdaptiveLayoutInfo mAdaptiveLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mAdaptiveLayoutInfo = LayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        @Override
        public AdaptiveLayoutInfo getAutoLayoutInfo() {
            return mAdaptiveLayoutInfo;
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

    }

}

package com.gy.amobile.hslibrary.adaptive.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.gy.amobile.hslibrary.adaptive.AdaptiveLayoutInfo;
import com.gy.amobile.hslibrary.adaptive.utils.LayoutHelper;

public class AdaptiveRadioGroup extends RadioGroup {
    private LayoutHelper mHelper = new LayoutHelper(this);

    public AdaptiveRadioGroup(Context context) {
        super(context);
    }

    public AdaptiveRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends RadioGroup.LayoutParams
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

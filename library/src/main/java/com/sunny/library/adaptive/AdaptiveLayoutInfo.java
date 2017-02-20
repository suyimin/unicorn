package com.sunny.library.adaptive;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunny.library.adaptive.attr.Attrs;
import com.sunny.library.adaptive.attr.AutoAttr;
import com.sunny.library.adaptive.attr.HeightAttr;
import com.sunny.library.adaptive.attr.MarginBottomAttr;
import com.sunny.library.adaptive.attr.MarginLeftAttr;
import com.sunny.library.adaptive.attr.MaxHeightAttr;
import com.sunny.library.adaptive.attr.MaxWidthAttr;
import com.sunny.library.adaptive.attr.MinHeightAttr;
import com.sunny.library.adaptive.attr.PaddingBottomAttr;
import com.sunny.library.adaptive.attr.PaddingLeftAttr;
import com.sunny.library.adaptive.attr.PaddingTopAttr;
import com.sunny.library.adaptive.attr.TextSizeAttr;
import com.sunny.library.adaptive.attr.WidthAttr;
import com.sunny.library.adaptive.attr.MarginRightAttr;
import com.sunny.library.adaptive.attr.MarginTopAttr;
import com.sunny.library.adaptive.attr.MinWidthAttr;
import com.sunny.library.adaptive.attr.PaddingRightAttr;

import java.util.ArrayList;
import java.util.List;

public class AdaptiveLayoutInfo {
    private List<AutoAttr> autoAttrs = new ArrayList<>();

    public void addAttr(AutoAttr autoAttr) {
        autoAttrs.add(autoAttr);
    }


    public void fillAttrs(View view) {
        for (AutoAttr autoAttr : autoAttrs) {
            autoAttr.apply(view);
        }
    }

    public static AdaptiveLayoutInfo getAttrFromView(View view, int attrs, int base) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) return null;
        AdaptiveLayoutInfo adaptiveLayoutInfo = new AdaptiveLayoutInfo();

        // width & height
        if ((attrs & Attrs.WIDTH) != 0 && params.width > 0) {
            adaptiveLayoutInfo.addAttr(WidthAttr.generate(params.width, base));
        }

        if ((attrs & Attrs.HEIGHT) != 0 && params.height > 0) {
            adaptiveLayoutInfo.addAttr(HeightAttr.generate(params.height, base));
        }

        //margin
        if (params instanceof ViewGroup.MarginLayoutParams) {
            if ((attrs & Attrs.MARGIN) != 0) {
                adaptiveLayoutInfo.addAttr(MarginLeftAttr.generate(((ViewGroup.MarginLayoutParams) params).leftMargin, base));
                adaptiveLayoutInfo.addAttr(MarginTopAttr.generate(((ViewGroup.MarginLayoutParams) params).topMargin, base));
                adaptiveLayoutInfo.addAttr(MarginRightAttr.generate(((ViewGroup.MarginLayoutParams) params).rightMargin, base));
                adaptiveLayoutInfo.addAttr(MarginBottomAttr.generate(((ViewGroup.MarginLayoutParams) params).bottomMargin, base));
            }
            if ((attrs & Attrs.MARGIN_LEFT) != 0) {
                adaptiveLayoutInfo.addAttr(MarginLeftAttr.generate(((ViewGroup.MarginLayoutParams) params).leftMargin, base));
            }
            if ((attrs & Attrs.MARGIN_TOP) != 0) {
                adaptiveLayoutInfo.addAttr(MarginTopAttr.generate(((ViewGroup.MarginLayoutParams) params).topMargin, base));
            }
            if ((attrs & Attrs.MARGIN_RIGHT) != 0) {
                adaptiveLayoutInfo.addAttr(MarginRightAttr.generate(((ViewGroup.MarginLayoutParams) params).rightMargin, base));
            }
            if ((attrs & Attrs.MARGIN_BOTTOM) != 0) {
                adaptiveLayoutInfo.addAttr(MarginBottomAttr.generate(((ViewGroup.MarginLayoutParams) params).bottomMargin, base));
            }
        }

        //padding
        if ((attrs & Attrs.PADDING) != 0) {
            adaptiveLayoutInfo.addAttr(PaddingLeftAttr.generate(view.getPaddingLeft(), base));
            adaptiveLayoutInfo.addAttr(PaddingTopAttr.generate(view.getPaddingTop(), base));
            adaptiveLayoutInfo.addAttr(PaddingRightAttr.generate(view.getPaddingRight(), base));
            adaptiveLayoutInfo.addAttr(PaddingBottomAttr.generate(view.getPaddingBottom(), base));
        }
        if ((attrs & Attrs.PADDING_LEFT) != 0) {
            adaptiveLayoutInfo.addAttr(MarginLeftAttr.generate(view.getPaddingLeft(), base));
        }
        if ((attrs & Attrs.PADDING_TOP) != 0) {
            adaptiveLayoutInfo.addAttr(MarginTopAttr.generate(view.getPaddingTop(), base));
        }
        if ((attrs & Attrs.PADDING_RIGHT) != 0) {
            adaptiveLayoutInfo.addAttr(MarginRightAttr.generate(view.getPaddingRight(), base));
        }
        if ((attrs & Attrs.PADDING_BOTTOM) != 0) {
            adaptiveLayoutInfo.addAttr(MarginBottomAttr.generate(view.getPaddingBottom(), base));
        }

        //minWidth ,maxWidth , minHeight , maxHeight
        if ((attrs & Attrs.MIN_WIDTH) != 0) {
            adaptiveLayoutInfo.addAttr(MinWidthAttr.generate(MinWidthAttr.getMinWidth(view), base));
        }
        if ((attrs & Attrs.MAX_WIDTH) != 0) {
            adaptiveLayoutInfo.addAttr(MaxWidthAttr.generate(MaxWidthAttr.getMaxWidth(view), base));
        }
        if ((attrs & Attrs.MIN_HEIGHT) != 0) {
            adaptiveLayoutInfo.addAttr(MinHeightAttr.generate(MinHeightAttr.getMinHeight(view), base));
        }
        if ((attrs & Attrs.MAX_HEIGHT) != 0) {
            adaptiveLayoutInfo.addAttr(MaxHeightAttr.generate(MaxHeightAttr.getMaxHeight(view), base));
        }

        //textsize

        if (view instanceof TextView) {
            if ((attrs & Attrs.TEXTSIZE) != 0) {
                adaptiveLayoutInfo.addAttr(TextSizeAttr.generate((int) ((TextView) view).getTextSize(), base));
            }
        }
        return adaptiveLayoutInfo;
    }


    @Override
    public String toString() {
        return "AutoLayoutInfo{" +
                "autoAttrs=" + autoAttrs +
                '}';
    }
}
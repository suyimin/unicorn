package com.sunny.library.adaptive.utils;

import android.view.View;
import com.sunny.library.R;
import com.sunny.library.adaptive.AdaptiveLayoutInfo;
import com.sunny.library.adaptive.attr.Attrs;
import com.sunny.library.adaptive.attr.AutoAttr;
import com.sunny.library.adaptive.config.LayoutConifg;

public class AutoUtils {

    /**
     * 会直接将view的LayoutParams上设置的width，height直接进行百分比处理
     *
     * @param view
     */
    public static void auto(View view) {
        autoSize(view);
        autoPadding(view);
        autoMargin(view);
        autoTextSize(view, AutoAttr.BASE_DEFAULT);
    }

    /**
     * @param view
     * @param attrs #Attrs.WIDTH|Attrs.HEIGHT
     * @param base  AutoAttr.BASE_WIDTH|AutoAttr.BASE_HEIGHT|AutoAttr.BASE_DEFAULT
     */
    public static void auto(View view, int attrs, int base) {
        AdaptiveLayoutInfo adaptiveLayoutInfo = AdaptiveLayoutInfo.getAttrFromView(view, attrs, base);
        if (adaptiveLayoutInfo != null)
            adaptiveLayoutInfo.fillAttrs(view);
    }

    public static void autoTextSize(View view) {
        auto(view, Attrs.TEXTSIZE, AutoAttr.BASE_DEFAULT);
    }

    public static void autoTextSize(View view, int base) {
        auto(view, Attrs.TEXTSIZE, base);
    }

    public static void autoMargin(View view) {
        auto(view, Attrs.MARGIN, AutoAttr.BASE_DEFAULT);
    }

    public static void autoMargin(View view, int base) {
        auto(view, Attrs.MARGIN, base);
    }

    public static void autoPadding(View view) {
        auto(view, Attrs.PADDING, AutoAttr.BASE_DEFAULT);
    }

    public static void autoPadding(View view, int base) {
        auto(view, Attrs.PADDING, base);
    }

    public static void autoSize(View view) {
        auto(view, Attrs.WIDTH | Attrs.HEIGHT, AutoAttr.BASE_DEFAULT);
    }

    public static void autoSize(View view, int base) {
        auto(view, Attrs.WIDTH | Attrs.HEIGHT, base);
    }

    public static boolean autoed(View view) {
        Object tag = view.getTag(R.id.id_tag_autolayout_size);
        if (tag != null) return true;
        view.setTag(R.id.id_tag_autolayout_size, "Just Identify");
        return false;
    }

    public static float getPercentWidth1px() {
        int screenWidth = LayoutConifg.getInstance().getScreenWidth();
        int designWidth = LayoutConifg.getInstance().getDesignWidth();
        return 1.0f * screenWidth / designWidth;
    }

    public static float getPercentHeight1px() {
        int screenHeight = LayoutConifg.getInstance().getScreenHeight();
        int designHeight = LayoutConifg.getInstance().getDesignHeight();
        return 1.0f * screenHeight / designHeight;
    }


    public static int getPercentWidthSize(int val) {
        int screenWidth = LayoutConifg.getInstance().getScreenWidth();
        int designWidth = LayoutConifg.getInstance().getDesignWidth();
        return (int) (val * 1.0f / designWidth * screenWidth);
    }


    public static int getPercentWidthSizeBigger(int val) {
        int screenWidth = LayoutConifg.getInstance().getScreenWidth();
        int designWidth = LayoutConifg.getInstance().getDesignWidth();

        int res = val * screenWidth;
        if (res % designWidth == 0) {
            return res / designWidth;
        } else {
            return res / designWidth + 1;
        }

    }

    public static int getPercentHeightSizeBigger(int val) {
        int screenHeight = LayoutConifg.getInstance().getScreenHeight();
        int designHeight = LayoutConifg.getInstance().getDesignHeight();

        int res = val * screenHeight;
        if (res % designHeight == 0) {
            return res / designHeight;
        } else {
            return res / designHeight + 1;
        }
    }

    public static int getPercentHeightSize(int val) {
        int screenHeight = LayoutConifg.getInstance().getScreenHeight();
        int designHeight = LayoutConifg.getInstance().getDesignHeight();

        return (int) (val * 1.0f / designHeight * screenHeight);
    }
}

package com.gy.amobile.hslibrary.adaptive.attr;

import android.os.Build;
import android.view.View;

import java.lang.reflect.Field;


public class MinHeightAttr extends AutoAttr {
    public MinHeightAttr(int pxVal, int baseWidth, int baseHeight) {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal() {
        return Attrs.MIN_HEIGHT;
    }

    @Override
    protected boolean defaultBaseWidth() {
        return false;
    }

    @Override
    protected void execute(View view, int val) {
        try {
            view.setMinimumHeight(val);
        } catch (Exception ignore) {
        }
    }

    public static MinHeightAttr generate(int val, int baseFlag) {
        MinHeightAttr attr = null;
        switch (baseFlag) {
            case BASE_WIDTH:
                attr = new MinHeightAttr(val, Attrs.MIN_HEIGHT, 0);
                break;
            case BASE_HEIGHT:
                attr = new MinHeightAttr(val, 0, Attrs.MIN_HEIGHT);
                break;
            case BASE_DEFAULT:
                attr = new MinHeightAttr(val, 0, 0);
                break;
        }
        return attr;
    }

    public static int getMinHeight(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return view.getMinimumHeight();
        } else {
            try {
                Field minHeight = view.getClass().getField("mMinHeight");
                minHeight.setAccessible(true);
                return (int) minHeight.get(view);
            } catch (Exception e) {
            }
        }

        return 0;
    }

}

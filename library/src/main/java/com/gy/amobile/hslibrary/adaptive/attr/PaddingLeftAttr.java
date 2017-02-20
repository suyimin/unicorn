package com.gy.amobile.hslibrary.adaptive.attr;

import android.view.View;


public class PaddingLeftAttr extends AutoAttr {
    public PaddingLeftAttr(int pxVal, int baseWidth, int baseHeight) {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal() {
        return Attrs.PADDING_LEFT;
    }

    @Override
    protected boolean defaultBaseWidth() {
        return true;
    }

    @Override
    protected void execute(View view, int val) {
        int l = val;
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);

    }

    public static PaddingLeftAttr generate(int val, int baseFlag) {
        PaddingLeftAttr attr = null;
        switch (baseFlag) {
            case BASE_WIDTH:
                attr = new PaddingLeftAttr(val, Attrs.PADDING_LEFT, 0);
                break;
            case BASE_HEIGHT:
                attr = new PaddingLeftAttr(val, 0, Attrs.PADDING_LEFT);
                break;
            case BASE_DEFAULT:
                attr = new PaddingLeftAttr(val, 0, 0);
                break;
        }
        return attr;
    }
}

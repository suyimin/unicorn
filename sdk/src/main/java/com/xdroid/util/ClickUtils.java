package com.xdroid.util;

import android.os.SystemClock;
import android.view.View;

/**
 * 防止View在被快速的重复点击时，重复响应
 * Created by suym on 2016/9/18.
 */
public class ClickUtils {
    private static final long INTERVAL = 2000;
    private static View clickView;
    private static int oldId = -1;
    private static long lastClickTime;

    public static boolean isDoubleClick(View v) {
        clickView = v;
        LogUtil.d("==getId()==" + clickView.getId() + ",==oldId==" + oldId);
        if (clickView.getId() == oldId) {
            long time = SystemClock.elapsedRealtime();
            if (time - lastClickTime < INTERVAL) {
                return true;
            }
        }
        lastClickTime = SystemClock.elapsedRealtime();
        oldId = clickView.getId();
        return false;
    }

}

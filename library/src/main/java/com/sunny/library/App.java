package com.sunny.library;


import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.Locale;

public class App {

    // 是否允许全屏
    private static boolean mAllowFullScreen = false;

    // 是否隐藏ActionBar
    private static boolean mHiddenActionBar = true;

    public static boolean isAllowFullScreen() {
        return mAllowFullScreen;
    }

    public static void setAllowFullScreen(boolean mAllowFullScreen) {
        App.mAllowFullScreen = mAllowFullScreen;
    }

    public static boolean isHiddenActionBar() {
        return mHiddenActionBar;
    }

    public static void setHiddenActionBar(boolean mHiddenActionBar) {
        App.mHiddenActionBar = mHiddenActionBar;
    }

    /**
     *
     * @param context
     * @param language
     */
    public static void switchLaguage(Context context, String language) {
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        if ("en".equals(language)) {
            config.locale = Locale.ENGLISH;
        } else if ("tw".equals(language)) {
            config.locale = Locale.TAIWAN;
        } else {
            config.locale = Locale.SIMPLIFIED_CHINESE;
        }
        resources.updateConfiguration(config, metrics);
    }
}

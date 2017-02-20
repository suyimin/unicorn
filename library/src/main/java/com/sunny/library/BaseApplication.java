package com.sunny.library;

import android.app.Application;

import com.sunny.library.data.ExternalDataUtils;
import com.sunny.library.router.KeyUtil;
import com.sdk.x;


public class BaseApplication extends Application {

    public static String urlKey;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);
        App.setAllowFullScreen(true);
        App.setHiddenActionBar(true);
        App.switchLaguage(this, "cn");
        ExternalDataUtils.init(this);
        urlKey = KeyUtil.getRandomKey();
    }
}

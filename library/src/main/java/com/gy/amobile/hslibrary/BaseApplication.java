package com.gy.amobile.hslibrary;

import android.app.Application;

import com.gy.amobile.hslibrary.data.ExternalDataUtils;
import com.gy.amobile.hslibrary.router.KeyUtil;
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

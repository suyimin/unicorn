package com.sunny.library;

import android.app.Application;

import com.sunny.library.data.ExternalDataUtils;
import com.sunny.library.router.KeyUtil;
import com.xdroid.x;


public class BaseApplication extends Application {

    /**
     * 随机生成加密过的Key，用于Router跳转时校验，防止其它应用打开我们的Activity。
     */
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

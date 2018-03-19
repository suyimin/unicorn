package com.sunny.library.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.sunny.library.adaptive.AdaptiveActivity;
import com.sunny.library.App;
import com.xdroid.activity.AppActivityManager;
import com.xdroid.activity.IActivity;

public abstract class BaseActivity extends AdaptiveActivity implements IActivity {

    public Activity aty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aty = this;
        AppActivityManager.create().addActivity(this);
        if (App.isHiddenActionBar()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (App.isAllowFullScreen()) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    protected void onDestroy() {
        AppActivityManager.create().getActivityStack().remove(this);
        super.onDestroy();
    }

    @Override
    public void showActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    @Override
    public void showActivity(Activity aty, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    @Override
    public void showActivity(Activity aty, Intent it) {
        aty.startActivity(it);
    }

    @Override
    public void skipActivity(Activity aty, Class<?> cls) {
        showActivity(aty, cls);
        aty.finish();
    }

    @Override
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
        showActivity(aty, cls, extras);
        aty.finish();
    }

    @Override
    public void skipActivity(Activity aty, Intent it) {
        showActivity(aty, it);
        aty.finish();
    }

}

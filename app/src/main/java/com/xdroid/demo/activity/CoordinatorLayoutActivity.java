package com.xdroid.demo.activity;


import android.os.Bundle;

import com.xdroid.library.activity.BaseActivity;
import com.xdroid.demo.R;

/**
 * 使用CoordinatorLayout主题必须是Theme.AppCompat的子类
 */
public class CoordinatorLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout);
    }

}

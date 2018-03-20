package com.sunny.demo;

import com.xdroid.library.BaseApplication;
import com.sunny.demo.service.InitializeService;

public class ApplicationHelper extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        InitializeService.startActionInit(this);
    }
}

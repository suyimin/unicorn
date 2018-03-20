package com.xdroid.demo;

import com.xdroid.library.BaseApplication;
import com.xdroid.demo.service.InitializeService;

public class ApplicationHelper extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        InitializeService.startActionInit(this);
    }
}

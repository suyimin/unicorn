package com.gy.amobile.demo;

import com.gy.amobile.hslibrary.BaseApplication;
import com.gy.amobile.demo.service.InitializeService;

public class ApplicationHelper extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        InitializeService.startActionInit(this);
    }
}

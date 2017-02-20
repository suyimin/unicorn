package com.sunny.demo.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.sdk.util.LogUtil;

public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "com.gy.amobile.person.service.action.INIT";

    public InitializeService() {
        super("InitializeService");
    }

    public static void startActionInit(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                LogUtil.e("InitializeService start");
            }
        }
    }

}

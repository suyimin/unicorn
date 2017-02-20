package com.gy.amobile.demo.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.gy.amobile.hslibrary.activity.BaseActivity;
import com.gy.amobile.demo.R;
import com.gy.amobile.demo.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusActivity extends BaseActivity {

    @BindView(R.id.btn_send)
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
        ButterKnife.bind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 0, sticky = true)
    public void MessageEvent(MessageEvent event) {
        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_send)
    public void onClick() {
        EventBus.getDefault().post(new MessageEvent("Hello everyone!"));
    }
}

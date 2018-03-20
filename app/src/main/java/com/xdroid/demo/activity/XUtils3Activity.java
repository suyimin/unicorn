package com.xdroid.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xdroid.library.BaseApplication;
import com.xdroid.library.activity.BaseActivity;
import com.xdroid.library.router.Router;
import com.xdroid.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class XUtils3Activity extends BaseActivity {

    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutils3);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_1, R.id.btn_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                Router.getInstance(this).routerUri().jumpToForgetPwdActivity(BaseApplication.urlKey);
                break;
            case R.id.btn_2:
                showActivity(aty, DBActivity.class);
                break;
        }
    }
}

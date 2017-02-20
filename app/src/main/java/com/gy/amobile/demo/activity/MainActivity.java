package com.gy.amobile.demo.activity;

import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.gy.amobile.hslibrary.activity.BaseActivity;
import com.gy.amobile.demo.R;
import com.sdk.activity.AppActivityManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_3)
    Button btn3;
    @BindView(R.id.btn_4)
    Button btn4;
    @BindView(R.id.btn_5)
    Button btn5;
    @BindView(R.id.btn_6)
    Button btn6;
    @BindView(R.id.btn_7)
    Button btn7;
    @BindView(R.id.btn_8)
    Button btn8;
    @BindView(R.id.btn_9)
    Button btn9;
    @BindView(R.id.btn_10)
    Button btn10;
    @BindView(R.id.btn_11)
    Button btn11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
            R.id.btn_8, R.id.btn_9, R.id.btn_10, R.id.btn_11})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                AppActivityManager.create().AppExit(this);
                break;
            case R.id.btn_2:
                showActivity(aty, SimpleActivity.class);
                break;
            case R.id.btn_3:
                showActivity(aty, ListViewActivity.class);
                break;
            case R.id.btn_4:
                showActivity(aty, CaptureActivity.class);
                break;
            case R.id.btn_5:
                showActivity(aty, XUtils3Activity.class);
                break;
            case R.id.btn_6:
                showActivity(aty, EventBusActivity.class);
                break;
            case R.id.btn_7:
                showActivity(aty, GlideActivity.class);
                break;
            case R.id.btn_8:
                showActivity(aty, CoordinatorLayoutActivity.class);
                break;
            case R.id.btn_9:
                tips();
                break;
            case R.id.btn_10:
                showActivity(aty, PopActivity.class);
                break;
            case R.id.btn_11:
                showActivity(aty, JavaActivity.class);
                break;
        }
    }

    /**
     * 提示框
     */
    private void tips() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.LEFT | Gravity.TOP;
        lp.x = 10;
        lp.y = 10;
        dialog.show();
        window.setContentView(R.layout.dialog_tips);
        TextView tvName = (TextView) window.findViewById(R.id.tv_name);
        String name = "提示信息";
        tvName.setText(name);
        tvName.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
    }

}

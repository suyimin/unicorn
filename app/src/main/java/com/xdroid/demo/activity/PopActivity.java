package com.xdroid.demo.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xdroid.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopActivity extends Activity {

    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.iv_pop_close)
    ImageView ivPopClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        ButterKnife.bind(this);
        setTheWindowDisplay();
    }

    @OnClick({R.id.btn_confirm, R.id.iv_pop_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                resultOK();
                getRootView(PopActivity.this).setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_pop_close:
                finish();
                break;
        }
    }

    private static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    private void setTheWindowDisplay() {
        final DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = 460;
        layoutParams.height = 300;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.horizontalMargin = 0.16f;
        layoutParams.verticalMargin = 0.36f;
        getWindow().setAttributes(layoutParams);
        setFinishOnTouchOutside(false);
    }

    private void resultOK() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.LEFT | Gravity.TOP;
        lp.horizontalMargin = 0.16f;
        lp.verticalMargin = 0.36f;
        window.setAttributes(lp);

        window.setContentView(R.layout.dialog_success);
        TextView tvNum = (TextView) window.findViewById(R.id.tv_num);
        tvNum.setText("yyy");
        ImageView ivClose = (ImageView) window.findViewById(R.id.iv_pop_close);
        Button btnConfirm = (Button) window.findViewById(R.id.btn_confirm);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setClass(PopActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}

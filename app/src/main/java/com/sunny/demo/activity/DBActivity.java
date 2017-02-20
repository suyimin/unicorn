package com.sunny.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.sunny.library.activity.BaseActivity;
import com.sunny.library.data.ExternalDataUtils;
import com.sunny.library.data.SharedPreferencesUtils;
import com.sunny.library.data.entity.AddrInfo;
import com.sunny.demo.R;
import com.sdk.util.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sunny.library.data.ExternalDataUtils.KEY_FIRST;
import static com.sunny.library.data.ExternalDataUtils.VAL_FLAG;


public class DBActivity extends BaseActivity {


    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.btn_sql)
    Button btnSql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        ButterKnife.bind(this);
        setTheWindowDisplay();
    }

    private void setTheWindowDisplay() {
        final DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = 400;
        layoutParams.height = 600;
        layoutParams.gravity = Gravity.CENTER;
        getWindow().setAttributes(layoutParams);
        setFinishOnTouchOutside(false);
    }

    @OnClick({R.id.btn_confirm, R.id.btn_sql})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                if (VAL_FLAG.equals(SharedPreferencesUtils.getStringData(aty, KEY_FIRST))) {
                    test();
                }
                break;
            case R.id.btn_sql:
                Intent intent = new Intent();
                intent.setClass(this, SQLActivity.class);
                startActivity(intent);
                finish();
        }
    }

    private void test() {
        //获取省份列表
        List<AddrInfo> ps = ExternalDataUtils.getHsProviceList();
        LogUtil.e("省份名[0]-->" + ps.get(0).getArea_name());
        //获取省份下面的城市列表
        List<AddrInfo> addrs = ExternalDataUtils.getHsCityList("190000");
        LogUtil.e("地区名[0]-->" + addrs.get(0).getArea_name());
        //通过城市编码获取城市名
        AddrInfo addr = ExternalDataUtils.getHsAddrInfo("440104");
        LogUtil.e("地区名-->" + addr.getArea_name());
        //通过错误码获取错误信息
        String errMsg = ExternalDataUtils.getErrorInfo(this, 2158);
        LogUtil.e("错误信息-->" + errMsg);
    }

}

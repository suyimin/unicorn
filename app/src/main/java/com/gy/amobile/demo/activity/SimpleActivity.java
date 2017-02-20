package com.gy.amobile.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.gy.amobile.demo.R;
import com.gy.amobile.hslibrary.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.checked;


public class SimpleActivity extends BaseActivity {

    @BindView(R.id.simple_activity_button)
    Button simpleActivityButton;
    @BindView(R.id.simple_activity_checkbox)
    CheckBox simpleActivityCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.simple_activity_button, R.id.simple_activity_checkbox})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.simple_activity_button:
                Toast.makeText(this, "Button Click!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.simple_activity_checkbox:
                Toast.makeText(this, "CheckBox Changed! " + checked, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

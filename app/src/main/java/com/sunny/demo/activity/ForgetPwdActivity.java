package com.sunny.demo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdroid.library.HsRegex;
import com.xdroid.library.activity.BaseActivity;
import com.xdroid.library.http.HttpCodeCallBack;
import com.xdroid.library.http.RequestUtils;
import com.xdroid.library.widget.PromptUtils;
import com.sunny.demo.R;
import com.sunny.demo.config.HttpConfig;
import com.xdroid.http.HttpMethod;
import com.xdroid.util.LogUtil;
import com.xdroid.util.RegexUtils;
import com.xdroid.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sunny.demo.R.string.please_input_true_user_email;


/**
 * 忘记密码
 */
public class ForgetPwdActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.btn_next)
    Button btnNext;

    /**
     * 资源号
     */
    private String resource_no;
    /**
     * 用户名
     */
    private String user_name = "0000";
    /**
     * 邮箱
     */
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.bind(this);
        setTheWindowDisplay();
    }

    /**
     * 找回密码-邮件
     */
    public void sendFindPasswordMail() {
        if (TextUtils.isEmpty(resource_no)) {
            PromptUtils.toastPrompt(ForgetPwdActivity.this, null, getResources().getString(R.string.please_input_true_company_res_no));
            return;
        } else if (resource_no.length() != 11) {
            PromptUtils.toastPrompt(ForgetPwdActivity.this, null, getResources().getString(R.string.please_input_true_company_res_no));
            return;
        }

        if (TextUtils.isEmpty(email)) {
            PromptUtils.toastPrompt(ForgetPwdActivity.this, null, getResources().getString(please_input_true_user_email));
            return;
        } else if (!RegexUtils.isEmail(email)) {
            PromptUtils.toastPrompt(ForgetPwdActivity.this, null, getResources().getString(please_input_true_user_email));
            return;
        }

        Map<String, Object> param = new HashMap<>();
        param.put("userType", 3);
        param.put("hsResNo", resource_no);
        if (HsRegex.isHsNo(resource_no, "B")) {
            param.put("custType", 2);
        } else if (HsRegex.isHsNo(resource_no, "T")) {
            param.put("custType", 3);
        } else if (HsRegex.isHsNo(resource_no, "S")) {
            param.put("custType", 4);
        }
        param.put("entUserName", user_name);
        param.put("email", email);
        String url = HttpConfig.getLoginUrl("HSXT_RESET_LOGIN_PWD_EMAIL");
        RequestUtils.requestData(ForgetPwdActivity.this, HttpMethod.POST, url, param, true, new HttpCodeCallBack<String>() {
            @Override
            public void onCancelled(CancelledException cex) {
                super.onCancelled(cex);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }

            @Override
            public void onFinished() {
                super.onFinished();
            }

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                LogUtil.e(result);
                JSONObject jobj = JSON.parseObject(result);
                int retCode = jobj.getInteger("retCode");
                if (retCode == 200) {
                    ToastUtil toastUtil = new ToastUtil();
                    toastUtil.Short(ForgetPwdActivity.this, "自定义message字体、背景色").setToastColor(Color.WHITE, getResources().getColor(R.color.colorAccent)).show();
                } else {
                    PromptUtils.toastPrompt(ForgetPwdActivity.this, null, getResources().getString(please_input_true_user_email));
                }
            }
        });
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

    @OnClick(R.id.btn_next)
    public void onClick() {
        resource_no = etAccount.getText().toString();
        email = etEmail.getText().toString();
        sendFindPasswordMail();
    }
}

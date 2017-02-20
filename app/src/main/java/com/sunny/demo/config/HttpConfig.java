package com.sunny.demo.config;

import java.util.HashMap;
import java.util.Map;

public class HttpConfig {

    /**
     * 登录登出URL
     */
    public final static String HSXT_URL_OPEN = "http://192.168.229.72:8090/made/";

    /**
     * 后台接口列表
     */
    private final static Map<String, String> sMthodMap = new HashMap<String, String>() {
        {
            put("HSXT_RESET_LOGIN_PWD_EMAIL", "v1/findPwd/resetLoginPwdByEmail");   // 重置登录密码-通过邮箱
        }
    };

    public static String getLoginUrl(String mthod) {
        return HSXT_URL_OPEN + sMthodMap.get(mthod);
    }
}

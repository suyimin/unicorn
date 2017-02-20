package com.gy.amobile.hslibrary.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * 本地数据管理
 *
 * create: 2016/11/1 09:31
 * author：linqj
 */

public class SharedPreferencesUtils {

    private static SharedPreferences preferences; // 本地数据存储对象

    /**
     * 获取本地存储数据
     * @param mContext
     * @param key
     * @return
     */
    public static String getStringData(Context mContext, String key){
        if (TextUtils.isEmpty(key)) return null; // 如果需要查找对象为空，返回NULL
        String str = null;
        if (null != key){
            preferences = mContext.getSharedPreferences(key, Activity.MODE_PRIVATE); // 初始化
            String product = preferences.getString(key, "");  // 取得值
            if (TextUtils.isEmpty(product)) return null;  // 如果取不到数据返回NULL

            str = DataSecurityUtils.base64Decode(product); // 数据解码

        }

        return str;  // 返回结果
    }

    /**
     * 获取本地存储数据，以INT串返回
     * @param mContext
     * @param key
     * @return
     */
    public static void saveData(Context mContext, String key, String val){
        if (TextUtils.isEmpty(key) || null == val) return ; // 如果需要查找对象为空，返回

        String oAuth_Base64 = DataSecurityUtils.base64Encode(val); // 将数据转成BASE64
        preferences = mContext.getSharedPreferences(key, Activity.MODE_PRIVATE); // 初始化
        // 保存数据到本地
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, oAuth_Base64);
        editor.commit();
    }
}

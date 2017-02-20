package com.gy.amobile.demo.data;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gy.amobile.demo.bean.PlatformListData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class MapPerm {

    private final static String GET_MAP_PERM = "getMapPerm.txt";

    /**
     * 读取 Assets中的数据
     */
    private static String getAssetsDate(Context mContext, String str) {
        // 存放读出来的数据
        String result = "";
        try {
            // 将assets中的数据以流方式读入
            InputStreamReader inputReader = new InputStreamReader(mContext.getAssets().open(str));
            // 存放在缓存中
            BufferedReader bufReader = new BufferedReader(inputReader);
            // 当前行
            String line = "";
            // 遍历缓存中的数据
            while ((line = bufReader.readLine()) != null) result += line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<PlatformListData> getMapPerm(Context mContext) {
        List<PlatformListData> info = null;
        String result = getAssetsDate(mContext, GET_MAP_PERM);
        if (null != result) {
            JSONObject jo = JSON.parseObject(result);
            info = JSON.parseArray(jo.getString("data"), PlatformListData.class);
        }
        return info;
    }
}

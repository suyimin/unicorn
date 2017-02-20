package com.sunny.library.data;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunny.library.data.entity.AddrInfo;
import com.sunny.library.data.table.ErrorCodeConsultedTable;
import com.sunny.library.data.table.HSPubAreaTable;
import com.sdk.db.table.DbModel;
import com.sdk.util.LogUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 提供外部数据
 */
public class ExternalDataUtils {
    // 第一次初始化标识
    public final static String KEY_FIRST = "first";
    // 标识值
    public final static String VAL_FLAG = "1";
    // 所有城市列表
    private final static String CITY_LIST = "citylist.txt";

    /**
     * 初始化基础数据
     *
     * @param mContext
     */
    public static void init(final Context mContext) {
        String first = SharedPreferencesUtils.getStringData(mContext, KEY_FIRST); // 标识是为第一次启动
        LogUtil.e("==init1================================"+first);
        if (TextUtils.isEmpty(first)) {
            LogUtil.e("==init2================================");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 初始化地区表
                    HSPubAreaTable areaTable = new HSPubAreaTable();
                    DBUtils.insertData(areaTable);
                    // 初始化错误码表
                    ErrorCodeConsultedTable errTable = new ErrorCodeConsultedTable();
                    DBUtils.insertData(errTable);
                    // 将地区信息插入本地数据库，将数据本地化
                    SqlUtils.executeAssetsSQL(mContext, "T_PUB_AREA.sql");
                    // 添加错误码
                    SqlUtils.executeAssetsSQL(mContext, "errcode.sql");
                    // 标识已经初始化
                    SharedPreferencesUtils.saveData(mContext, KEY_FIRST, VAL_FLAG);
                    LogUtil.e("==init3================================");
                }
            }).start();
        }
    }

    /**
     * 读取 Assets中的数据
     */
    private static String getAssetsDate(Context mContext, String str) {
        // 存放读出来的数据
        String result = "";
        try {
            // 将assets中的城市数据以流方式读入
            InputStreamReader inputReader = new InputStreamReader(mContext.getResources().getAssets().open(CITY_LIST));
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

    /**
     * 根据code获取地区信息
     */
    public static AddrInfo getAddrInfo(Context mContext, String code) {
        AddrInfo info = null;
        try {
            String result = getAssetsDate(mContext, CITY_LIST);
            if (null != result) { // 将数据转换成JSON对象
                JSONObject jo = JSON.parseObject(result);
                List<AddrInfo> addrList = JSON.parseArray(jo.getString("data"), AddrInfo.class);
                if (null != addrList) {
                    for (AddrInfo bean : addrList) {
                        if (null != bean && code.equals(bean.getAreaCode())) {
                            info = bean;
                            LogUtil.d("getAddrInfo====================0");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return info;
    }

    /**
     * 取得省份
     */
    public static List<AddrInfo> getProviceList(Context mContext) {
        List<AddrInfo> info = new ArrayList<>();
        String result = getAssetsDate(mContext, CITY_LIST);
        if (null != result) {
            JSONObject jo = JSON.parseObject(result);
            List<AddrInfo> addrList = JSON.parseArray(jo.getString("data"), AddrInfo.class);
            if (null != addrList) {
                for (AddrInfo bean : addrList) {
                    if (null != bean && bean.getAreaType() == 2) {
                        LogUtil.d("getAddrInfo====================1");
                        info.add(bean);
                    }
                }
            }
        }

        return info;
    }

    /**
     * 获取城市列表
     */
    public static List<AddrInfo> getCityList(Context mContext, String code) {
        List<AddrInfo> info = new ArrayList<>();
        String result = getAssetsDate(mContext, CITY_LIST);
        if (null != result) {
            JSONObject jo = JSON.parseObject(result);
            List<AddrInfo> addrList = JSON.parseArray(jo.getString("data"), AddrInfo.class);
            if (null != addrList) {
                for (AddrInfo bean : addrList) {
                    if (null != bean && code.equals(bean.getParentCode())) {
                        info.add(bean);
                        LogUtil.d("getAddrInfo====================2");
                    }
                }
            }
        }
        return info;
    }

    /**
     * 根据城市编码获取城市名
     *
     * @param code
     * @return
     */
    public static AddrInfo getHsAddrInfo(String code) {
        String sql = "select * from HSPubAreaTable where area_no = " + code;
        DbModel dbModel = DBUtils.queryMDFirst(sql);
        AddrInfo info = new AddrInfo();
        HashMap<String, String> map = dbModel.getDataMap();
        info.setArea_name(map.get("area_name"));
        info.setFull_name(map.get("full_name"));
        info.setArea_no(map.get("area_no"));
        info.setHiberarchy(map.get("hiberarchy"));
        info.setParent_id(Integer.parseInt(map.get("parent_id")));
        info.setArea_id(Long.parseLong(map.get("area_id")));
        return info;
    }

    /**
     * 获取省份列表
     */
    public static List<AddrInfo> getHsProviceList() {
        List<AddrInfo> list = getHsCityList("239");
        return list;
    }

    public static List<AddrInfo> getHsCityList(String code) {
        String sql = "select * from HSPubAreaTable where parent_id = " + code;
        List<DbModel> ms = DBUtils.queryMDAll(sql);
        List<AddrInfo> list = new ArrayList<>();
        for (DbModel m : ms) {
            HashMap<String, String> map = m.getDataMap();
            AddrInfo info = new AddrInfo();
            info.setArea_name(map.get("area_name"));
            info.setFull_name(map.get("full_name"));
            info.setArea_no(map.get("area_no"));
            info.setHiberarchy(map.get("hiberarchy"));
            info.setParent_id(Integer.parseInt(map.get("parent_id")));
            info.setArea_id(Long.parseLong(map.get("area_id")));
            list.add(info);
        }
        return list;
    }

    /**
     * 根据错码获取相应信息
     *
     * @param code
     * @return 错误信息
     */
    public static String getErrorInfo(final Context mContext, int code) {
        return SqlUtils.getErrorMsg(mContext, code);
    }
}

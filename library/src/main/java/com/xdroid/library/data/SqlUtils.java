package com.xdroid.library.data;

import android.content.Context;
import android.text.TextUtils;

import com.sunny.library.R;
import com.xdroid.db.table.DbModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * create: 2016/11/1 09:10
 * author：linqj
 * function description:
 *                     数据库操作
 */
public class SqlUtils {
    /**
     * 读取assets文件夹中的数据库文件（.sql），并执行sql语句
     */
    public static int executeAssetsSQL(Context mContext, String schemaName) {
        if (null == mContext || TextUtils.isEmpty(schemaName)) return -1;

        BufferedReader in = null;
        List<String> strList = null;
        try {
            try {
                in = new BufferedReader(new InputStreamReader(mContext.getAssets().open(schemaName)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String line;
            String buffer = "";
            strList = new ArrayList<String>();

            while ((line = in.readLine()) != null) {
                buffer += line;
                if (line.trim().endsWith(";")) {
                    strList.add(buffer);
                    buffer = "";
                }
            }

            boolean bl = DBUtils.exeSqlSave(strList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return 1;
    }

    /**
     * 查询数据库所有安装的信息
     */
    public static List<DbModel> getInstallAPKInfo() {
        String sql = "select * from ModuleTable";
        return DBUtils.queryMDAll(sql);
    }

    /**
     * 查询常用的APK
     *
     * @return
     */
    public static List<DbModel> queryCommonApk() {
        String sql = "select * from ModuleTable where count>0 order by count desc LIMIT 10";
        return DBUtils.queryMDAll(sql);
    }

    /**
     * 根据错误码，查询错误信息
     *
     * @param code
     * @return
     */
    public static String getErrorMsg(Context mContext, int code) {
        String sql = "select * from ErrorCodeConsultedTable where errorCode = " + code;
        DbModel dbModel = DBUtils.queryMDFirst(sql);
        if (null == dbModel) return mContext.getString(R.string.base_unknown_err);
        return dbModel.getString("errorMsg");
    }
}

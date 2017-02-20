package com.sunny.demo.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sunny.demo.bean.CAreaEntity;
import com.sunny.demo.bean.HandlerBean;
import com.sunny.demo.bean.NAreaEntity;
import com.sunny.demo.util.DBHelper;
import com.sdk.util.LogUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SQLActivity extends AppCompatActivity {

    // 数据库名称
    private static final String DB_NAME = "hs_data.db";
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("将JSON数据插入数据库");
        setContentView(textView);
        dbHelper = new DBHelper(this, DB_NAME, null, 1);

        db = dbHelper.getReadableDatabase();
        readAssetsErrorCode(this, "china_area.txt", 1);
        readAssetsErrorCode(this, "nearby_area.txt", 2);
    }


    /**
     * 读取assets中错误码并保存到数据库
     */
    public void readAssetsErrorCode(Context mContext, String schemaName, int flag) {
        if (null == mContext || TextUtils.isEmpty(schemaName)) return;

        BufferedReader in = null;
        HandlerBean bean = null;
        StringBuffer strBuff = new StringBuffer();
        List<ContentValues> cvList = new ArrayList<ContentValues>();
        try {
            in = new BufferedReader(new InputStreamReader(mContext.getAssets().open(schemaName)));
            String line;
            while ((line = in.readLine()) != null) {
                strBuff.append(line);
            }
            if (null != strBuff)
                bean = JSON.parseObject(strBuff.toString(), HandlerBean.class);

            if (1 == flag) {
                List<CAreaEntity> ca = JSON.parseArray(bean.getData(), CAreaEntity.class);
                if (null != ca) {
                    for (CAreaEntity entity : ca) {
                        cvList.add(getData(entity));
                        if (null != entity)
                            for (CAreaEntity entity1 : entity.getChilds()) {
                                cvList.add(getData(entity1));
                            }
                    }

                    insert(cvList, dbHelper.CAREA_TABLE);
                }
            } else if (2 == flag) {
                Map<String, List<NAreaEntity>> map = JSON.parseObject(bean.getData(), new TypeReference<Map<String, List<NAreaEntity>>>() {
                });
                for (String key : map.keySet()) {
                    for (NAreaEntity entity : map.get(key)) {
                        cvList.add(getData1(entity));
                    }
                }

                LogUtil.e("cvList = " + cvList.size());
                insert(cvList, dbHelper.NAREA_TABLE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 插入数据库
     *
     * @param entity
     * @return
     */
    private ContentValues getData(CAreaEntity entity) {
        //生成ContentValues对象 //key:列名，value:想插入的值
        ContentValues cv = new ContentValues();
        //往ContentValues对象存放数据，键-值对模式
        cv.put("areaCode", entity.getAreaCode());
        cv.put("areaName", entity.getAreaName());
        cv.put("parentCode", entity.getParentCode());
        cv.put("sortOrder", entity.getSortOrder());
        return cv;
    }

    /**
     * 插入数据库
     *
     * @param entity
     * @return
     */
    private ContentValues getData1(NAreaEntity entity) {

        //生成ContentValues对象 //key:列名，value:想插入的值
        ContentValues cv = new ContentValues();
        //往ContentValues对象存放数据，键-值对模式
        cv.put("id", entity.getId());
        cv.put("areaCode", entity.getAreaCode());
        cv.put("locationName", entity.getLocationName());
        cv.put("landmark", entity.getLandmark());

        return cv;
    }

    // 插入数据
    private void insert(List<ContentValues> cvList, String tableName) {
        LogUtil.e("插入数据 cvList = " + cvList.size());
        db.beginTransaction();
        try {
            for (ContentValues cv : cvList) {
                db.insertOrThrow(tableName, null, cv);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }
}

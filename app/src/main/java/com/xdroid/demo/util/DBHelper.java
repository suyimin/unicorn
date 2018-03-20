package com.xdroid.demo.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * create: 2016/12/29 10:17
 * author：linqj
 * function description:
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";
    public static final int VERSION = 1;

    public final String CAREA_TABLE = "cArea";
    public final String NAREA_TABLE = "nArea";

    public final String CAREATE_CAREA_TABLE_SQL = "CREATE TABLE " + CAREA_TABLE + "("
            + "areaCode VARCHAR(20),"
            + "areaName varchar(30),"
            + "parentCode varchar(20),"
            + "sortOrder INTEGER"
            + ");";

    public final String CAREATE_NAREA_TABLE_SQL = "CREATE TABLE " + NAREA_TABLE + "("
            + "id VARCHAR(20),"
            + "areaCode varchar(30),"
            + "locationName varchar(20),"
            + "landmark varchar(50)"
            + ");";

    //必须要有构造函数
    public DBHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
    }

    // 当第一次创建数据库的时候，调用该方法
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CAREATE_CAREA_TABLE_SQL);
        Log.e("create","数据库创建成功1");

        db.execSQL(CAREATE_NAREA_TABLE_SQL);
        Log.e("create","数据库创建成功2");
    }

    //当更新数据库的时候执行该方法
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //输出更新数据库的日志信息
        Log.i(TAG, "update Database------------->");
    }
}

package com.sdk.db.converter;

import android.database.Cursor;

import com.sdk.db.sqlite.ColumnDbType;

/**
 * Author: Lucky
 * Date: 13-11-4
 * Time: 下午8:57
 */
public interface ColumnConverter<T> {

    T getFieldValue(final Cursor cursor, int index);

    Object fieldValue2DbValue(T fieldValue);

    ColumnDbType getColumnDbType();
}

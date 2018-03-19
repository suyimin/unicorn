package com.xdroid.db.converter;

import android.database.Cursor;

import com.xdroid.db.sqlite.ColumnDbType;

/**
 * Author: Lucky
 * Date: 13-11-4
 * Time: 下午10:51
 */
public class BooleanColumnConverter implements ColumnConverter<Boolean> {
    @Override
    public Boolean getFieldValue(final Cursor cursor, int index) {
        return cursor.isNull(index) ? null : cursor.getInt(index) == 1;
    }

    @Override
    public Object fieldValue2DbValue(Boolean fieldValue) {
        if (fieldValue == null) return null;
        return fieldValue ? 1 : 0;
    }

    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}

package com.sdk.db.converter;

import android.database.Cursor;

import com.sdk.db.sqlite.ColumnDbType;

/**
 * Author: Lucky
 * Date: 13-11-4
 * Time: 下午10:51
 */
public class ByteArrayColumnConverter implements ColumnConverter<byte[]> {
    @Override
    public byte[] getFieldValue(final Cursor cursor, int index) {
        return cursor.isNull(index) ? null : cursor.getBlob(index);
    }

    @Override
    public Object fieldValue2DbValue(byte[] fieldValue) {
        return fieldValue;
    }

    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.BLOB;
    }
}

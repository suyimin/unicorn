package com.xdroid.library.data;

import android.util.Base64;

/**
 * 数据安全类
 */

public class DataSecurityUtils {

    /**
     * 将数据转换成BASE64编码
     *
     * @param str
     * @return
     */
    public static String base64Encode(String str) {
        String encodeStr = null;
        if (null != str)
            encodeStr = Base64.encodeToString(str.getBytes(), Base64.DEFAULT); // 将字符串转成BASE64编码

        return encodeStr;
    }

    /**
     * BASE64解码
     *
     * @param str
     * @return
     */
    public static String base64Decode(String str) {
        String decodeStr = null;
        if (null != str)
            decodeStr = new String(Base64.decode(str.getBytes(), Base64.DEFAULT)); // 将BASE64解码

        return decodeStr;
    }
}

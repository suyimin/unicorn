package com.gy.amobile.hslibrary.router;

import android.util.Base64;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成随机数字和字母, 并Base64
     */
    public static String getRandomKey() {

        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for (int i = 0; i < 16; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        // 将随机数转换成base64
        return Base64.encodeToString(val.getBytes(), Base64.DEFAULT);
    }
}

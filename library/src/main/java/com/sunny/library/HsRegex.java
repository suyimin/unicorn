package com.sunny.library;


import com.xdroid.util.StringUtils;

public class HsRegex {

    /**
     * 校验互生卡号合法性
     * 服务公司-S\托管企业-T\成员企业-B\消费者-C
     */
    public static boolean isHsNo(String hsNo, String uType) {
        if (StringUtils.isEmpty(hsNo) || !hsNo.matches("[0-9]{11}")) {
            return false;
        }

        if (hsNo.substring(0, 2).equals("00") || hsNo.substring(2, 5).equals("000")) {
            return false;
        } else {
            if ("S".equals(uType)) {
                if (hsNo.substring(5).equals("000000")) {
                    return true;
                }
            } else if ("T".equals(uType)) {
                if (!hsNo.substring(5, 7).equals("00") && hsNo.substring(7).equals("0000")) {
                    return true;
                }
            } else if ("B".equals(uType)) {
                if (hsNo.substring(5, 7).equals("00") && !hsNo.substring(7).equals("0000")) {
                    return true;
                }
            } else if ("C".equals(uType)) {
                if (!hsNo.substring(5, 7).equals("00") && !hsNo.substring(7).equals("0000")) {
                    return true;
                }
            }
        }
        return false;
    }
}

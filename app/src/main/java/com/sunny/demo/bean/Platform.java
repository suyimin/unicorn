package com.sunny.demo.bean;

import java.util.List;


/**
 * {"PLATFORM_CODE":"WEB","PLATFORM_NAME":"网站",
 * "permList":
 * [
 * {"PERM_ID":"201002","PERM_NAME":"我的经营平台"},
 * {"PERM_ID":"201016","PERM_NAME":"系统经营设置"}
 * ]
 * },
 */
public class Platform {
    private String platform_code;//终端类型
    private String platform_name;//终端名称
    private List<PermList> permList;

    public List<PermList> getPermList() {
        return permList;
    }

    public void setPermList(List<PermList> permList) {
        this.permList = permList;
    }

    public String getPlatform_code() {
        return platform_code;
    }

    public void setPlatform_code(String platform_code) {
        this.platform_code = platform_code;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }
}

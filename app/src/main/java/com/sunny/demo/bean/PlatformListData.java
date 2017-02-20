package com.sunny.demo.bean;

import java.util.List;

/**
 * {"platformList":
 * [
 * {"PLATFORM_CODE":"WEB","PLATFORM_NAME":"网站",
 * "permList":
 * [
 * {"PERM_ID":"201002","PERM_NAME":"我的经营平台"},
 * {"PERM_ID":"201016","PERM_NAME":"系统经营设置"}
 * ]
 * },
 * {"PLATFORM_CODE":"POS","PLATFORM_NAME":"POS机",
 * "permList":
 * [
 * {"PERM_ID":"204010","PERM_NAME":"终端管理"}
 * ]
 * }
 * ],"ROLE_NAME":"经理","ROLE_ID":"2010"
 * },
 */
public class PlatformListData {

    private String role_name;//角色名称
    private String role_id;//角色ID
    private List<Platform> platformList;

    public List<Platform> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(List<Platform> platformList) {
        this.platformList = platformList;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}

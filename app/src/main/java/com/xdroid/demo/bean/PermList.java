package com.xdroid.demo.bean;


/**
 * {"PERM_ID":"201002","PERM_NAME":"我的经营平台"}
 */
public class PermList {

    private String perm_id;//{"PERM_ID":"201002",
    private String perm_name;// "PERM_NAME":"我的经营平台"}
    private boolean status;//选中或不选中

    public String getPerm_id() {
        return perm_id;
    }

    public void setPerm_id(String perm_id) {
        this.perm_id = perm_id;
    }

    public String getPerm_name() {
        return perm_name;
    }

    public void setPerm_name(String perm_name) {
        this.perm_name = perm_name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

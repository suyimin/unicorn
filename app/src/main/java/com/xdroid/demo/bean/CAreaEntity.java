package com.xdroid.demo.bean;

import java.util.List;

/**
 * 城市表
 */
public class CAreaEntity {

    private String areaCode;
    private String areaName;
    private String parentCode;
    private int sortOrder;
    private List<CAreaEntity> childs;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<CAreaEntity> getChilds() {
        return childs;
    }

    public void setChilds(List<CAreaEntity> childs) {
        this.childs = childs;
    }
}

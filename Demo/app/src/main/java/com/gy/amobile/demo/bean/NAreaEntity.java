package com.gy.amobile.demo.bean;

/**
 * create: 2016/12/29 09:39
 * author：linqj
 * function description:
 *     城市表
 */

public class NAreaEntity {

    private String id;
    private String areaCode;
    private String locationName;
    private String landmark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}

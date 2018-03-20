package com.xdroid.library.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 提供给子进程获取地区
 */
public class AddrInfo implements Parcelable {
    /**
     * 地区编号
     */
    private String areaCode;
    /**
     * 地区名称
     */
    private String areaName;
    /**
     *地区类型
     */
    private int areaType;
    /**
     * 所属编号
     */
    private String parentCode;
    /**
     *
     */
    private int sortOrder;

    /*** 互生地址 ***/
    private int id;
    private long area_id; /** 模块名称 */
    private int parent_id; /** 模块名称 */
    private String hiberarchy; /** 模块名称 */
    private String area_name; /** 模块名称 */
    private String full_name; /** 模块名称 */
    private String isbn_code; /** 模块名称 */
    private String code; /** 模块名称 */
    private String area_no; /** 模块名称 */
    private String area_code; /** 模块名称 */
    private String phone_prefix; /** 模块名称 */
    private String lang_code; /** 模块名称 */
    private String date_fmt_code; /** 模块名称 */
    private String tree_level; /** 模块名称 */
    private String currency_id; /** 模块名称 */
    private String populations; /** 模块名称 */
    private String is_parent; /** 模块名称 */
    private String international_code; /** 模块名称 */
    private String zone_no; /** 模块名称 */
    private String created; /** 模块名称 */
    private String createdby; /** 模块名称 */
    private String updated; /** 模块名称 */
    private String updatedby; /** 模块名称 */
    private String sort_no; /** 模块名称 */
    private String time_code; /** 模块名称 */
    private String isactive; /** 模块名称 */

    public AddrInfo() {

    }

    /**
     * 读取变量(写入变量的顺序应该一致,不然得不到正确的结果)
     */
    public AddrInfo(Parcel in) {
        areaCode = in.readString();
        areaName = in.readString();
        areaType = in.readInt();
        parentCode = in.readString();
        sortOrder = in.readInt();

        id = in.readInt();
        area_id = in.readLong();
        parent_id = in.readInt();
        hiberarchy = in.readString();
        area_name = in.readString();
        full_name = in.readString();
        isbn_code = in.readString();
        code = in.readString();
        area_no = in.readString();
        area_code = in.readString();
        phone_prefix = in.readString();
        lang_code = in.readString();
        date_fmt_code = in.readString();
        tree_level = in.readString();
        currency_id = in.readString();
        populations = in.readString();
        is_parent = in.readString();
        international_code = in.readString();
        zone_no = in.readString();
        created = in.readString();
        createdby = in.readString();
        updated = in.readString();
        updatedby = in.readString();
        sort_no = in.readString();
        time_code = in.readString();
        isactive = in.readString();
    }

    public static final Creator<AddrInfo> CREATOR = new Creator<AddrInfo>() {
        @Override
        public AddrInfo createFromParcel(Parcel in) {
            return new AddrInfo(in);
        }

        @Override
        public AddrInfo[] newArray(int size) {
            return new AddrInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(areaCode);
        parcel.writeString(areaName);
        parcel.writeInt(areaType);
        parcel.writeString(parentCode);
        parcel.writeInt(sortOrder);

        parcel.writeInt(id);
        parcel.writeLong(area_id);
        parcel.writeInt(parent_id);
        parcel.writeString(hiberarchy);
        parcel.writeString(area_name);
        parcel.writeString(full_name);
        parcel.writeString(isbn_code);
        parcel.writeString(code);
        parcel.writeString(area_no);
        parcel.writeString(area_code);
        parcel.writeString(phone_prefix);
        parcel.writeString(lang_code);
        parcel.writeString(date_fmt_code);
        parcel.writeString(tree_level);
        parcel.writeString(currency_id);
        parcel.writeString(populations);
        parcel.writeString(is_parent);
        parcel.writeString(international_code);
        parcel.writeString(zone_no);
        parcel.writeString(created);
        parcel.writeString(createdby);
        parcel.writeString(updated);
        parcel.writeString(updatedby);
        parcel.writeString(sort_no);
        parcel.writeString(time_code);
        parcel.writeString(isactive);
    }

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

    public int getAreaType() {
        return areaType;
    }

    public void setAreaType(int areaType) {
        this.areaType = areaType;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getArea_id() {
        return area_id;
    }

    public void setArea_id(long area_id) {
        this.area_id = area_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getHiberarchy() {
        return hiberarchy;
    }

    public void setHiberarchy(String hiberarchy) {
        this.hiberarchy = hiberarchy;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getIsbn_code() {
        return isbn_code;
    }

    public void setIsbn_code(String isbn_code) {
        this.isbn_code = isbn_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getArea_no() {
        return area_no;
    }

    public void setArea_no(String area_no) {
        this.area_no = area_no;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getPhone_prefix() {
        return phone_prefix;
    }

    public void setPhone_prefix(String phone_prefix) {
        this.phone_prefix = phone_prefix;
    }

    public String getLang_code() {
        return lang_code;
    }

    public void setLang_code(String lang_code) {
        this.lang_code = lang_code;
    }

    public String getDate_fmt_code() {
        return date_fmt_code;
    }

    public void setDate_fmt_code(String date_fmt_code) {
        this.date_fmt_code = date_fmt_code;
    }

    public String getTree_level() {
        return tree_level;
    }

    public void setTree_level(String tree_level) {
        this.tree_level = tree_level;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getPopulations() {
        return populations;
    }

    public void setPopulations(String populations) {
        this.populations = populations;
    }

    public String getIs_parent() {
        return is_parent;
    }

    public void setIs_parent(String is_parent) {
        this.is_parent = is_parent;
    }

    public String getInternational_code() {
        return international_code;
    }

    public void setInternational_code(String international_code) {
        this.international_code = international_code;
    }

    public String getZone_no() {
        return zone_no;
    }

    public void setZone_no(String zone_no) {
        this.zone_no = zone_no;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getSort_no() {
        return sort_no;
    }

    public void setSort_no(String sort_no) {
        this.sort_no = sort_no;
    }

    public String getTime_code() {
        return time_code;
    }

    public void setTime_code(String time_code) {
        this.time_code = time_code;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }
}

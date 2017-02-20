package com.gy.amobile.hslibrary.data.table;


import com.sdk.db.annotation.Column;
import com.sdk.db.annotation.Table;

/**
 * 互生地区表
 */
@Table(name = "HSPubAreaTable")
public class HSPubAreaTable {
    /* name是表的主键的标识，isId是表的主键标识(true为主键，否则反之)，autoGen主键是否是自增长，如果设置autoGen属性，默认是自增长*/
    @Column(name = "id", isId = true, autoGen = true)
    private int id;

    @Column(name="area_id")
    private long area_id; /** 地区表 */

    @Column(name="parent_id")
    private int parent_id; /** 父ID */

    @Column(name="hiberarchy")
    private String hiberarchy; /** 模块名称 */

    @Column(name="area_name")
    private String area_name; /** 模块名称 */

    @Column(name="full_name")
    private String full_name; /** 模块名称 */

    @Column(name="isbn_code")
    private String isbn_code; /** 模块名称 */

    @Column(name="code")
    private String code; /** 模块名称 */

    @Column(name="area_no")
    private String area_no; /** 模块名称 */

    @Column(name="area_code")
    private String area_code; /** 模块名称 */

    @Column(name="phone_prefix")
    private String phone_prefix; /** 模块名称 */

    @Column(name="lang_code")
    private String lang_code; /** 模块名称 */

    @Column(name="date_fmt_code")
    private String date_fmt_code; /** 模块名称 */

    @Column(name="tree_level")
    private String tree_level; /** 模块名称 */

    @Column(name="currency_id")
    private String currency_id; /** 模块名称 */

    @Column(name="populations")
    private String populations; /** 模块名称 */

    @Column(name="is_parent")
    private String is_parent; /** 模块名称 */

    @Column(name="international_code")
    private String international_code; /** 模块名称 */

    @Column(name="zone_no")
    private String zone_no; /** 模块名称 */

    @Column(name="created")
    private String created; /** 模块名称 */

    @Column(name="createdby")
    private String createdby; /** 模块名称 */

    @Column(name="updated")
    private String updated; /** 模块名称 */

    @Column(name="updatedby")
    private String updatedby; /** 模块名称 */

    @Column(name="sort_no")
    private String sort_no; /** 模块名称 */

    @Column(name="time_code")
    private String time_code; /** 模块名称 */

    @Column(name="isactive")
    private String isactive; /** 模块名称 */

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

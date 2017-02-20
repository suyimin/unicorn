package com.gy.amobile.hslibrary.data.table;

import com.sdk.db.annotation.Column;
import com.sdk.db.annotation.Table;

/**
 * 错误码对照表
 */
@Table(name = "ErrorCodeConsultedTable")
public class ErrorCodeConsultedTable {
    /* name是表的主键的标识，isId是表的主键标识(true为主键，否则反之)，autoGen主键是否是自增长，如果设置autoGen属性，默认是自增长*/
    @Column(name = "id", isId = true, autoGen = true)
    private int id;
    /**
     * 删除标识
     */
    @Column(name = "delFlag")
    private boolean delFlag;
    /**
     * 错误码
     */
    @Column(name = "errorCode")
    private int errorCode;
    /**
     * 错误信息
     */
    @Column(name = "errorMsg")
    private String errorMsg;
    /**
     * 语言
     */
    @Column(name = "languageCode")
    private String languageCode;
    /**
     * 版本号
     */
    @Column(name = "version")
    private Long version;

    /**
     * 本次返回数据中最大的版本号（用于下次查询）
     */
    @Column(name = "maxVersion")
    private Long maxVersion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getMaxVersion() {
        return maxVersion;
    }

    public void setMaxVersion(Long maxVersion) {
        this.maxVersion = maxVersion;
    }
}

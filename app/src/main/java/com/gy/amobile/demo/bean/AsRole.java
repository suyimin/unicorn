package com.gy.amobile.demo.bean;

import java.io.Serializable;

/**
 * Created by ChenWei on 2016/7/5.
 */
public class AsRole implements Serializable {
    /** 角色ID */
    private String roleId;
    /** 角色名称 */
    private String roleName;
    /** 企业客户号 */
    private String entCustId;
    /** 角色描述 */
    private String roleDesc;
    /** 子系统代码 */
    private String subSystemCode;
    /** 平台代码 */
    private String platformCode;
    /** 角色类型 */
    private String roleType;
    /** 是否被选中 */
    private boolean isChecked;

    public AsRole() {
    }

    public AsRole(String roleId, String roleName, String entCustId, String roleDesc, String subSystemCode, String platformCode, String roleType, boolean isChecked) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.entCustId = entCustId;
        this.roleDesc = roleDesc;
        this.subSystemCode = subSystemCode;
        this.platformCode = platformCode;
        this.roleType = roleType;
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEntCustId() {
        return entCustId;
    }

    public void setEntCustId(String entCustId) {
        this.entCustId = entCustId;
    }

    public String getSubSystemCode() {
        return subSystemCode;
    }

    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}

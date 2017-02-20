package com.gy.amobile.hslibrary.router;

/**
 * URI协议接口
 */
public interface IRouterUri {
    @RouterUri(routerUri = "ForgetPwdActivity://host:8888/main")
    String jumpToForgetPwdActivity(@RouterParam("urlKey") String urlKey);
}

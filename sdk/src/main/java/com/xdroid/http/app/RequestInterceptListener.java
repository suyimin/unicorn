package com.xdroid.http.app;


import com.xdroid.http.request.UriRequest;

/**
 * Created by Lucky on 15/11/10.
 * 拦截请求响应(在后台线程工作).
 * <p>
 * 用法: 请求的callback参数同时实现RequestInterceptListener
 */
public interface RequestInterceptListener {

    void beforeRequest(UriRequest request) throws Throwable;

    void afterRequest(UriRequest request) throws Throwable;
}
package com.sunny.library.http;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.xdroid.common.Callback.Cancelable;
import com.xdroid.http.HttpMethod;
import com.xdroid.http.RequestParams;
import com.xdroid.util.LogUtil;
import com.xdroid.x;

import java.util.Map;

/**
 * 网络请求工具类
 */
public class RequestUtils {

    /**
     * @return 返回值：void
     * @method 方法名：requestData
     * @features 功    能：// HTTP谓词枚举   GET("GET"), POST("POST"), PUT("PUT"),DELETE("DELETE")
     * @params 参    数：@param method HttpMethod.GET,HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE
     * @params 参    数：@param url
     * @params 参    数：@param map  可以为null
     * @params 参    数：@param isShowDialog  true 显示dialog
     * @params 参    数：@param httpCallBack
     */
    public static void requestData(Context context, HttpMethod method, String url, Map<String, Object> map, final boolean isShowDialog,
                                   final HttpCodeCallBack<String> httpCallBack) {

        if (isShowDialog) {
            HSHud.showLoadingMessage(context, "", true);
        }
        RequestParams params = new RequestParams(url);
        params.setAsJsonContent(true); // 请求body将参数转换为json形式发送
        params.setHeader("token", "");
        params.setHeader("channelType", "5");

        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue()); // 设置参数
            }
        }

        LogUtil.e("请求参数==" + params.toJSONString());
        LogUtil.e("Headers======" + JSON.toJSON(params.getHeaders()));
        LogUtil.e("Headers2======" + JSON.toJSONString(params.getHeaders()));
        x.http().request(method, params, new HttpCodeCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                if (isShowDialog) {
                    HSHud.dismiss();
                }
                LogUtil.e("result = " + result);
                httpCallBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                if (isShowDialog) {
                    HSHud.dismiss();
                }
                httpCallBack.onError(ex, isOnCallback);
            }

            public void onFinished() {
                httpCallBack.onFinished();
            }

            public void onCancelled(CancelledException cex) {
                httpCallBack.onCancelled(cex);
            }
        });

    }

    /**
     * 上传文件
     *
     * @param url      请求服务器路径
     * @param map      请求参数
     * @param callback 回调
     */
    public static <T> Cancelable upLoadFile(String url, Map<String, Object> map, HttpProgressCallBack<T> callback) {
        RequestParams params = new RequestParams(url);
        params.setMultipart(true); // 标识上传文件
        params.setHeader("token", "");
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }

    /**
     * 下载文件
     *
     * @param url      请求服务器路径
     * @param filepath 文件存储路径
     * @param callback 回调
     */
    public static <T> Cancelable downLoadFile(String url, String filepath, HttpProgressCallBack<T> callback) {
        RequestParams params = new RequestParams(url);
        params.setAutoResume(true); // 设置断点续传
        params.setSaveFilePath(filepath);
        params.setHeader("token", "");
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }

}

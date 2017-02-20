package com.sdk.http.loader;

import android.text.TextUtils;

import com.sdk.cache.DiskCacheEntity;
import com.sdk.common.util.IOUtil;
import com.sdk.http.RequestParams;

import org.json.JSONArray;

import com.sdk.http.request.UriRequest;

import java.io.InputStream;

/**
 * Author: Lucky
 * Time: 2014/06/16
 */
/*package*/ class JSONArrayLoader extends Loader<JSONArray> {

    private String charset = "UTF-8";
    private String resultStr = null;

    @Override
    public Loader<JSONArray> newInstance() {
        return new JSONArrayLoader();
    }

    @Override
    public void setParams(final RequestParams params) {
        if (params != null) {
            String charset = params.getCharset();
            if (!TextUtils.isEmpty(charset)) {
                this.charset = charset;
            }
        }
    }

    @Override
    public JSONArray load(final InputStream in) throws Throwable {
        resultStr = IOUtil.readStr(in, charset);
        return new JSONArray(resultStr);
    }

    @Override
    public JSONArray load(final UriRequest request) throws Throwable {
        request.sendRequest();
        return this.load(request.getInputStream());
    }

    @Override
    public JSONArray loadFromCache(final DiskCacheEntity cacheEntity) throws Throwable {
        if (cacheEntity != null) {
            String text = cacheEntity.getTextContent();
            if (!TextUtils.isEmpty(text)) {
                return new JSONArray(text);
            }
        }

        return null;
    }

    @Override
    public void save2Cache(UriRequest request) {
        saveStringCache(request, resultStr);
    }
}

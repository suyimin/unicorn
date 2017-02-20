package com.sdk.http.loader;

import com.sdk.common.util.IOUtil;
import com.sdk.cache.DiskCacheEntity;
import com.sdk.http.request.UriRequest;

import java.io.InputStream;

/**
 * Author: Lucky
 * Time: 2014/05/30
 */
/*package*/ class ByteArrayLoader extends Loader<byte[]> {

    @Override
    public Loader<byte[]> newInstance() {
        return new ByteArrayLoader();
    }

    @Override
    public byte[] load(final InputStream in) throws Throwable {
        return IOUtil.readBytes(in);
    }

    @Override
    public byte[] load(final UriRequest request) throws Throwable {
        request.sendRequest();
        return this.load(request.getInputStream());
    }

    @Override
    public byte[] loadFromCache(final DiskCacheEntity cacheEntity) throws Throwable {
        return null;
    }

    @Override
    public void save2Cache(final UriRequest request) {
    }
}

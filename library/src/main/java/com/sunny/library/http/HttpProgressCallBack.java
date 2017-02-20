package com.sunny.library.http;

import com.sdk.common.Callback;

public class HttpProgressCallBack<ResultType> implements Callback.ProgressCallback<ResultType> {

    @Override
    public void onSuccess(ResultType result) {
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }

    @Override
    public void onWaiting() {

    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {

    }

}

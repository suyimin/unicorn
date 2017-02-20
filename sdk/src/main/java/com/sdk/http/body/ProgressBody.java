package com.sdk.http.body;


import com.sdk.http.ProgressHandler;

/**
 * Created by Lucky on 15/8/13.
 */
public interface ProgressBody extends RequestBody {
    void setProgressHandler(ProgressHandler progressHandler);
}

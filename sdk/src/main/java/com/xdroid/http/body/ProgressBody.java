package com.xdroid.http.body;


import com.xdroid.http.ProgressHandler;

/**
 * Created by Lucky on 15/8/13.
 */
public interface ProgressBody extends RequestBody {
    void setProgressHandler(ProgressHandler progressHandler);
}

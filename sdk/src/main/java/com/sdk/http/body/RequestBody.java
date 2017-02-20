package com.sdk.http.body;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Lucky on 15/10/29.
 */
public interface RequestBody {

    long getContentLength();

    void setContentType(String contentType);

    String getContentType();

    void writeTo(OutputStream out) throws IOException;
}

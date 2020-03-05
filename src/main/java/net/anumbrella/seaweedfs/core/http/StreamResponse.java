package net.anumbrella.seaweedfs.core.http;


import com.google.common.base.MoreObjects;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class StreamResponse {
    private ByteArrayOutputStream byteArrayOutputStream;
    private int httpResponseStatusCode;
    private long length = 0;

    public StreamResponse(InputStream inputStream, int httpResponseStatusCode) throws IOException {
        this.httpResponseStatusCode = httpResponseStatusCode;
        if (inputStream == null){
            return;
        }

        this.byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > -1) {
            byteArrayOutputStream.write(buffer, 0, length);
            this.length += length;
        }
        byteArrayOutputStream.flush();
    }

    public InputStream getInputStream() {
        if (byteArrayOutputStream == null){
            return null;
        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public int getHttpResponseStatusCode() {
        return httpResponseStatusCode;
    }

    public OutputStream getOutputStream() {
        return byteArrayOutputStream;
    }

    public long getLength() {
        return length;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("byteArrayOutputStream", byteArrayOutputStream)
                .add("httpResponseStatusCode", httpResponseStatusCode)
                .add("length", length)
                .toString();
    }
}

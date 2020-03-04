package net.anumbrella.seaweedfs.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.anumbrella.seaweedfs.core.http.HeaderResponse;
import net.anumbrella.seaweedfs.core.http.JsonResponse;
import net.anumbrella.seaweedfs.core.http.StreamResponse;
import net.anumbrella.seaweedfs.exception.SeaweedfsException;
import net.anumbrella.seaweedfs.exception.SeaweedfsFileNotFoundException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.CharsetUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class FilerWrapper {
    private Connection connection;
    private ObjectMapper objectMapper = new ObjectMapper();

    public FilerWrapper(Connection connection) {
        this.connection = connection;
    }

    public long uploadFile(String url, String fileName, InputStream stream, ContentType contentType) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        httpPost.setHeader(new BasicHeader("Accept-Language", "zh-cn"));

        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset(CharsetUtils.get("UTF-8"));
        builder.addBinaryBody("upload", stream, contentType, fileName);
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        JsonResponse jsonResponse = connection.fetchJsonResultByRequest(httpPost);
        if (jsonResponse == null) {
            jsonResponse = new JsonResponse("{\"name\":\"" + fileName + "\",\"size\":0}", HttpStatus.SC_OK);
        }
        convertResponseStatusToException(jsonResponse.statusCode, url, false, false, false, false);
        return (Integer) objectMapper.readValue(jsonResponse.json, Map.class).get("size");
    }

    public StreamResponse getFileStream(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        StreamResponse cache = connection.fetchStreamCacheByRequest(request);
        convertResponseStatusToException(cache.getHttpResponseStatusCode(), url, false, false, false, false);
        return cache;
    }

    public void deleteFile(String url) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
        JsonResponse jsonResponse = connection.fetchJsonResultByRequest(httpDelete);
        if (jsonResponse != null) {
            convertResponseStatusToException(connection.fetchJsonResultByRequest(httpDelete).statusCode,
                    url, false, false, false, false);
        } else {
            //SeaweedFS用filer删除文件时没有返回值，只有204状态
            convertResponseStatusToException(204, url, false, false, false, false);
        }
    }

    public boolean checkFileExist(String url) throws IOException {
        HttpHead request = new HttpHead(url);
        final int statusCode = connection.fetchStatusCodeByRequest(request);
        try {
            convertResponseStatusToException(statusCode, url, false, true, false, false);
            return true;
        } catch (SeaweedfsFileNotFoundException e) {
            return false;
        }
    }

    /**
     * @param statusCode         Server response code.
     * @param url                Server url.
     * @param ignoreNotFound     Ignore http response not found status.
     * @param ignoreRedirect     Ignore http response redirect status.
     * @param ignoreRequestError Ignore http response request error status.
     * @param ignoreServerError  Ignore http response server error status.
     * @throws SeaweedfsException Http connection is fail or server response within some error message.
     */
    private void convertResponseStatusToException(int statusCode, String url,
                                                  boolean ignoreNotFound,
                                                  boolean ignoreRedirect,
                                                  boolean ignoreRequestError,
                                                  boolean ignoreServerError) throws SeaweedfsException {

        switch (statusCode / 100) {
            case 1:
            case 2:
                return;
            case 3:
                if (ignoreRedirect) {
                    return;
                }
                throw new SeaweedfsException(
                        "fetch file from [" + url + "] is redirect, " +
                                "response stats code is [" + statusCode + "]");
            case 4:
                if (statusCode == 404 && ignoreNotFound) {
                    return;
                } else if (statusCode == 404) {
                    throw new SeaweedfsFileNotFoundException(
                            "fetch file from [" + url + "/" + "] is not found, " +
                                    "response stats code is [" + statusCode + "]");
                }
                if (ignoreRequestError) {
                    return;
                }
                throw new SeaweedfsException(
                        "fetch file from [" + url + "/" + "] is request error, " +
                                "response stats code is [" + statusCode + "]");
            case 5:
                if (ignoreServerError) {
                    return;
                }
                throw new SeaweedfsException(
                        "fetch file from [" + url + "/" + "] is request error, " +
                                "response stats code is [" + statusCode + "]");
            default:
                throw new SeaweedfsException(
                        "fetch file from [" + url + "/" + "] is error, " +
                                "response stats code is [" + statusCode + "]");
        }
    }
}

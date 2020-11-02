package net.anumbrella.seaweedfs.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.anumbrella.seaweedfs.core.http.JsonResponse;
import net.anumbrella.seaweedfs.core.http.StreamResponse;
import net.anumbrella.seaweedfs.exception.SeaweedfsFileNotFoundException;
import net.anumbrella.seaweedfs.util.Utils;
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
        Utils.convertResponseStatusToException(jsonResponse.statusCode, url, false, false, false, false);
        return (Integer) objectMapper.readValue(jsonResponse.json, Map.class).get("size");
    }

    public StreamResponse getFileStream(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        StreamResponse cache = connection.fetchStreamCacheByRequest(request);
        Utils.convertResponseStatusToException(cache.getHttpResponseStatusCode(), url, false, false, false, false);
        return cache;
    }

    public void deleteFile(String url) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
        JsonResponse jsonResponse = connection.fetchJsonResultByRequest(httpDelete);
        if (jsonResponse != null) {
            Utils.convertResponseStatusToException(jsonResponse.statusCode, url, false, false, false, false);
        } else {
            //SeaweedFS用filer删除文件时没有返回值，只有204状态
            Utils.convertResponseStatusToException(204, url, false, false, false, false);
        }
    }

    public boolean checkFileExist(String url) throws IOException {
        HttpHead request = new HttpHead(url);
        final int statusCode = connection.fetchStatusCodeByRequest(request);
        try {
            Utils.convertResponseStatusToException(statusCode, url, false, true, false, false);
            return true;
        } catch (SeaweedfsFileNotFoundException e) {
            return false;
        }
    }
}

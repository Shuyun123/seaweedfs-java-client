package net.anumbrella.seaweedfs.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.anumbrella.seaweedfs.core.http.HeaderResponse;
import net.anumbrella.seaweedfs.core.http.JsonResponse;
import net.anumbrella.seaweedfs.core.http.StreamResponse;
import net.anumbrella.seaweedfs.core.topology.VolumeStatus;
import net.anumbrella.seaweedfs.exception.SeaweedfsFileNotFoundException;
import net.anumbrella.seaweedfs.util.RequestPathStrategy;
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

public class VolumeWrapper {

    private Connection connection;
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Constructor.
     *
     * @param Connection Connection from file source.
     */
    public VolumeWrapper(Connection Connection) {
        this.connection = Connection;
    }


    /**
     * Upload file.
     *
     * @param url         url
     * @param fid         fid
     * @param fileName    fileName
     * @param stream      stream
     * @param ttl         ttl
     * @param contentType contentType
     * @return The size returned is the size stored on SeaweedFS.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public long uploadFile(String url, String fid, String fileName, InputStream stream, String ttl,
                           ContentType contentType)
            throws IOException {
        HttpPost httpPost;
        if (ttl != null) {
            httpPost = new HttpPost(url + "/" + fid + "?ttl=" + ttl);
        } else {
            httpPost = new HttpPost(url + "/" + fid);
        }

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        httpPost.setHeader(new BasicHeader("Accept-Language", "zh-cn"));

        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset(CharsetUtils.get("UTF-8"));
        builder.addBinaryBody("upload", stream, contentType, fileName);
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        JsonResponse jsonResponse = connection.fetchJsonResultByRequest(httpPost);
        //如果jsonResponse为空,只有可能这个文件比较大
        if (jsonResponse == null) {
            jsonResponse = new JsonResponse("{\"name\":\"" + fileName + "\",\"size\":0}", HttpStatus.SC_OK);
        }
        Utils.convertResponseStatusToException(jsonResponse.statusCode, url, fid, false, false, false, false);
        return (Integer) objectMapper.readValue(jsonResponse.json, Map.class).get("size");
    }


    /**
     * Check file is exist.
     *
     * @param url Server url.
     * @param fid File id.
     * @return If file is exist that result is true.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public boolean checkFileExist(String url, String fid) throws IOException {
        HttpHead request = new HttpHead(url + "/" + fid);
        final int statusCode = connection.fetchStatusCodeByRequest(request);
        try {
            Utils.convertResponseStatusToException(statusCode, url, fid, false, true, false, false);
            return true;
        } catch (SeaweedfsFileNotFoundException e) {
            return false;
        }
    }

    /**
     * Delete file.
     *
     * @param url Server url.
     * @param fid File id.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public void deleteFile(String url, String fid) throws IOException {
        HttpDelete request = new HttpDelete(url + "/" + fid);
        Utils.convertResponseStatusToException(connection.fetchJsonResultByRequest(request).statusCode,
                url, fid, false, false, false, false);
    }


    /**
     * Get file stream.
     *
     * @param url Server url.
     * @param fid File id.
     * @return File input stream cache at system memory.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public StreamResponse getFileStream(String url, String fid) throws IOException {
        HttpGet request = new HttpGet(url + "/" + fid);
        StreamResponse cache = connection.fetchStreamCacheByRequest(request);
        Utils.convertResponseStatusToException(cache.getHttpResponseStatusCode(), url, fid, false, false, false, false);
        return cache;
    }


    /**
     * Get file status.
     *
     * @param url Server url.
     * @param fid File id.
     * @return File status header.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public HeaderResponse getFileStatusHeader(String url, String fid) throws IOException {
        HttpHead request = new HttpHead(url + "/" + fid);
        HeaderResponse cache = connection.fetchHeaderByRequest(request);
        Utils.convertResponseStatusToException(cache.getHttpResponseStatusCode(), url, fid, false, false, false, false);
        return cache;
    }

    /**
     * 检查Volume状态接口
     * @param url 一般是IP:port即可
     * @return 返回VolumeStatus对象
     * @throws IOException HTTP请求可能的异常
     */
    public VolumeStatus checkVolumeStatus(String url) throws IOException {
        HttpGet request = new HttpGet(url + RequestPathStrategy.checkVolumeStatus);
        JsonResponse jsonResponse = connection.fetchJsonResultByRequest(request);
        String json = jsonResponse.json;
        return Utils.convertJsonToEntity(json, VolumeStatus.class);
    }


}

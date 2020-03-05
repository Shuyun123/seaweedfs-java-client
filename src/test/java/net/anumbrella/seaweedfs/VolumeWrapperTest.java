package net.anumbrella.seaweedfs;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.anumbrella.seaweedfs.core.Connection;
import net.anumbrella.seaweedfs.core.ConnectionProperties;
import net.anumbrella.seaweedfs.core.FileSource;
import net.anumbrella.seaweedfs.core.VolumeWrapper;
import net.anumbrella.seaweedfs.core.content.AssignFileKeyResult;
import net.anumbrella.seaweedfs.core.http.HeaderResponse;
import net.anumbrella.seaweedfs.core.http.JsonResponse;
import net.anumbrella.seaweedfs.core.http.StreamResponse;
import net.anumbrella.seaweedfs.core.topology.VolumeStatus;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class VolumeWrapperTest {
    public static FileSource fileSource = new FileSource();
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static VolumeWrapper volumeWrapper;
    public static Connection connection;

    @Before
    public void setUp() {
        ConnectionProperties connectionProperties
                = new ConnectionProperties.Builder()
                .host("localhost")
                .port(9333)
                .maxConnection(100)
                .connectionTimeout(60)
                .build();
        fileSource.setProperties(connectionProperties);
        fileSource.startup();
        connection = fileSource.getConnection();
        volumeWrapper = new VolumeWrapper(connection);
    }

    @Test
    public void testUploadFile() throws IOException {
        HttpGet httpGet = new HttpGet("http://localhost:9333/dir/assign");
        JsonResponse jsonResponse = connection.fetchJsonResultByRequest(httpGet);
        AssignFileKeyResult assignFileKeyResult = objectMapper.readValue(jsonResponse.json, AssignFileKeyResult.class);
        String fid = assignFileKeyResult.getFid();
        InputStream inputStream = new FileInputStream(new File("D:\\reba.jpg"));
        long size = volumeWrapper.uploadFile("http://localhost:9222", fid, "reba.jpg", inputStream, null, ContentType.DEFAULT_BINARY);
        Assert.assertNotEquals(0, size);
    }

    @Test
    public void testCheckFileExists() throws IOException {
        Pair<String, String> pair = storeFile();
        boolean isFileExists = volumeWrapper.checkFileExist(pair.getLeft(), pair.getRight());
        Assert.assertTrue(isFileExists);
    }

    @Test
    public void testDeleteFile() throws IOException {
        Pair<String, String> pair = storeFile();
        volumeWrapper.deleteFile(pair.getLeft(), pair.getRight());
    }

    @Test
    public void testGetFileStream() throws IOException {
        Pair<String, String> pair = storeFile();
        StreamResponse streamResponse = volumeWrapper.getFileStream(pair.getLeft(), pair.getRight());
        int responseCode = streamResponse.getHttpResponseStatusCode();
        Assert.assertEquals(responseCode, 200);
    }

    @Test
    public void testGetFileStatusHeader() throws IOException {
        Pair<String, String> pair = storeFile();
        HeaderResponse headerResponse = volumeWrapper.getFileStatusHeader(pair.getLeft(), pair.getRight());
        Assert.assertEquals(200, headerResponse.getHttpResponseStatusCode());
    }

    @Test
    public void testCheckVolumeStatus() throws IOException {
        VolumeStatus volumeStatus = volumeWrapper.checkVolumeStatus("http://localhost:9222");
        Assert.assertNotEquals(0, volumeStatus.getVolumes().size());
    }



    private Pair<String, String> storeFile() throws IOException {
        HttpGet httpGet = new HttpGet("http://localhost:9333/dir/assign");
        JsonResponse jsonResponse = connection.fetchJsonResultByRequest(httpGet);
        AssignFileKeyResult assignFileKeyResult = objectMapper.readValue(jsonResponse.json, AssignFileKeyResult.class);
        String fid = assignFileKeyResult.getFid();
        String url = assignFileKeyResult.getPublicUrl();
        InputStream inputStream = new FileInputStream(new File("D:\\reba.jpg"));
        volumeWrapper.uploadFile("http://localhost:9222", fid, "reba.jpg", inputStream, null, ContentType.DEFAULT_BINARY);
        return Pair.of(url, fid);
    }


}

package net.anumbrella.seaweedfs;

import net.anumbrella.seaweedfs.core.Connection;
import net.anumbrella.seaweedfs.core.ConnectionProperties;
import net.anumbrella.seaweedfs.core.FileSource;
import net.anumbrella.seaweedfs.core.FileTemplate;
import net.anumbrella.seaweedfs.core.FilerWrapper;
import net.anumbrella.seaweedfs.core.file.FileHandleStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileSourceTest {
    private static FileSource fileSource = new FileSource();
    @Before
    public void setUp() {
        ConnectionProperties connectionProperties
                = new ConnectionProperties.Builder().host("localhost").port(9333).maxConnection(100).build();
        fileSource.setProperties(connectionProperties);
        fileSource.startup();
    }

    @Test
    public void testStartup() {
        Connection connection = fileSource.getConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void testUpload() throws IOException {
        Connection connection = fileSource.getConnection();
        FileTemplate fileTemplate = new FileTemplate(connection);
        File file = new File("C:\\Users\\zhiqu\\Downloads\\reba.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileHandleStatus fileHandleStatus = fileTemplate.saveFileByStream("reba.jpg", fileInputStream);
        Assert.assertNotNull(fileHandleStatus.getFileId());
    }

    @Test
    public void testDeleteFile() throws IOException {
        Connection connection = fileSource.getConnection();
        FileTemplate fileTemplate = new FileTemplate(connection);
        File file = new File("C:\\Users\\zhiqu\\Downloads\\reba.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileHandleStatus fileHandleStatus = fileTemplate.saveFileByStream("reba.jpg", fileInputStream);
        fileTemplate.deleteFile(fileHandleStatus.getFileId());
    }

    @Test
    public void testUploadByFiler() throws IOException {
        Connection connection = fileSource.getConnection();
        FileTemplate fileTemplate = new FileTemplate(connection);
        File file = new File("C:\\Users\\zhiqu\\Downloads\\reba.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileHandleStatus fileHandleStatus = fileTemplate.saveFileByFiler("reba.jpg", fileInputStream, "http://localhost:8888/picture/");
        Assert.assertNotEquals(0, fileHandleStatus.getSize());
    }

    @Test
    public void testDeleteFileByFiler() throws IOException {
        Connection connection = fileSource.getConnection();
        FileTemplate fileTemplate = new FileTemplate(connection);
        File file = new File("C:\\Users\\zhiqu\\Downloads\\reba.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        fileTemplate.saveFileByFiler("reba.jpg", fileInputStream, "http://localhost:8888/picture/");
        fileTemplate.deleteFileByFiler("http://localhost:8888/picture/reba.jpg");
    }
}
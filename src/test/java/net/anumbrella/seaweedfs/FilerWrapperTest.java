package net.anumbrella.seaweedfs;

import net.anumbrella.seaweedfs.core.ConnectionProperties;
import net.anumbrella.seaweedfs.core.FileSource;
import net.anumbrella.seaweedfs.core.FilerWrapper;
import net.anumbrella.seaweedfs.core.http.StreamResponse;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class FilerWrapperTest {
    private static FileSource fileSource = new FileSource();

    @Before
    public void setUp() {
        ConnectionProperties connectionProperties
                = new ConnectionProperties.Builder().host("localhost").port(8888).maxConnection(100).build();
        fileSource.setProperties(connectionProperties);
        fileSource.startup();
    }

    @Test
    public void testUploadFile() throws IOException {
        FilerWrapper filerWrapper = new FilerWrapper(fileSource.getConnection());
        File file = new File("C:\\Users\\zhiqu\\Downloads\\reba.jpg");
        InputStream inputStream = new FileInputStream(file);
        long size = filerWrapper.uploadFile("http://localhost:8888/test/", "reba.jpg", inputStream, ContentType.DEFAULT_BINARY);
        Assert.assertNotEquals(0, size);
    }

    @Test
    public void testGetFileStream() throws IOException {
        FilerWrapper filerWrapper = new FilerWrapper(fileSource.getConnection());
        StreamResponse streamResponse = filerWrapper.getFileStream("http://localhost:8888/test/reba.jpg");
        InputStream inputStream = streamResponse.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\reba.jpg");
        int index;
        byte[] bytes = new byte[1024];
        while ((index = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, index);
            fileOutputStream.flush();
        }
        inputStream.close();
        fileOutputStream.close();
        Assert.assertNotNull(streamResponse);
    }

    @Test
    public void testDeleteFile() throws IOException {
        FilerWrapper filerWrapper = new FilerWrapper(fileSource.getConnection());
        filerWrapper.deleteFile("http://localhost:8888/picture/reba.jpg");
    }
}

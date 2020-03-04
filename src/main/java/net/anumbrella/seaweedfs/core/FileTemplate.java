package net.anumbrella.seaweedfs.core;

import net.anumbrella.seaweedfs.core.content.*;
import net.anumbrella.seaweedfs.core.file.FileHandleStatus;
import net.anumbrella.seaweedfs.core.http.HeaderResponse;
import net.anumbrella.seaweedfs.core.http.StreamResponse;
import net.anumbrella.seaweedfs.exception.SeaweedfsException;
import net.anumbrella.seaweedfs.exception.SeaweedfsFileDeleteException;
import net.anumbrella.seaweedfs.exception.SeaweedfsFileNotFoundException;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.TimeZone;

public class FileTemplate implements InitializingBean, DisposableBean {

    private static final SimpleDateFormat headerDateFormat =
            new SimpleDateFormat("EEE',' dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);


    private MasterWrapper masterWrapper;
    private VolumeWrapper volumeWrapper;
    private FilerWrapper filerWrapper;

    private int sameRackCount = 0;
    private int diffRackCount = 0;
    private int diffDataCenterCount = 0;
    /**
     * 复制规则，默认000，即不复制
     */
    private String replicationFlag = "000";
    private String timeToLive = null;
    private String dataCenter = null;
    private String collection = null;
    private boolean usingPublicUrl = true;
//    private boolean loadBalance = true;
    private AssignFileKeyParams assignFileKeyParams = new AssignFileKeyParams();

    /**
     * Constructor.
     *
     * @param connection Connection from file source.
     */
    public FileTemplate(Connection connection) {
        this.masterWrapper = new MasterWrapper(connection);
        this.volumeWrapper = new VolumeWrapper(connection);
        this.filerWrapper = new FilerWrapper(connection);
        headerDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    /**
     * Save a file.
     *
     * @param fileName File name, that can be gzipped based on the file name extension and zip it.
     * @param stream   File stream.
     * @return File status.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public FileHandleStatus saveFileByStream(String fileName, InputStream stream) throws IOException {
        return saveFileByStream(fileName, stream, ContentType.DEFAULT_BINARY);
    }


    /**
     * Save a file.
     *
     * @param fileName    File name, that can be gzipped based on the file name extension and zip it.
     * @param stream      File stream.
     * @param contentType File content type.
     * @return File status.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public FileHandleStatus saveFileByStream(String fileName, InputStream stream, ContentType contentType)
            throws IOException {
        // Assign file key
        final AssignFileKeyResult assignFileKeyResult =
                masterWrapper.assignFileKey(assignFileKeyParams);
        String uploadUrl;
        if (usingPublicUrl) {
            uploadUrl = assignFileKeyResult.getPublicUrl();
        } else {
            uploadUrl = assignFileKeyResult.getUrl();
        }
        // Upload file
        return new FileHandleStatus(
                assignFileKeyResult.getFid(),
                volumeWrapper.uploadFile(
                        uploadUrl,
                        assignFileKeyResult.getFid(),
                        fileName, stream,
                        timeToLive, contentType), uploadUrl);
    }

    /**
     * Save files by stream map.
     *
     * @param streamMap Map of file name and file stream.
     * @return Files status.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public LinkedHashMap<String, FileHandleStatus> saveFilesByStreamMap(LinkedHashMap<String, InputStream> streamMap)
            throws IOException {
        return saveFilesByStreamMap(streamMap, ContentType.DEFAULT_BINARY);
    }

    /**
     * Save files by stream map.
     *
     * @param streamMap   Map of file name and file stream.
     * @param contentType File content type.
     * @return Files status.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public LinkedHashMap<String, FileHandleStatus> saveFilesByStreamMap(LinkedHashMap<String, InputStream> streamMap,
                                                                        ContentType contentType) throws IOException {
        // Assign file key
        final AssignFileKeyParams params = new AssignFileKeyParams(
                assignFileKeyParams.getReplication(),
                streamMap.size(),
                assignFileKeyParams.getDataCenter(),
                assignFileKeyParams.getTtl(),
                assignFileKeyParams.getCollection()
        );

        final AssignFileKeyResult assignFileKeyResult =
                masterWrapper.assignFileKey(params);
        String uploadUrl;
        if (usingPublicUrl) {
            uploadUrl = assignFileKeyResult.getPublicUrl();
        } else {
            uploadUrl = assignFileKeyResult.getUrl();
        }
        // Upload file
        LinkedHashMap<String, FileHandleStatus> resultMap = new LinkedHashMap<>();
        int index = 0;
        for (String fileName : streamMap.keySet()) {
            if (index == 0) {
                resultMap.put(fileName, new FileHandleStatus(assignFileKeyResult.getFid(),
                        volumeWrapper.uploadFile(
                                uploadUrl,
                                assignFileKeyResult.getFid(),
                                fileName,
                                streamMap.get(fileName),
                                timeToLive,
                                contentType)));
            } else {
                resultMap.put(fileName, new FileHandleStatus(assignFileKeyResult.getFid() + "_" + index,
                        volumeWrapper.uploadFile(
                                uploadUrl,
                                assignFileKeyResult.getFid() + "_" + index,
                                fileName,
                                streamMap.get(fileName),
                                timeToLive,
                                contentType)));
            }
            index++;
        }
        return resultMap;
    }

    /**
     * Delete file.
     *
     * @param fileId File id whatever file is not exist.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public void deleteFile(String fileId) throws IOException {
        final String targetUrl = getTargetUrl(fileId);

        if (!volumeWrapper.checkFileExist(targetUrl, fileId))
            throw new SeaweedfsFileNotFoundException("file is not exist");

        volumeWrapper.deleteFile(targetUrl, fileId);
    }

    /**
     * Delete files.
     *
     * @param fileIds File id list whatever file is not exist.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public void deleteFiles(ArrayList<String> fileIds) throws IOException {
        if (fileIds != null)
            for (String fileId : fileIds) {
                deleteFile(fileId);
            }
    }


    /**
     * Update file, if file id is not exist, it wouldn't throw any exception.
     *
     * @param fileId   File id whatever it is not exist.
     * @param fileName File name.
     * @param stream   File stream.
     * @return Files status.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public FileHandleStatus updateFileByStream(String fileId, String fileName,
                                               InputStream stream) throws IOException {
        return updateFileByStream(fileId, fileName, stream, ContentType.DEFAULT_BINARY);
    }


    /**
     * Update file, if file id is not exist, it wouldn't throw any exception.
     *
     * @param fileId      File id whatever it is not exist.
     * @param fileName    File name.
     * @param stream      File stream.
     * @param contentType File content type.
     * @return Files status.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public FileHandleStatus updateFileByStream(String fileId, String fileName, InputStream stream,
                                               ContentType contentType) throws IOException {
        final String targetUrl = getTargetUrl(fileId);

        if (!volumeWrapper.checkFileExist(targetUrl, fileId)) {
            throw new SeaweedfsFileNotFoundException("file is not exist");
        }
        return new FileHandleStatus(fileId,
                volumeWrapper.uploadFile(targetUrl, fileId, fileName, stream, timeToLive, contentType));
    }

    public FileHandleStatus saveFileByFiler(String fileName, InputStream inputStream, String url) throws IOException {
        return new FileHandleStatus(filerWrapper.uploadFile(url, fileName, inputStream, ContentType.DEFAULT_BINARY));
    }

    public void deleteFileByFiler(String url) throws IOException {
        if (!filerWrapper.checkFileExist(url)) {
            throw new SeaweedfsFileNotFoundException("file is not exist");
        }
        filerWrapper.deleteFile(url);
    }

    /**
     * Get file stream, this is the faster method to get file stream from server.
     *
     * @param fileId File id.
     * @return File stream cache in jvm.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public StreamResponse getFileStream(String fileId) throws IOException {
        final String targetUrl = getTargetUrl(fileId);
        return volumeWrapper.getFileStream(targetUrl, fileId);
    }

    /**
     * Get file status without file stream.
     *
     * @param fileId File id.
     * @return File status.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public FileHandleStatus getFileStatus(String fileId) throws IOException {
        final String targetUrl = getTargetUrl(fileId);
        HeaderResponse headerResponse = volumeWrapper.getFileStatusHeader(targetUrl, fileId);
        try {
            return new FileHandleStatus(fileId,
                    headerDateFormat.parse(headerResponse.getLastHeader("Last-Modified").getValue()).getTime(),
                    headerResponse.getLastHeader("Content-Disposition").getValue()
                            .substring(10, headerResponse.getLastHeader("Content-Disposition").getValue().length() - 1),
                    headerResponse.getLastHeader("Content-Type").getValue(),
                    Long.parseLong(headerResponse.getLastHeader("Content-Length").getValue()));
        } catch (ParseException e) {
            throw new SeaweedfsException("Could not parse last modified time [" +
                    headerResponse.getLastHeader("Last-Modified").getValue() + "] to long value");
        }
    }

    /**
     * Get file url, could get file directly from seaweedfs volume server.
     *
     * @param fileId File id.
     * @return File url.
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public String getFileUrl(String fileId) throws IOException {
        final String targetUrl = getTargetUrl(fileId);
        return targetUrl + "/" + fileId;
    }

    public int getSameRackCount() {
        return sameRackCount;
    }

    /**
     * 配置同一机架内机器数量
     * @param sameRackCount 同一机架内机器数量
     */
    public void setSameRackCount(int sameRackCount) {
        if (sameRackCount < 0 || sameRackCount > 9) {
            throw new IllegalArgumentException("seaweedfs replication at same rack count is error");
        }
        this.sameRackCount = sameRackCount;
        buildReplicationFlag();
        buildAssignFileKeyParams();
    }

    public int getDiffRackCount() {
        return diffRackCount;
    }

    /**
     * 配置同机房内机架数量
     * @param diffRackCount 机架数量
     */
    public void setDiffRackCount(int diffRackCount) {
        if (diffRackCount < 0 || diffRackCount > 9) {
            throw new IllegalArgumentException("seaweedfs replication at diff rack count is error");
        }
        this.diffRackCount = diffRackCount;
        buildReplicationFlag();
        buildAssignFileKeyParams();
    }

    public int getDiffDataCenterCount() {
        return diffDataCenterCount;
    }

    /**
     * 配置机房的数量
     * @param diffDataCenterCount 机房数量
     */
    public void setDiffDataCenterCount(int diffDataCenterCount) {
        if (diffDataCenterCount < 0 || diffDataCenterCount > 9) {
            throw new IllegalArgumentException("seaweedfs replication at diff data center count is error");
        }
        this.diffDataCenterCount = diffDataCenterCount;
        buildReplicationFlag();
        buildAssignFileKeyParams();
    }

    public String getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(String timeToLive) {
        if (timeToLive != null && timeToLive.trim().isEmpty()) {
            this.timeToLive = null;
        } else {
            this.timeToLive = timeToLive;
        }
        buildAssignFileKeyParams();
    }

    public String getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(String dataCenter) {
        this.dataCenter = dataCenter;
        buildAssignFileKeyParams();
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
        buildAssignFileKeyParams();
    }

    public boolean isUsingPublicUrl() {
        return usingPublicUrl;
    }

    public void setUsingPublicUrl(boolean usingPublicUrl) {
        this.usingPublicUrl = usingPublicUrl;
    }

//    public boolean isLoadBalance() {
//        return loadBalance;
//    }
//
//    public void setLoadBalance(boolean loadBalance) {
//        this.loadBalance = loadBalance;
//    }

    private void buildReplicationFlag() {
        this.replicationFlag = "" + diffDataCenterCount + diffRackCount + sameRackCount;
    }

    private void buildAssignFileKeyParams() {
        AssignFileKeyParams params = new AssignFileKeyParams();
        params.setCount(1);
        params.setTtl(timeToLive);
        params.setReplication(replicationFlag);
        params.setDataCenter(dataCenter);
        params.setCollection(collection);
        this.assignFileKeyParams = params;
    }


    private String getTargetUrl(String fileId) throws IOException {
        if (usingPublicUrl) {
            return getTargetLocation(fileId).getPublicUrl();
        } else {
            return getTargetLocation(fileId).getUrl();
        }

    }

    private LocationResult getTargetLocation(String fileId) throws IOException {
        final LookupVolumeResult volumeResult = masterWrapper.lookupVolume(new LookupVolumeParams(fileId, collection));
        if (volumeResult.getLocations() == null || volumeResult.getLocations().size() == 0) {
            throw new SeaweedfsFileDeleteException(fileId,
                    new SeaweedfsException("can not found the volume server"));
        }
        return volumeResult.getLocations().iterator().next();
    }


    @Override
    public void destroy() {
        this.masterWrapper = null;
        this.volumeWrapper = null;
    }

    @Override
    public void afterPropertiesSet() {
    }
}

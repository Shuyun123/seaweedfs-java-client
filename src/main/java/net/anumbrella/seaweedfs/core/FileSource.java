package net.anumbrella.seaweedfs.core;

import net.anumbrella.seaweedfs.exception.SeaweedfsException;
import net.anumbrella.seaweedfs.util.ConnectionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.cache.HttpCacheStorage;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;

public class FileSource implements InitializingBean, DisposableBean {

    private static final Log log = LogFactory.getLog(FileSource.class);

    private String host = "localhost";
    private int port = 9333;
    private int connectionTimeout = 3000;
    private int statusExpiry = 60;
    private int maxConnection = 100;
    private int idleConnectionExpiry = 60;
    private int maxConnectionsPreRoute = 1000;
    private boolean enableLookupVolumeCache = true;
    private int lookupVolumeCacheExpiry = 120;
    private int lookupVolumeCacheEntries = 100;
    private boolean enableFileStreamCache = true;
    private int fileStreamCacheEntries = 1000;
    private long fileStreamCacheSize = 8192;
    private HttpCacheStorage fileStreamCacheStorage = null;
    volatile private boolean startup = false;

    //赠面积master为集群的模式
    private boolean masterCluster = false;

    private Connection connection;


    /**
     * Get wrapper connection.
     *
     * @return Wrapper connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Start up the connection to the Seaweedfs server
     *
     * @throws IOException Http connection is fail or server response within some error message.
     */
    public void startup() throws IOException {
        if (this.startup) {
            log.info("connect is already startup");
        } else {

            if (this.connection == null) {
                String masterUrl = null;
                if(masterCluster){
                    //增加master为集群时必须为域地址的控制
                    if(ConnectionUtil.checkHostIPV4Fromat(host)){
                        throw new SeaweedfsException("master is cluster, you must use domain address.");
                    }
                    masterUrl = ConnectionUtil.convertUrlWithScheme(host);
                }else{
                    masterUrl = ConnectionUtil.convertUrlWithScheme(host + ":" + port);
                }
                log.info("start connect to the seaweedfs master server "+masterUrl);
                this.connection = new Connection(
                        //ConnectionUtil.convertUrlWithScheme(host + ":" + port),
                        masterUrl,
                        this.connectionTimeout,
                        this.statusExpiry,
                        this.idleConnectionExpiry,
                        this.maxConnection,
                        this.maxConnectionsPreRoute,
                        this.enableLookupVolumeCache,
                        this.lookupVolumeCacheExpiry,
                        this.lookupVolumeCacheEntries,
                        this.enableFileStreamCache,
                        this.fileStreamCacheEntries,
                        this.fileStreamCacheSize,
                        this.fileStreamCacheStorage);
            }
            this.connection.startup();
            this.startup = true;
        }
    }

    /**
     * Shutdown connect to the any Seaweedfs server
     */
    public void shutdown() {
        log.info("stop connect to the seaweedfs master server");
        if (this.connection != null){
            this.connection.stop();
        }
    }




    /**
     * Working with Spring framework startup
     *
     * @throws IOException Http connection is fail or server response within some error message.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        startup();
    }

    /**
     * Using when the Spring framework is destroy
     *
     * @throws IOException Http connection is fail or server response within some error message.
     */
    @Override
    public void destroy() throws Exception {
        shutdown();
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getStatusExpiry() {
        return statusExpiry;
    }

    public void setStatusExpiry(int statusExpiry) {
        this.statusExpiry = statusExpiry;
    }

    public int getMaxConnection() {
        return maxConnection;
    }

    public void setMaxConnection(int maxConnection) {
        this.maxConnection = maxConnection;
    }

    public int getMaxConnectionsPreRoute() {
        return maxConnectionsPreRoute;
    }

    public void setMaxConnectionsPreRoute(int maxConnectionsPreRoute) {
        this.maxConnectionsPreRoute = maxConnectionsPreRoute;
    }

    public boolean isEnableLookupVolumeCache() {
        return enableLookupVolumeCache;
    }

    public void setEnableLookupVolumeCache(boolean enableLookupVolumeCache) {
        this.enableLookupVolumeCache = enableLookupVolumeCache;
    }

    public int getLookupVolumeCacheExpiry() {
        return lookupVolumeCacheExpiry;
    }

    public void setLookupVolumeCacheExpiry(int lookupVolumeCacheExpiry) {
        this.lookupVolumeCacheExpiry = lookupVolumeCacheExpiry;
    }

    public int getIdleConnectionExpiry() {
        return idleConnectionExpiry;
    }

    public void setIdleConnectionExpiry(int idleConnectionExpiry) {
        this.idleConnectionExpiry = idleConnectionExpiry;
    }

    public int getLookupVolumeCacheEntries() {
        return lookupVolumeCacheEntries;
    }

    public void setLookupVolumeCacheEntries(int lookupVolumeCacheEntries) {
        this.lookupVolumeCacheEntries = lookupVolumeCacheEntries;
    }

    public boolean isEnableFileStreamCache() {
        return enableFileStreamCache;
    }

    public void setEnableFileStreamCache(boolean enableFileStreamCache) {
        this.enableFileStreamCache = enableFileStreamCache;
    }

    public int getFileStreamCacheEntries() {
        return fileStreamCacheEntries;
    }

    public void setFileStreamCacheEntries(int fileStreamCacheEntries) {
        this.fileStreamCacheEntries = fileStreamCacheEntries;
    }

    public long getFileStreamCacheSize() {
        return fileStreamCacheSize;
    }

    public void setFileStreamCacheSize(long fileStreamCacheSize) {
        this.fileStreamCacheSize = fileStreamCacheSize;
    }

    public HttpCacheStorage getFileStreamCacheStorage() {
        return fileStreamCacheStorage;
    }

    public void setFileStreamCacheStorage(HttpCacheStorage fileStreamCacheStorage) {
        this.fileStreamCacheStorage = fileStreamCacheStorage;
    }

    public boolean isMasterCluster() {
        return masterCluster;
    }

    public void setMasterCluster(boolean masterCluster) {
        this.masterCluster = masterCluster;
    }
}

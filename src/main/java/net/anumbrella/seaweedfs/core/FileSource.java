package net.anumbrella.seaweedfs.core;

import net.anumbrella.seaweedfs.util.ConnectionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;

public class FileSource implements InitializingBean, DisposableBean {

    private static final Log log = LogFactory.getLog(FileSource.class);

    private ConnectionProperties connectionProperties;
    volatile private boolean startup = false;

    private Connection connection;

    /**
     * Get wrapper connection.
     *
     * @return Wrapper connection.
     */
    public Connection getConnection() {
        return connection;
    }

    public void setProperties(ConnectionProperties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    /**
     * Start up the connection to the Seaweedfs server
     *
     */
    public void startup() {
        String serverUrl = this.connectionProperties.getHost() + ":" + this.connectionProperties.getPort();
        if (this.startup) {
            log.info("connect is already startup");
        } else {
            log.info("start connect to the seaweedfs master server [" + ConnectionUtil.convertUrlWithScheme(serverUrl)
                    + "]");
            if (this.connection == null) {
                this.connection = new Connection(ConnectionUtil.convertUrlWithScheme(serverUrl),
                        this.connectionProperties.getConnectionTimeout(),
                        this.connectionProperties.getStatusExpiry(),
                        this.connectionProperties.getIdleConnectionExpiry(),
                        this.connectionProperties.getMaxConnection(),
                        this.connectionProperties.getMaxConnectionsPreRoute(),
                        this.connectionProperties.isEnableLookupVolumeCache(),
                        this.connectionProperties.getLookupVolumeCacheExpiry(),
                        this.connectionProperties.getLookupVolumeCacheEntries(),
                        this.connectionProperties.isEnableFileStreamCache(),
                        this.connectionProperties.getFileStreamCacheEntries(),
                        this.connectionProperties.getFileStreamCacheSize(),
                        this.connectionProperties.getFileStreamCacheStorage());
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
        if (this.connection != null) {
            this.connection.stop();
        }
    }

    /**
     * Working with Spring framework startup
     *
     * @throws IOException Http connection is fail or server response within some
     *                     error message.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        startup();
    }

    /**
     * Using when the Spring framework is destroy
     *
     */
    @Override
    public void destroy() {
        shutdown();
    }
}

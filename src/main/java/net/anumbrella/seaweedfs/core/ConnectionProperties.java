package net.anumbrella.seaweedfs.core;


import org.apache.http.client.cache.HttpCacheStorage;

public class ConnectionProperties {
    /**
     * seaweedfs master地址，默认localhost
     */
    private String host;
    /**
     * master端口，默认9333
     */
    private int port;
    /**
     * 默认连接超时时间，单位ms
     */
    private int connectionTimeout;
    /**
     * 检查连接存活状态间隔时间，单位ms，但是实际代码中会被乘1000成为s
     */
    private int statusExpiry;
    /**
     * 连接池的最大连接数
     */
    private int maxConnection;
    /**
     * 连接的最大空闲时间，单位s
     */
    private int idleConnectionExpiry;
    /**
     * 每一个路由的最大连接数
     */
    private int maxConnectionsPreRoute;
    /**
     * 缓存开关
     */
    private boolean enableLookupVolumeCache;

    private int lookupVolumeCacheExpiry;
    private int lookupVolumeCacheEntries;
    private boolean enableFileStreamCache;
    private int fileStreamCacheEntries;
    private long fileStreamCacheSize;
    private HttpCacheStorage fileStreamCacheStorage;

    private ConnectionProperties(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.connectionTimeout = builder.connectionTimeout;
        this.statusExpiry = builder.statusExpiry;
        this.maxConnection = builder.maxConnection;
        this.idleConnectionExpiry = builder.idleConnectionExpiry;
        this.maxConnectionsPreRoute = builder.maxConnectionsPreRoute;
        this.enableLookupVolumeCache = builder.enableLookupVolumeCache;
        this.lookupVolumeCacheExpiry = builder.lookupVolumeCacheExpiry;
        this.lookupVolumeCacheEntries = builder.lookupVolumeCacheEntries;
        this.enableFileStreamCache = builder.enableFileStreamCache;
        this.fileStreamCacheEntries = builder.fileStreamCacheEntries;
        this.fileStreamCacheSize = builder.fileStreamCacheSize;
        this.fileStreamCacheStorage = builder.fileStreamCacheStorage;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getStatusExpiry() {
        return statusExpiry;
    }

    public int getMaxConnection() {
        return maxConnection;
    }

    public int getIdleConnectionExpiry() {
        return idleConnectionExpiry;
    }

    public int getMaxConnectionsPreRoute() {
        return maxConnectionsPreRoute;
    }

    public boolean isEnableLookupVolumeCache() {
        return enableLookupVolumeCache;
    }

    public int getLookupVolumeCacheExpiry() {
        return lookupVolumeCacheExpiry;
    }

    public int getLookupVolumeCacheEntries() {
        return lookupVolumeCacheEntries;
    }

    public boolean isEnableFileStreamCache() {
        return enableFileStreamCache;
    }

    public int getFileStreamCacheEntries() {
        return fileStreamCacheEntries;
    }

    public long getFileStreamCacheSize() {
        return fileStreamCacheSize;
    }

    public HttpCacheStorage getFileStreamCacheStorage() {
        return fileStreamCacheStorage;
    }

    public static class Builder {
        /**
         * seaweedfs master地址，默认localhost
         */
        private String host = "localhost";
        /**
         * master端口，默认9333
         */
        private int port = 9333;
        /**
         * 默认连接超时时间，单位ms
         */
        private int connectionTimeout = 3000;
        /**
         * 检查连接存活状态间隔时间，单位ms，但是实际代码中会被乘1000成为s
         */
        private int statusExpiry = 60;
        /**
         * 连接池的最大连接数
         */
        private int maxConnection;
        /**
         * 连接的最大空闲时间，单位s
         */
        private int idleConnectionExpiry = 60;
        /**
         * 每一个路由的最大连接数
         */
        private int maxConnectionsPreRoute = 1000;
        /**
         * 缓存开关
         */
        private boolean enableLookupVolumeCache = true;

        private int lookupVolumeCacheExpiry = 120;
        private int lookupVolumeCacheEntries = 100;
        private boolean enableFileStreamCache = true;
        private int fileStreamCacheEntries = 1000;
        private long fileStreamCacheSize = 8192;
        private HttpCacheStorage fileStreamCacheStorage = null;

        public Builder() {
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder connectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public Builder statusExpiry(int statusExpiry) {
            this.statusExpiry = statusExpiry;
            return this;
        }

        public Builder maxConnection(int maxConnection) {
            this.maxConnection = maxConnection;
            return this;
        }

        public Builder idleConnectionExpiry(int idleConnectionExpiry) {
            this.idleConnectionExpiry = idleConnectionExpiry;
            return this;
        }

        public Builder maxConnectionsPreRoute(int maxConnectionsPreRoute) {
            this.maxConnectionsPreRoute = maxConnectionsPreRoute;
            return this;
        }

        public Builder enableLookupVolumeCache(boolean enableLookupVolumeCache) {
            this.enableLookupVolumeCache = enableLookupVolumeCache;
            return this;
        }

        public Builder lookupVolumeCacheExpiry(int lookupVolumeCacheExpiry) {
            this.lookupVolumeCacheExpiry = lookupVolumeCacheExpiry;
            return this;
        }

        public Builder lookupVolumeCacheEntries(int lookupVolumeCacheEntries) {
            this.lookupVolumeCacheEntries = lookupVolumeCacheEntries;
            return this;
        }

        public Builder enableFileStreamCache(boolean enableFileStreamCache) {
            this.enableFileStreamCache = enableFileStreamCache;
            return this;
        }

        public Builder fileStreamCacheEntries(int fileStreamCacheEntries) {
            this.fileStreamCacheEntries = fileStreamCacheEntries;
            return this;
        }

        public Builder fileStreamCacheSize(long fileStreamCacheSize) {
            this.fileStreamCacheSize = fileStreamCacheSize;
            return this;
        }

        public Builder fileStreamCacheStorage(HttpCacheStorage fileStreamCacheStorage) {
            this.fileStreamCacheStorage = fileStreamCacheStorage;
            return this;
        }

        public ConnectionProperties build() {
            return new ConnectionProperties(this);
        }
    }
}

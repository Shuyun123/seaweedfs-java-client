# SeaweedFS Client For Java


# latest readme

this project fork from [SeaweedFS Client For Java](https://github.com/Shuyun123/seaweedfs-java-client.git).

maven version does not update,if you want to use my code, please clone and compile it by yourself.


项目frok自[SeaweedFS Client For Java](https://github.com/Shuyun123/seaweedfs-java-client.git).

修复了以下bug:

1.判定leader之前先判断IsLeader，避免当weedfs运行在docker中时，自动更新为docker container虚拟地址的情况

2.在返回值中加入了文件的具体链接。

3.使用HttpClientBuilder.create().build()，防止超时。

maven 版本暂未更新，如果你想使用我的版本，请自行下载并编译。


## Original Content
[![Maven Central](http://img.shields.io/badge/maven_central-0.0.1.RELEASE-brightgreen.svg)](https://search.maven.org/#artifactdetails%7Corg.lokra.seaweedfs%7Cseaweedfs-client%7C0.7.3.RELEASE%7Cjar)
[![GitHub Release](http://img.shields.io/badge/Release-0.0.1.RELEASE-brightgreen.svg)](https://github.com/lokra-platform/seaweedfs-client/releases/tag/0.7.3.RELEASE)
[![Apache license](https://img.shields.io/badge/license-Apache-blue.svg)](http://opensource.org/licenses/Apache)


项目更改自[weed-client](https://github.com/lokra/weed-client)，修复了一下作者原来的部分bug，然后重新打包了。


合并更改部分bug，统一代码样式


# Quick Start


##### Maven

```xml
<dependency>
    <groupId>net.anumbrella.seaweedfs</groupId>
    <artifactId>seaweedfs-java-client</artifactId>
    <version>0.0.1.RELEASE</version>
</dependency>
```

##### Gradle
```
repositories {
    mavenCentral()
}

dependencies {
    compile 'net.anumbrella.seaweedfs:seaweedfs-java-client:0.0.1.RELEASE'
}
```

##### Create a connection manager
```java
FileSource fileSource = new FileSource();
// SeaweedFS master server host
fileSource.setHost("localhost");
// SeaweedFS master server port
fileSource.setPort(9333);
// Set Connection Timeout
fileSource.setConnectionTimeout(5000);
// Startup manager and listens for the change
fileSource.startup();
```

##### Create a file operation template
```java
// Template used with connection manager
FileTemplate template = new FileTemplate(fileSource.getConnection());

//you can decide does it use publicUrl
//if your client server cannot connect to weed server url,example cloud docker weed
//when you start up your weed volume,you must config publicUrl variable
//not recommended for use publicUrl variable, exists security risk
//当你的客户端不能连接weed服务端，例如服务端部署在云端docker,启动的时候必须配置publicUrl参数
//不建议使用publicUrl，存在安全风险 
template.setUsingPublicUrl(false);

template.saveFileByStream("filename.doc", someFile);
```

##### Create a connection manager with master cluster
How to create a mater cluster and use nginx as load balance

You can view this [SeadWeedFS Master cluster with load balance](http://note.youdao.com/noteshare?id=60c9f9478a08511c0fa8e1faf1f5bb08)

如何创建一个Master集群且使用nginx作为负载均衡

[SeadWeedFS Master集群负载均衡](http://note.youdao.com/noteshare?id=60c9f9478a08511c0fa8e1faf1f5bb08)
```java
FileSource fileSource = new FileSource();
// SeaweedFS master server host
// SeaweedFS master must be a domain address
fileSource.setHost("weedfsmaster.xxx.com");
// SeaweedFS with master cluster
fileSource.setMasterCluster(true);
// Startup manager and listens for the change
fileSource.startup();
```

##### Create a file operation template
```java
// Template used with connection manager
FileTemplate template = new FileTemplate(fileSource.getConnection());
template.saveFileByStream("filename.doc", someFile);
```

## License

The Apache Software License, Version 2.0

Copyright  [2017]  [Anumbrella]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

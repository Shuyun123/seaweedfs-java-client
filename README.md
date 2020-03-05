# SeaweedFS Client For Java

[![Maven Central](http://img.shields.io/badge/maven_central-0.0.2.RELEASE-brightgreen.svg)](https://search.maven.org/#artifactdetails%7Corg.lokra.seaweedfs%7Cseaweedfs-client%7C0.7.3.RELEASE%7Cjar)
[![GitHub Release](http://img.shields.io/badge/Release-0.0.2.RELEASE-brightgreen.svg)](https://github.com/lokra-platform/seaweedfs-client/releases/tag/0.7.3.RELEASE)
[![Apache license](https://img.shields.io/badge/license-Apache-blue.svg)](http://opensource.org/licenses/Apache)


项目更改自[seaweedfs-java-client](https://github.com/Shuyun123/seaweedfs-java-client)，增加了一些内容，更新了一些组件。


# Quick Start

##### Create a connection manager
```java
FileSource fileSource = new FileSource();
ConnectionProperties connectionProperties
                = new ConnectionProperties.Builder()
                    .host("localhost")
                    .port(9333)
                    .maxConnection(100).build();
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

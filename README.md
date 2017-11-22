# SeaweedFS Client For Java

[![Maven Central](http://img.shields.io/badge/maven_central-0.0.1.RELEASE-brightgreen.svg)](https://search.maven.org/#artifactdetails%7Corg.lokra.seaweedfs%7Cseaweedfs-client%7C0.7.3.RELEASE%7Cjar)
[![GitHub Release](http://img.shields.io/badge/Release-0.0.1.RELEASE-brightgreen.svg)](https://github.com/lokra-platform/seaweedfs-client/releases/tag/0.7.3.RELEASE)
[![MIT license](http://img.shields.io/badge/license-MIT-blue.svg)](http://opensource.org/licenses/MIT)


######项目更改自[weed-client](https://github.com/lokra/weed-client)，修复了一下作者原来的部分bug，然后重新打包了。


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

Copyright [2017] [Anumbrella]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
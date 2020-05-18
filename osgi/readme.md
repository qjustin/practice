
 
D:\Dev\GITHUB\practice\osgi>mvn clean package
// 因为需要读取配置因此 必须在felix目录下
D:\Dev\GITHUB\practice\osgi\env\felix>java -jar ./bin/felix.jar

g! lb                                                                                                                                                                                                                                                                        15:59:59
START LEVEL 1
   ID|State      |Level|Name
    0|Active     |    0|System Bundle (6.0.1)|6.0.1
    1|Active     |    1|jansi (1.17.1)|1.17.1
    2|Active     |    1|JLine Bundle (3.7.0)|3.7.0
    3|Active     |    1|Apache Felix Bundle Repository (2.0.10)|2.0.10
    4|Active     |    1|Apache Felix Gogo Command (1.0.2)|1.0.2
    5|Active     |    1|Apache Felix Gogo JLine Shell (1.1.0)|1.1.0
    6|Active     |    1|Apache Felix Gogo Runtime (1.1.0)|1.1.0
g! install file:/Dev/GITHUB/practice/osgi/server/target/server.jar                                                                                                                                                                                                           16:38:39
Bundle ID: 8
g! install file:/Dev/GITHUB/practice/osgi/client/target/client.jar                                                                                                                                                                                                           16:39:05
Bundle ID: 9
g! lb                                                                                                                                                                                                                                                                        16:03:08
START LEVEL 1
   ID|State      |Level|Name
    0|Active     |    0|System Bundle (6.0.1)|6.0.1
    1|Active     |    1|jansi (1.17.1)|1.17.1
    2|Active     |    1|JLine Bundle (3.7.0)|3.7.0
    3|Active     |    1|Apache Felix Bundle Repository (2.0.10)|2.0.10
    4|Active     |    1|Apache Felix Gogo Command (1.0.2)|1.0.2
    5|Active     |    1|Apache Felix Gogo JLine Shell (1.1.0)|1.1.0
    6|Active     |    1|Apache Felix Gogo Runtime (1.1.0)|1.1.0
   11|Installed  |    1|server (1.0.0.SNAPSHOT)|1.0.0.SNAPSHOT
   12|Installed  |    1|client (1.0.0.SNAPSHOT)|1.0.0.SNAPSHOT
g! start 12                                                                                                                                                                                                                                                                  16:03:10
----------------hello client start---------------------
Service:Hello---not exists
----------------hello client start---------------------
g! start 11                                                                                                                                                                                                                                                                  16:03:20
----------------hello start---------------------
----------------hello start---------------------
g!        
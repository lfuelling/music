#!/bin/bash
echo Pulling changes...
git pull
echo
echo Switching Branch to debug...
git checkout debug
echo
echo Please enter your initials:
echo > vi src/main/java/net/k40s/Storage.java
vi src/main/java/net/k40s/Storage.java
echo Building project...
echo
mvn -Dmaven.test.skip=true -DskipTests clean package
echo
service tomcat7 stop
echo
echo Deleting files:
rm -vrf /var/lib/tomcat7/webapps/debug*
echo
echo Moving files:
mv -v target/music-*.war /var/lib/tomcat7/webapps/debug.war
echo
service tomcat7 start
echo
echo Done.

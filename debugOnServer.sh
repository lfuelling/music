#!/bin/bash
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
service tomcat
echo
echo Done.

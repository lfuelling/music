#!/bin/bash
mvn -Dmaven.test.skip=true -DskipTests clean package
echo
echo Deleting files:
rm -v ~/Desktop/ROOT.war
echo
echo Moving files:
mv -v target/music-*.war ~/Desktop/ROOT.war
echo
echo Done

#!/bin/bash
mvn -Dmaven.test.skip=true -DskipTests clean package
terminal-notifier -title "Build Successful" -message "The Warfile will be placed at the Desktop."
echo
echo Deleting files:
rm -v ~/Desktop/ROOT.war
echo
echo Moving files:
mv -v target/music-*.war ~/Desktop/ROOT.war
echo
echo Done

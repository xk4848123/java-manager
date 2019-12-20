#!/bin/sh

PWD=$(cd `dirname $0`;pwd);
opsdir=$1
cd ${PWD}
git pull
mvn clean package -Dmaven.test.skip=true
cp -f ./target/manager-ndc-0.0.1-SNAPSHOT.war ${opsdir}/webserver/tomcat8/webapps/
cd ${opsdir}/webserver/tomcat8/webapps
unzip -oq manager-ndc-0.0.1-SNAPSHOT.war -d manager-ndc
rm -rf manager-ndc-0.0.1-SNAPSHOT.war
cd ${opsdir}
git add .
git commit -a -m "update"
git push

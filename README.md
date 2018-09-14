[![Build Status](https://travis-ci.org/garethahealy/filetransfer-bridge.svg?branch=master)](https://travis-ci.org/garethahealy/filetransfer-bridge)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.garethahealy.filetransfer-bridge%3Afiletransfer-bridge&metric=coverage)](https://sonarcloud.io/dashboard?id=com.garethahealy.filetransfer-bridge%3Afiletransfer-bridge)
[![Release Version](https://img.shields.io/maven-central/v/com.garethahealy.filetransfer-bridge/filetransfer-bridge.svg?maxAge=2592000)](https://mvnrepository.com/artifact/com.garethahealy.filetransfer-bridge/filetransfer-bridge)
[![License](https://img.shields.io/hexpm/l/plug.svg?maxAge=2592000)]()

# filetransfer-bridge
Simple example showing SFTP bridge via a Apache Camel

## OCP
1.Create FIS2.0 resources

    oc create -f https://raw.githubusercontent.com/jboss-fuse/application-templates/GA/fis-image-streams.json
    oc create -f https://raw.githubusercontent.com/jboss-fuse/application-templates/GA/quickstarts/spring-boot-camel-xml-template.json

2.Deploy filetransfer-bridge

    oc process s2i-spring-boot-camel-xml -p GIT_REPO=https://github.com/garethahealy/filetransfer-bridge.git -p APP_NAME=filetransfer-bridge -p GIT_REF=master -p MAVEN_ARGS_APPEND=-Popenshift -n openshift | oc create -f -

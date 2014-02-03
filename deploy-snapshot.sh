#!/bin/sh

VERSION=`grep version pom.xml | head -2 |tail -1 | cut -f 2 -d '>' | cut -f 1 -d '<'`
echo Deploying dnsapi-client-$VERSION
mvn gpg:sign-and-deploy-file -Durl=https://oss.sonatype.org/content/repositories/snapshots/ -DrepositoryId=sonatype-nexus-snapshots -DpomFile=target/dnsapi-client-$VERSION.pom -Dfile=target/dnsapi-client-$VERSION.jar
mvn gpg:sign-and-deploy-file -Durl=https://oss.sonatype.org/content/repositories/snapshots/ -DrepositoryId=sonatype-nexus-snapshots -DpomFile=target/dnsapi-client-$VERSION.pom -Dfile=target/dnsapi-client-$VERSION-sources.jar -Dclassifier=sources
mvn gpg:sign-and-deploy-file -Durl=https://oss.sonatype.org/content/repositories/snapshots/ -DrepositoryId=sonatype-nexus-snapshots -DpomFile=target/dnsapi-client-$VERSION.pom -Dfile=target/dnsapi-client-$VERSION-javadoc.jar -Dclassifier=javadoc

## Introduction

This project is part of the [DiscoveryDNS](http://www.discoverydns.com) solution. It is a client to the DiscoveryDNS Server REST API.

## License

This project is placed under the [LGPLv3](http://www.gnu.org/licenses/lgpl.txt) license.

## API Description

Please consult the <a href="http://discoverydns.github.io/dnsapi-client/doc/DDNS%20-%20DiscoveryDNS%20API%20-%20v1.3.pdf">"doc/DDNS - DiscoveryDNS API - v1.3.pdf"</a> document for a full description of all possible REST requests and responses.

## Usage examples

Please consult the sources in the "com.discoverydns.dnsapiclient.example" package for usage examples of all possible REST requests.

## Building

To build the dnsapi-client, you must have the Java Development Kit (JDK) v6.0 or above, as well as Maven v2.2.1 or above installed. The project can be built with the command `mvn package`.

## Installation and Setup

### How to get the dnsapi-client

#### Direct download

Obtain the latest dnsapi-client here: [dnsapi-client v1.0.1](http://discoverydns.github.io/dnsapi-client/repo/dnsapi-client-1.0.1.jar) ([sources](http://discoverydns.github.io/dnsapi-client/repo/dnsapi-client-1.0.1-sources.jar) | [javadoc](http://discoverydns.github.io/dnsapi-client/repo/dnsapi-client-1.0.1-javadoc.jar))

#### Dependency Management

Use your build's dependency management tool to automatically download the dnsapi-client from Maven Central.

* groupId: `com.discoverydns.dnsapi`
* artifactId: `dnsapi-client`
* version: `1.0.1`

For example (using Maven):

    <dependencies>
       <dependency>
          <groupId>com.discoverydns.dnsapi</groupId>
          <artifactId>dnsapi-client</artifactId>
          <version>1.0.1</version>
       </dependency>
    </dependencies>

#### Contribute

You can view the source on [GitHub/DiscoveryDNS](http://github.com/discoverydns/dnsapi-client). Contributions via pull requests are welcome.

### Development documentation

The javadoc is available online: [dnsapi-client javadoc](http://discoverydns.github.io/dnsapi-client/repo/dnsapi-client-1.0.1-javadoc.jar)

## Introduction

This project is part of the [DiscoveryDNS](http://www.discoverydns.com) solution. It is a Java client to the DiscoveryDNS Server REST API.

## User manual

The user manual of the DiscoveryDNS API can be found in the ["doc/DDNS - Managed DNS Manual - 2.1.docx"](https://github.com/discoverydns/dnsapi-client/blob/master/doc/DDNS%20-%20Managed%20DNS%20Manual%20-%202.1.docx) document.

## DNSSEC Practice Statement

The DNSSEC Practice Statement of the DiscoveryDNS API can be found in the ["doc/DDNS - DNSSEC Practice Statement - Reseller - 1.0.docx"](https://github.com/discoverydns/dnsapi-client/blob/master/doc/DDNS%20-%20DNSSEC%20Practice%20Statement%20-%20Reseller%20-%201.0.docx) document.

## API Description

The details of the DiscoveryDNS REST API commands and responses can be found in the ["doc/DDNS - DiscoveryDNS API - v2.1.docx"](https://github.com/discoverydns/dnsapi-client/blob/master/doc/DDNS%20-%20DiscoveryDNS%20API%20-%20v2.1.docx) document.

If you want to develop a client to the API in a language different from Java, you will have to consult this document to find out what REST calls are to be implemented and what responses are to be expected.

## License

This project is placed under the [LGPLv3](http://www.gnu.org/licenses/lgpl.txt) license.

## Installation and Setup

### How to get the dnsapi-client

#### Dependency Management

The prefered way is to use your build's dependency management tool to automatically download the dnsapi-client and all its dependencies from Maven Central.

* groupId: `com.discoverydns.dnsapi`
* artifactId: `dnsapi-client`
* version: `1.4.0`

For example (using Maven):

    <dependencies>
       <dependency>
          <groupId>com.discoverydns.dnsapi</groupId>
          <artifactId>dnsapi-client</artifactId>
          <version>1.4.0</version>
       </dependency>
    </dependencies>

### Building

To build the dnsapi-client, you must have the Java Development Kit (JDK) v6.0 or above, as well as Maven v2.2.1 or above installed. The project can be built with the command `mvn package`.

## Development documentation

The javadoc is available online: [dnsapi-client javadoc](http://discoverydns.github.io/dnsapi-client/javadoc/index.html).

## Usage examples

Please consult the sources in the `com.discoverydns.dnsapiclient.example` package for usage examples of all possible REST requests. However, here is a small description of what needs to be done:
* The dnsapi-client library uses a [DNSAPIClient](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/DNSAPIClient.html) to send commmands to the DNSAPI server, through a REST API over the HTTPS protocol.
* The [DNSAPIClient](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/DNSAPIClient.html) should be obtained from a [DNSAPIClientFactory](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/DNSAPIClientFactory.html).
* This [DNSAPIClientFactory](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/DNSAPIClientFactory.html) uses several feature providers to create the client:
    * An implementation of the [DNSAPIClientConfig](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/config/DNSAPIClientConfig.html) interface, to configure the HTTP connection used by the client.
    * An implementation of the [SSLContextFactory](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/SSLContextFactory.html) interface, to create a `javax.net.ssl.SSLContext` instance, to establish a secure communication between the client and the server.
    * An implementation of the [ObjectMapperFactory](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/ObjectMapperFactory.html) interface, to create an `com.fasterxml.jackson.databind.ObjectMapper` instance, for the client to serialize and deserialize the JSON data sent to or coming from the server.
    * An implementation of the [ClientTransactionIdStrategy](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/ClientTransactionIdStrategy.html) interface, to generate a client transaction id to be put in each transaction's meta-data.
    * An implementation of the [TransactionLogHandler](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/TransactionLogHandler.html) interface, to log the successful and failed transactions between the client and the server.
* To obtain the [DNSAPIClient](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/DNSAPIClient.html) from the [DNSAPIClientFactory](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/DNSAPIClientFactory.html), you need to:
    * Setup the SLF4J logging (the client uses the logback's native binding) for log messages.
    * Instantiate the [DNSAPIClientFactory](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/DNSAPIClientFactory.html).
    * Then, you can either:
        * Call its `createInstance` method, passing your custom implementation of all the features providers' interfaces described above.
            * Call its `createInstanceFromDefaultProviders` method to use the provided default implementation of all the features providers' interfaces. You then only need to pass:
                * An implementation of the [DNSAPIClientConfig](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/config/DNSAPIClientConfig.html) interface, to configure the HTTP connection used by the client.
                * An implementation of the [DefaultSSLContextFactoryConfig](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/config/DefaultSSLContextFactoryConfig.html) interface, to configure the provided [DefaultSSLContextFactory](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/DefaultSSLContextFactory.html), using path-accessible keystore and if required, trustore resources (return null to the truststore path method will use the system truststore).
                * An implementation of the [DefaultTransactionLogHandlerConfig](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/config/DefaultTransactionLogHandlerConfig.html) interface, to configure the provided [DefaultTransactionLogHandler](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/DefaultTransactionLogHandler.html), using path-accessible log file resources, with a rotation pattern.
                * A [DefaultObjectMapperFactory](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/DefaultObjectMapperFactory.html) will then be automatically provided, using the Jackson serializers and deserializers provided by the [ddns-dnsjava](http://discoverydns.github.io/ddns-dnsjava/) library.
                * A [DefaultClientTransactionIdStrategy](http://static.javadoc.io/com.discoverydns.dnsapi/dnsapi-client/1.4.0/com/discoverydns/dnsapiclient/DefaultClientTransactionIdStrategy.html) will then be automatically provided, using random UUID strings as client transaction Ids.
* Once the client is created, you can use it to send the commands to the server, thanks to its `process` method. The client is thread safe, and one client can be shared by many threads, however it can only execute one outstanding command per http connection configured.
* Then simply follow the examples from the `com.discoverydns.dnsapiclient.example` package to see how to create and execute each command object.

## Contribute

You can view the source on [GitHub/DiscoveryDNS](http://github.com/discoverydns/dnsapi-client). Contributions via pull requests are welcome.

## Contact

Contact us at [support@discoverydns.com](mailto:support@discoverydns.com).

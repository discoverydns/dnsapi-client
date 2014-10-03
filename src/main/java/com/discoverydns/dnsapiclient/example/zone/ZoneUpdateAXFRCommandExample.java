package com.discoverydns.dnsapiclient.example.zone;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateAXFRCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateAXFRResponse;
import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.example.ExampleDNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultTransactionLogHandlerConfig;
import com.google.common.collect.Lists;
import org.slf4j.LoggerFactory;

/**
 * Example of sending an ZoneUpdateAXFRCommand and receiving an ZoneUpdateAXFRResponse.
 *
 * @author Bin Chen
 */
public final class ZoneUpdateAXFRCommandExample {

    private ZoneUpdateAXFRCommandExample() {
        // for CheckStyle: utility class should not have public constructor
    }

    public static void main(String[] args) {
        //Configure logging properties, using Logback
        final Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);

        //Instantiate configuration beans
        final DNSAPIClientConfig config = new ExampleDNSAPIClientConfig();
        final DefaultSSLContextFactoryConfig sslConfig = new ExampleDefaultSSLContextFactoryConfig();
        final DefaultTransactionLogHandlerConfig logConfig = new ExampleDefaultTransactionLogHandlerConfig();

        //Instantiate client instance from DNSAPIClientFactory
        final DNSAPIClientFactory dnsapiClientFactory = new DNSAPIClientFactory();
        DNSAPIClient client = null;
        try {
            client = dnsapiClientFactory.createInstanceFromDefaultProviders(config, sslConfig, logConfig);
        } catch (final Exception e) {
            root.error("failed to instantiate the DNSAPI client, please check your configurations", e);
            System.exit(1);
        }

        //Create command
        final ZoneUpdateAXFRCommand command =
                new ZoneUpdateAXFRCommand.Builder()
                        .withId("<my-zone-id>")
                        .withVersion(0L)
                        .withTsigName("<my-tsig-name>")
                        .withTsigKey("<my-tsig-key>")
                        .withTsigAlgorithm("<my-tsig-algorighm>")
                        .withAXFRServers(Lists.newArrayList("master-server-ip1", "master-server-ip2"))
                        .build();

        //Send command to server and receive response
        try {
            Response<ZoneUpdateAXFRResponse> response = client.process(command);

            root.info("== Successful response ==");
            root.info("== Server transaction id: {}", response.getServerTransactionId());
            root.info("== Client transaction id: {}", response.getClientTransactionId());
            root.info("== Processing time: {}ms", response.getTransactionProcessingTime());

            //The response object can now be used
            root.info("Zone URI: {}", response.getResponseObject().getURI());
            root.info("Zone UUID: {}", response.getResponseObject().getId());
            root.info("Zone version: {}", response.getResponseObject().getVersion());
            root.info("Zone name: {}", response.getResponseObject().getName());
        } catch (final Throwable e) {
            root.error("failed to receive response for ZoneUpdateAXFRCommand", e);
            System.exit(1);
        }
    }
}

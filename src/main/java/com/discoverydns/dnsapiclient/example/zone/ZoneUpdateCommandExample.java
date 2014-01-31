package com.discoverydns.dnsapiclient.example.zone;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateResponse;
import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultTransactionLogHandlerConfig;

/**
 * Example of sending an ZoneUpdateCommand and receiving an ZoneUpdateResponse
 * @author Arnaud Dumont
 */
public class ZoneUpdateCommandExample {

    public static void main(String[] args) {
        //Configure logging properties, using Logback
        final ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory
                .getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
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
            e.printStackTrace();
            System.exit(1);
        }

        //Create command
        final ZoneUpdateCommand command =
                new ZoneUpdateCommand.Builder()
                        .withId("<my-zone-id>")
                        .withVersion(0L)
                        .withDnssecSigned(Boolean.FALSE)
                        .withBrandedNameServers(Boolean.TRUE)
                        .withGroup("mygroup")
                        .withNameServerSetId("<my-nss-id>")
                        .withPlanId("<my-plan-id>")
                        .build();

        //Send command to server and receive response
        try {
            Response<ZoneUpdateResponse> response = client.process(command);

            System.out.println("== Successful response ==");
            System.out.println("== Server transaction id: " + response.getServerTransactionId());
            System.out.println("== Client transaction id: " + response.getClientTransactionId());
            System.out.println("== Processing time: " + response.getTransactionProcessingTime() + "ms");

            //The response object can now be used
            System.out.println("Zone URI: " + response.getResponseObject().getURI());
            System.out.println("Zone UUID: " + response.getResponseObject().getId());
            System.out.println("Zone version: " + response.getResponseObject().getVersion());
            System.out.println("Zone name: " + response.getResponseObject().getName());
        } catch (final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

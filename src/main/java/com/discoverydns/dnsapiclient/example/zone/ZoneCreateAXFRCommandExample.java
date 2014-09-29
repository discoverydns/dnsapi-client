package com.discoverydns.dnsapiclient.example.zone;

import org.xbill.DNS.TSIG;

import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.zone.ZoneCreateAXFRCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneCreateAXFRResponse;
import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.example.ExampleDNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultTransactionLogHandlerConfig;
import com.google.common.collect.Lists;

import ch.qos.logback.classic.Level;

/**
 * Example of sending an ZoneCreateAXFRCommand and receiving an ZoneCreateAXFRResponse
 * @author Arnaud Dumont
 */
public class ZoneCreateAXFRCommandExample {

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
        ZoneCreateAXFRCommand command = null;
        try {
            command = new ZoneCreateAXFRCommand.Builder()
                        .withName("myzone.com")
                        .withGroup("mygroup")
                        .withNameServerSetId("<my-nss-id>")
                        .withPlanId("<my-plan-id>")
                        .withAXFRServers(Lists.newArrayList("myAXFRserver"))
                        .withTsigKey("myTSIGKey")
                        .withTsigName("myTSIGName")
                        .withTsigAlgorithm(TSIG.HMAC_MD5.toString())
                        .build();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        //Send command to server and receive response
        try {
            Response<ZoneCreateAXFRResponse> response = client.process(command);

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

package com.discoverydns.dnsapiclient.example.zone;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.zone.ZoneCreateCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneCreateResponse;
import com.discoverydns.dnsapiclient.example.ExampleConfig;

/**
 * Example of sending an ZoneCreateCommand and receiving an ZoneCreateResponse
 * @author Arnaud Dumont
 */
public class ZoneCreateCommandExample {

    public static void main(String[] args) {
        //Configure logging properties, using Logback
        final ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory
                .getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);

        //Instantiate configuration bean
        final DNSAPIClientConfig config = new ExampleConfig();

        //Instantiate client instance from DNSAPIClientFactory
        final DNSAPIClientFactory dnsapiClientFactory = new DNSAPIClientFactory();
        DNSAPIClient client = null;
        try {
            client = dnsapiClientFactory.createInstance(config);
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        //Create command
        final ZoneCreateCommand command =
                new ZoneCreateCommand.Builder()
                        .withName("myzone.com")
                        .withDnssecSigned(Boolean.FALSE)
                        .withBrandedNameServers(Boolean.TRUE)
                        .withGroup("mygroup")
                        .withNameServerSetId("<my-nss-id>")
                        .withPlanId("<my-plan-id>")
                        .build();

        //Send command to server and receive response
        try {
            Response<ZoneCreateResponse> response = client.process(command);

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

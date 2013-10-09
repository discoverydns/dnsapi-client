package com.discoverydns.dnsapiclient.example.zone;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.zone.ZoneDeleteCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneDeleteResponse;
import com.discoverydns.dnsapiclient.example.ExampleConfig;

/**
 * Example of sending an ZoneDeleteCommand and receiving an ZoneDeleteResponse
 * @author Arnaud Dumont
 */
public class ZoneDeleteCommandExample {

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
        final ZoneDeleteCommand command =
                new ZoneDeleteCommand.Builder()
                        .withId("<my-zone-id>")
                        .build();

        //Send command to server and receive response
        try {
            Response<ZoneDeleteResponse> response = client.process(command);

            System.out.println("== Successful response ==");
            System.out.println("== Server transaction id: " + response.getServerTransactionId());
            System.out.println("== Client transaction id: " + response.getClientTransactionId());
            System.out.println("== Processing time: " + response.getTransactionProcessingTime() + "ms");
        } catch (final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

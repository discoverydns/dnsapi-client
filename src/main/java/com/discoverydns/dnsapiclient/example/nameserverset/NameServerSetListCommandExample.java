package com.discoverydns.dnsapiclient.example.nameserverset;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.nameServerSet.NameServerSetListCommand;
import com.discoverydns.dnsapiclient.command.nameServerSet.NameServerSetListResponse;
import com.discoverydns.dnsapiclient.command.nameServerSet.NameServerSetRecord;
import com.discoverydns.dnsapiclient.example.ExampleConfig;

/**
 * Example of sending a NameServerSetListCommand and receiving an NameServerSetListResponse
 * @author Arnaud Dumont
 */
public class NameServerSetListCommandExample {

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
        final NameServerSetListCommand command =
                new NameServerSetListCommand.Builder()
                        .build();

        //Send command to server and receive response
        try {
            Response<NameServerSetListResponse> response = client.process(command);

            System.out.println("== Successful response ==");
            System.out.println("== Server transaction id: " + response.getServerTransactionId());
            System.out.println("== Client transaction id: " + response.getClientTransactionId());
            System.out.println("== Processing time: " + response.getTransactionProcessingTime() + "ms");

            //The response object can now be used
            System.out.println("Search request URI: " + response.getResponseObject().getURI());
            System.out.println("Search results count: " + response.getResponseObject().getTotalRecordCount());
            for (NameServerSetRecord record: response.getResponseObject().getNameServerSetRecords()) {
                System.out.println("-- Found NameServerSet:");
                System.out.println("   NameServerSet URI: " + record.getUri());
                System.out.println("   NameServerSet UUID: " + record.getId());
                System.out.println("   NameServerSet name: " + record.getName());
                System.out.println("   NameServerSet create date: " + record.getCreateDate());
                System.out.println("   NameServerSet last update date: " + record.getLastUpdateDate());
            }
        } catch (final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

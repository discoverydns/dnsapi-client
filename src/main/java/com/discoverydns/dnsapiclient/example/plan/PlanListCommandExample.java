package com.discoverydns.dnsapiclient.example.plan;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.plan.PlanListCommand;
import com.discoverydns.dnsapiclient.command.plan.PlanListResponse;
import com.discoverydns.dnsapiclient.command.plan.PlanRecord;
import com.discoverydns.dnsapiclient.example.ExampleConfig;

/**
 * Example of sending a PlanListCommand and receiving an PlanListResponse
 * @author Arnaud Dumont
 */
public class PlanListCommandExample {

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
        final PlanListCommand command =
                new PlanListCommand.Builder()
                        .build();

        //Send command to server and receive response
        try {
            Response<PlanListResponse> response = client.process(command);

            System.out.println("== Successful response ==");
            System.out.println("== Server transaction id: " + response.getServerTransactionId());
            System.out.println("== Client transaction id: " + response.getClientTransactionId());
            System.out.println("== Processing time: " + response.getTransactionProcessingTime() + "ms");

            //The response object can now be used
            System.out.println("Search request URI: " + response.getResponseObject().getURI());
            System.out.println("Search results count: " + response.getResponseObject().getTotalRecordCount());
            for (PlanRecord record: response.getResponseObject().getPlanRecords()) {
                System.out.println("-- Found Plan:");
                System.out.println("   Plan URI: " + record.getUri());
                System.out.println("   Plan UUID: " + record.getId());
                System.out.println("   Plan name: " + record.getName());
                System.out.println("   Plan status: " + record.getStatus());
                System.out.println("   Plan create date: " + record.getCreateDate());
                System.out.println("   Plan last update date: " + record.getLastUpdateDate());
            }
        } catch (final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

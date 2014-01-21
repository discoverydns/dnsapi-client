package com.discoverydns.dnsapiclient.example.zone;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.zone.ZoneGetQueryUsageCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneGetQueryUsageResponse;
import com.discoverydns.dnsapiclient.command.zone.ZoneQueryUsageGranularity;
import com.discoverydns.dnsapiclient.command.zone.ZoneQueryUsageRecord;
import com.discoverydns.dnsapiclient.example.ExampleConfig;
import org.joda.time.LocalDateTime;

/**
 * Example of sending an ZoneGetQueryUsageCommand and receiving an ZoneGetQueryUsageResponse
 * @author Arnaud Dumont
 */
public class ZoneGetQueryUsageCommandExample {

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
        final ZoneGetQueryUsageCommand command =
                new ZoneGetQueryUsageCommand.Builder()
                        .withId("<my-zone-id>")
                        .withSearchStartDate(LocalDateTime.now())
                        .withSearchEndDate(LocalDateTime.now())
                        .withSearchGranularity(ZoneQueryUsageGranularity.hourly)
                        .withSearchGroupUsage(false)
                        .build();

        //Send command to server and receive response
        try {
            Response<ZoneGetQueryUsageResponse> response = client.process(command);

            System.out.println("== Successful response ==");
            System.out.println("== Server transaction id: " + response.getServerTransactionId());
            System.out.println("== Client transaction id: " + response.getClientTransactionId());
            System.out.println("== Processing time: " + response.getTransactionProcessingTime() + "ms");

            //The response object can now be used
            System.out.println("Zone query usage URI: " + response.getResponseObject().getURI());
            System.out.println("Zone query usage records total count: " +
                    response.getResponseObject().getTotalRecordCount());
            System.out.println("-- Zone query usage records: ");
            for (ZoneQueryUsageRecord queryUsageRecord :
                    response.getResponseObject().getZoneQueryUsageRecords()) {
                System.out.println("   Timestamp: " + queryUsageRecord.getTimestamp()
                        + ", Count: " + queryUsageRecord.getCount());
            }
        } catch (final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

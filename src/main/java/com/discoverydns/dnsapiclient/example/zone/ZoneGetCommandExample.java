package com.discoverydns.dnsapiclient.example.zone;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.zone.ZoneGetCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneGetResponse;
import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultTransactionLogHandlerConfig;
import org.xbill.DNS.Record;

/**
 * Example of sending an ZoneGetCommand and receiving an ZoneGetResponse
 * @author Arnaud Dumont
 */
public class ZoneGetCommandExample {

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
        final ZoneGetCommand command =
                new ZoneGetCommand.Builder()
                        .withId("<my-zone-id>")
                        .build();

        //Send command to server and receive response
        try {
            Response<ZoneGetResponse> response = client.process(command);

            System.out.println("== Successful response ==");
            System.out.println("== Server transaction id: " + response.getServerTransactionId());
            System.out.println("== Client transaction id: " + response.getClientTransactionId());
            System.out.println("== Processing time: " + response.getTransactionProcessingTime() + "ms");

            //The response object can now be used
            final ZoneGetResponse zoneGetResponse = response.getResponseObject();
            System.out.println("Zone URI: " + zoneGetResponse.getURI());
            System.out.println("Zone UUID: " + zoneGetResponse.getId());
            System.out.println("Zone version: " + zoneGetResponse.getVersion());
            System.out.println("Zone name: " + zoneGetResponse.getName());
            System.out.println("Zone branded nameServers: " + zoneGetResponse.getBrandedNameServers());
            System.out.println("Zone DNSSEC signed: " + zoneGetResponse.getDNSSECSigned());
            if (zoneGetResponse.getDNSSECSigned()) {
                System.out.println("   ZSK Rollover state: " + zoneGetResponse.getZskRollOverState());
            }
            System.out.println("Zone pending operation: " + zoneGetResponse.getPendingOperation());
            System.out.println("Zone last publish date: " + zoneGetResponse.getLastPublishDate());
            System.out.println("Zone serial: " + zoneGetResponse.getSerial());
            System.out.println("Zone group: " + zoneGetResponse.getGroup());
            System.out.println("Zone sponsor Account UUID: " + zoneGetResponse.getSponsorAccountId());
            System.out.println("Zone sponsor Account identifier: "
                    + zoneGetResponse.getSponsorAccountIdentifier());
            System.out.println("Zone associated NameServerInterfaceSet UUID: "
                    + zoneGetResponse.getNameServerInterfaceSetId());
            System.out.println("Zone associated NameServerInterfaceSet name: "
                    + zoneGetResponse.getNameServerInterfaceSetName());
            System.out.println("Zone associated NameServerSet UUID: "
                    + zoneGetResponse.getNameServerSetId());
            System.out.println("Zone associated NameServerSet name: "
                    + zoneGetResponse.getNameServerSetName());
            System.out.println("Zone associated Plan UUID: "
                    + zoneGetResponse.getPlanId());
            System.out.println("Zone associated Plan name: "
                    + zoneGetResponse.getPlanName());
            System.out.println("-- Zone system-generated delegation records: ");
            for (Record delegationRecord : zoneGetResponse.getDelegationResourceRecords()) {
                System.out.println("   " + delegationRecord.toString());
            }
            System.out.println("-- Zone system-generated records: ");
            for (Record ddnsRecord : zoneGetResponse.getDDNSResourceRecords()) {
                System.out.println("   " + ddnsRecord.toString());
            }
            System.out.println("-- Zone user-generated records: ");
            for (Record userRecord : zoneGetResponse.getUserResourceRecords()) {
                System.out.println("   " + userRecord.toString());
            }
            System.out.println("Zone create Account UUID: " + zoneGetResponse.getCreateAccountId());
            System.out.println("Zone create Account Identifier: "
                    + zoneGetResponse.getCreateAccountIdentifier());
            System.out.println("Zone create User UUID: " + zoneGetResponse.getCreateUserId());
            System.out.println("Zone create User name: "
                    + zoneGetResponse.getCreateUserName());
            System.out.println("Zone create date: " + zoneGetResponse.getCreateDate());
            System.out.println("Zone last update Account UUID: "
                    + zoneGetResponse.getLastUpdateAccountId());
            System.out.println("Zone last update Account Identifier: "
                    + zoneGetResponse.getLastUpdateAccountIdentifier());
            System.out.println("Zone last update User UUID: " + zoneGetResponse.getLastUpdateUserId());
            System.out.println("Zone last update User name: "
                    + zoneGetResponse.getLastUpdateUserName());
            System.out.println("Zone last update date: " + zoneGetResponse.getLastUpdateDate());
        } catch (final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

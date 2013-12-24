package com.discoverydns.dnsapiclient.example.zone;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.zone.ZoneGetCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneGetResponse;
import com.discoverydns.dnsapiclient.example.ExampleConfig;
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
            System.out.println("Zone URI: " + response.getResponseObject().getURI());
            System.out.println("Zone UUID: " + response.getResponseObject().getId());
            System.out.println("Zone version: " + response.getResponseObject().getVersion());
            System.out.println("Zone name: " + response.getResponseObject().getName());
            System.out.println("Zone branded nameServers: " + response.getResponseObject().getBrandedNameServers());
            System.out.println("Zone DNSSEC signed: " + response.getResponseObject().getDNSSECSigned());
            System.out.println("Zone serial: " + response.getResponseObject().getSerial());
            System.out.println("Zone group: " + response.getResponseObject().getGroup());
            System.out.println("Zone sponsor Account UUID: " + response.getResponseObject().getSponsorAccountId());
            System.out.println("Zone sponsor Account identifier: "
                    + response.getResponseObject().getSponsorAccountIdentifier());
            System.out.println("Zone associated NameServerInterfaceSet UUID: "
                    + response.getResponseObject().getNameServerInterfaceSetId());
            System.out.println("Zone associated NameServerInterfaceSet name: "
                    + response.getResponseObject().getNameServerInterfaceSetName());
            System.out.println("Zone associated NameServerSet UUID: "
                    + response.getResponseObject().getNameServerSetId());
            System.out.println("Zone associated NameServerSet name: "
                    + response.getResponseObject().getNameServerSetName());
            System.out.println("Zone associated Plan UUID: "
                    + response.getResponseObject().getPlanId());
            System.out.println("Zone associated Plan name: "
                    + response.getResponseObject().getPlanName());
            System.out.println("-- Zone system-generated delegation records: ");
            for (Record delegationRecord : response.getResponseObject().getDelegationResourceRecords()) {
                System.out.println("   " + delegationRecord.toString());
            }
            System.out.println("-- Zone system-generated records: ");
            for (Record ddnsRecord : response.getResponseObject().getDDNSResourceRecords()) {
                System.out.println("   " + ddnsRecord.toString());
            }
            System.out.println("-- Zone user-generated records: ");
            for (Record userRecord : response.getResponseObject().getUserResourceRecords()) {
                System.out.println("   " + userRecord.toString());
            }
            System.out.println("Zone create Account UUID: " + response.getResponseObject().getCreateAccountId());
            System.out.println("Zone create Account Identifier: "
                    + response.getResponseObject().getCreateAccountIdentifier());
            System.out.println("Zone create User UUID: " + response.getResponseObject().getCreateUserId());
            System.out.println("Zone create User name: "
                    + response.getResponseObject().getCreateUserName());
            System.out.println("Zone create date: " + response.getResponseObject().getCreateDate());
            System.out.println("Zone last update Account UUID: "
                    + response.getResponseObject().getLastUpdateAccountId());
            System.out.println("Zone last update Account Identifier: "
                    + response.getResponseObject().getLastUpdateAccountIdentifier());
            System.out.println("Zone last update User UUID: " + response.getResponseObject().getLastUpdateUserId());
            System.out.println("Zone last update User name: "
                    + response.getResponseObject().getLastUpdateUserName());
            System.out.println("Zone last update date: " + response.getResponseObject().getLastUpdateDate());
        } catch (final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

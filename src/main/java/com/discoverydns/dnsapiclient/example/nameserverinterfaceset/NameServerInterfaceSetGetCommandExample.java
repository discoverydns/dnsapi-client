package com.discoverydns.dnsapiclient.example.nameserverinterfaceset;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterface;
import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterfaceSetGetCommand;
import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterfaceSetGetResponse;
import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultTransactionLogHandlerConfig;

/**
 * Example of sending a NameServerInterfaceSetGetCommand and receiving an NameServerInterfaceSetGetResponse
 * @author Arnaud Dumont
 */
public class NameServerInterfaceSetGetCommandExample {

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
        final NameServerInterfaceSetGetCommand command =
                new NameServerInterfaceSetGetCommand.Builder()
                        .withIdOrName("<my-nsis-id>")
                        .build();

        //Send command to server and receive response
        try {
            Response<NameServerInterfaceSetGetResponse> response = client.process(command);

            System.out.println("== Successful response ==");
            System.out.println("== Server transaction id: " + response.getServerTransactionId());
            System.out.println("== Client transaction id: " + response.getClientTransactionId());
            System.out.println("== Processing time: " + response.getTransactionProcessingTime() + "ms");

            //The response object can now be used
            System.out.println("NameServerInterfaceSet URI: " + response.getResponseObject().getURI());
            System.out.println("NameServerInterfaceSet UUID: " + response.getResponseObject().getId());
            System.out.println("NameServerInterfaceSet version: " + response.getResponseObject().getVersion());
            System.out.println("NameServerInterfaceSet name: " + response.getResponseObject().getName());
            System.out.println("NameServerInterfaceSet status: " + response.getResponseObject().getStatus());
            for (NameServerInterface nameServerInterface : response.getResponseObject().getNameServerInterfaces()) {
                System.out.println("-- NameServerInterface:");
                System.out.println("   Interface name: " + nameServerInterface.getName());
                System.out.println("   Interface I.P.v4: " + nameServerInterface.getIpv4Address().getHostAddress());
                System.out.println("   Interface I.P.v6: " + nameServerInterface.getIpv6Address().getHostAddress());
            }
            System.out.println("NameServerInterfaceSet create Account UUID: "
                    + response.getResponseObject().getCreateAccountId());
            System.out.println("NameServerInterfaceSet create Account Identifier: "
                    + response.getResponseObject().getCreateAccountIdentifier());
            System.out.println("NameServerInterfaceSet create User UUID: "
                    + response.getResponseObject().getCreateUserId());
            System.out.println("NameServerInterfaceSet create User name: "
                    + response.getResponseObject().getCreateUserName());
            System.out.println("NameServerInterfaceSet create date: " + response.getResponseObject().getCreateDate());
            System.out.println("NameServerInterfaceSet last update Account UUID: "
                    + response.getResponseObject().getLastUpdateAccountId());
            System.out.println("NameServerInterfaceSet last update Account Identifier: "
                    + response.getResponseObject().getLastUpdateAccountIdentifier());
            System.out.println("NameServerInterfaceSet last update User UUID: "
                    + response.getResponseObject().getLastUpdateUserId());
            System.out.println("NameServerInterfaceSet last update User name: "
                    + response.getResponseObject().getLastUpdateUserName());
            System.out.println("NameServerInterfaceSet last update date: "
                    + response.getResponseObject().getLastUpdateDate());
        } catch (final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

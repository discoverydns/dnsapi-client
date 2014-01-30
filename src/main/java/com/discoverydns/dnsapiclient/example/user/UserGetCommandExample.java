package com.discoverydns.dnsapiclient.example.user;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.user.UserGetCommand;
import com.discoverydns.dnsapiclient.command.user.UserGetResponse;
import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultTransactionLogHandlerConfig;

/**
 * Example of sending an UserGetCommand and receiving an UserGetResponse
 * @author Arnaud Dumont
 */
public class UserGetCommandExample {

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
        final UserGetCommand command =
                new UserGetCommand.Builder()
                        .withIdOrUsername("admin")
                        .build();

        //Send command to server and receive response
        try {
            Response<UserGetResponse> response = client.process(command);

            System.out.println("== Successful response ==");
            System.out.println("== Server transaction id: " + response.getServerTransactionId());
            System.out.println("== Client transaction id: " + response.getClientTransactionId());
            System.out.println("== Processing time: " + response.getTransactionProcessingTime() + "ms");

            //The response object can now be used
            System.out.println("User URI: " + response.getResponseObject().getURI());
            System.out.println("User UUID: " + response.getResponseObject().getId());
            System.out.println("User version: " + response.getResponseObject().getVersion());
            System.out.println("User username: " + response.getResponseObject().getUsername());
            System.out.println("User status: " + response.getResponseObject().getStatus());
            System.out.println("User name: " + response.getResponseObject().getName());
            System.out.println("User email: " + response.getResponseObject().getEmail());
            System.out.println("-- Roles:");
            for (String role : response.getResponseObject().getRoles()) {
                System.out.println("   " + role);
            }
            System.out.println("User password expiry date: " + response.getResponseObject().getPasswordExpireDate());
            System.out.println("User sponsor Account UUID: " + response.getResponseObject().getSponsorAccountId());
            System.out.println("User sponsor Account identifier: "
                    + response.getResponseObject().getSponsorAccountIdentifier());
            System.out.println("User create Account UUID: " + response.getResponseObject().getCreateAccountId());
            System.out.println("User create Account Identifier: "
                    + response.getResponseObject().getCreateAccountIdentifier());
            System.out.println("User create User UUID: " + response.getResponseObject().getCreateUserId());
            System.out.println("User create User name: "
                    + response.getResponseObject().getCreateUserName());
            System.out.println("User create date: " + response.getResponseObject().getCreateDate());
            System.out.println("User last update Account UUID: "
                    + response.getResponseObject().getLastUpdateAccountId());
            System.out.println("User last update Account Identifier: "
                    + response.getResponseObject().getLastUpdateAccountIdentifier());
            System.out.println("User last update User UUID: " + response.getResponseObject().getLastUpdateUserId());
            System.out.println("User last update User name: "
                    + response.getResponseObject().getLastUpdateUserName());
            System.out.println("User last update date: " + response.getResponseObject().getLastUpdateDate());
        } catch (final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

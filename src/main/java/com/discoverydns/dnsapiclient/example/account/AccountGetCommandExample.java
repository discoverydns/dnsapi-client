package com.discoverydns.dnsapiclient.example.account;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.account.AccountGetCommand;
import com.discoverydns.dnsapiclient.command.account.AccountGetResponse;
import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultTransactionLogHandlerConfig;

/**
 * Example of sending an AccountGetCommand and receiving an AccountGetResponse
 * @author Arnaud Dumont
 */
public class AccountGetCommandExample {

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
        final AccountGetCommand command =
                new AccountGetCommand.Builder()
                        .withIdOrIdentifier("system")
                        .build();

        //Send command to server and receive response
        try {
            Response<AccountGetResponse> response = client.process(command);

            System.out.println("== Successful response ==");
            System.out.println("== Server transaction id: " + response.getServerTransactionId());
            System.out.println("== Client transaction id: " + response.getClientTransactionId());
            System.out.println("== Processing time: " + response.getTransactionProcessingTime() + "ms");

            //The response object can now be used
            System.out.println("Account URI: " + response.getResponseObject().getURI());
            System.out.println("Account UUID: " + response.getResponseObject().getId());
            System.out.println("Account version: " + response.getResponseObject().getVersion());
            System.out.println("Account name: " + response.getResponseObject().getName());
            System.out.println("Account identifier: " + response.getResponseObject().getIdentifier());
            System.out.println("Account status: " + response.getResponseObject().getStatus());
            System.out.println("Account minimum commitment: " + response.getResponseObject().getMinimumCommitment());
            System.out.println("Account currency: " + response.getResponseObject().getCurrency());
            System.out.println("Account minimum commitment start date: "
                    + response.getResponseObject().getMinimumCommitmentStartDate());
            System.out.println("Account create Account UUID: " + response.getResponseObject().getCreateAccountId());
            System.out.println("Account create Account Identifier: "
                    + response.getResponseObject().getCreateAccountIdentifier());
            System.out.println("Account create User UUID: " + response.getResponseObject().getCreateUserId());
            System.out.println("Account create User name: "
                    + response.getResponseObject().getCreateUserName());
            System.out.println("Account create date: " + response.getResponseObject().getCreateDate());
            System.out.println("Account last update Account UUID: "
                    + response.getResponseObject().getLastUpdateAccountId());
            System.out.println("Account last update Account Identifier: "
                    + response.getResponseObject().getLastUpdateAccountIdentifier());
            System.out.println("Account last update User UUID: " + response.getResponseObject().getLastUpdateUserId());
            System.out.println("Account last update User name: "
                    + response.getResponseObject().getLastUpdateUserName());
            System.out.println("Account last update date: " + response.getResponseObject().getLastUpdateDate());
        } catch (final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

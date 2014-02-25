package com.discoverydns.dnsapiclient.example.message;

import ch.qos.logback.classic.Level;
import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.message.MessagePollCommand;
import com.discoverydns.dnsapiclient.command.message.MessagePollResponse;
import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.example.ExampleDNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultTransactionLogHandlerConfig;

/**
 * Example of sending a {@link com.discoverydns.dnsapiclient.command.message.MessagePollCommand}
 * and receiving a {@link com.discoverydns.dnsapiclient.command.message.MessagePollResponse}
 * @author Arnaud Dumont
 */
public class MessagePollCommandExample {

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
        final MessagePollCommand command
                = new MessagePollCommand.Builder()
                    .build();

        //Send command to server and receive response
        try {
            Response<MessagePollResponse> response = client.process(command);

            System.out.println("== Successful response ==");
            System.out.println("== Server transaction id: " + response.getServerTransactionId());
            System.out.println("== Client transaction id: " + response.getClientTransactionId());
            System.out.println("== Processing time: " + response.getTransactionProcessingTime() + "ms");

            //The response object can now be used
            if (response.getResponseObject().getMessage() == null) {
                System.out.println("No message found");
            } else {
                System.out.println("Message UUID: " + response.getResponseObject().getMessage().getId());
                System.out.println("Message type: " + response.getResponseObject().getMessage().getMessageType());
                System.out.println("Related object UUID: " + response.getResponseObject().getMessage().getRelatedObjectId());
                System.out.println("Sponsor account UUID: " + response.getResponseObject().getMessage().getSponsorAccountId());
                System.out.println("Message create date: " + response.getResponseObject().getMessage().getCreateDate());
                System.out.println("Message acknowledged date: " + response.getResponseObject().getMessage().getAcknowledgedDate());
                System.out.println("Message acknowledged user id: " + response.getResponseObject().getMessage().getAcknowledgedByUserId());
                System.out.println("Human-readable message: " + response.getResponseObject().getMessage().getMessage());
                System.out.println("Message contents: " + response.getResponseObject().getMessage().getMessageContents());
            }
        } catch (final Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

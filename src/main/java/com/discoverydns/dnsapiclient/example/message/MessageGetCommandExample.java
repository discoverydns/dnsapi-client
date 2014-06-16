package com.discoverydns.dnsapiclient.example.message;

import java.util.Map;

import ch.qos.logback.classic.Level;

import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.message.MessageGetCommand;
import com.discoverydns.dnsapiclient.command.message.MessageGetResponse;
import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.example.ExampleDNSAPIClientConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.example.ExampleDefaultTransactionLogHandlerConfig;

/**
 * Example of sending a
 * {@link com.discoverydns.dnsapiclient.command.message.MessagePollCommand} and
 * receiving a
 * {@link com.discoverydns.dnsapiclient.command.message.MessagePollResponse}
 * 
 * @author Arnaud Dumont
 */
public class MessageGetCommandExample {

	public static void main(String[] args) {
		// Configure logging properties, using Logback
		final ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory
				.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.INFO);

		// Instantiate configuration beans
		final DNSAPIClientConfig config = new ExampleDNSAPIClientConfig();
		final DefaultSSLContextFactoryConfig sslConfig = new ExampleDefaultSSLContextFactoryConfig();
		final DefaultTransactionLogHandlerConfig logConfig = new ExampleDefaultTransactionLogHandlerConfig();

		// Instantiate client instance from DNSAPIClientFactory
		final DNSAPIClientFactory dnsapiClientFactory = new DNSAPIClientFactory();
		DNSAPIClient client = null;
		try {
			client = dnsapiClientFactory.createInstanceFromDefaultProviders(
					config, sslConfig, logConfig);
		} catch (final Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Create command
		final MessageGetCommand command = new MessageGetCommand.Builder()
				.withId("<my-message-id>").build();

		// Send command to server and receive response
		try {
			Response<MessageGetResponse> response = client.process(command);

			System.out.println("== Successful response ==");
			System.out.println("== Server transaction id: "
					+ response.getServerTransactionId());
			System.out.println("== Client transaction id: "
					+ response.getClientTransactionId());
			System.out.println("== Processing time: "
					+ response.getTransactionProcessingTime() + "ms");

			// The response object can now be used
			System.out.println("URI: " + response.getResponseObject().getURI());
			System.out.println("Message UUID: "
					+ response.getResponseObject().getId());
			System.out.println("Message code: "
					+ response.getResponseObject().getMessageCode());
			System.out.println("Target account UUID: "
					+ response.getResponseObject().getTargetAccountId());
			System.out.println("Target account name: "
					+ response.getResponseObject().getTargetAccountName());
			System.out.println("Message enqueue date: "
					+ response.getResponseObject().getEnqueueDate());
			System.out.println("Subject: "
					+ response.getResponseObject().getSubject());
			System.out.println("Message: "
					+ response.getResponseObject().getMessage());
			System.out.println("Parameters:");
			for (Map.Entry<String, String> entry : response.getResponseObject()
					.getParameters().entrySet()) {
				System.out.println("	Key: " + entry.getKey() + " Value: "
						+ entry.getValue());
			}
            if (response.getResponseObject().getAcknowledgeDate() != null) {
                System.out.println("Message acknowledge date: "
                        + response.getResponseObject().getAcknowledgeDate());
            }
            if (response.getResponseObject().getAcknowledgeByUserId() != null) {
                System.out.println("Message acknowledge by user id: "
                        + response.getResponseObject().getAcknowledgeByUserId());
            }
            if (response.getResponseObject().getAcknowledgeByUserName() != null) {
                System.out.println("Message acknowledge by user name: "
                        + response.getResponseObject().getAcknowledgeByUserName());
			}
		} catch (final Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}

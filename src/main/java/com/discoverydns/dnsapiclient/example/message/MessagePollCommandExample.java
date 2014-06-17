package com.discoverydns.dnsapiclient.example.message;

import java.util.Map;

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
 * Example of sending a
 * {@link com.discoverydns.dnsapiclient.command.message.MessagePollCommand} and
 * receiving a
 * {@link com.discoverydns.dnsapiclient.command.message.MessagePollResponse}
 * 
 * @author Arnaud Dumont
 */
public class MessagePollCommandExample {

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
		final MessagePollCommand command = new MessagePollCommand.Builder()
				.build();

		// Send command to server and receive response
		try {
			Response<MessagePollResponse> response = client.process(command);

			System.out.println("== Successful response ==");
			System.out.println("== Server transaction id: "
					+ response.getServerTransactionId());
			System.out.println("== Client transaction id: "
					+ response.getClientTransactionId());
			System.out.println("== Processing time: "
					+ response.getTransactionProcessingTime() + "ms");

			System.out.println("Outstanding Message Count: "
					+ response.getResponseObject()
							.getOutstandingMessageCount());
			// The response object can now be used
			if (response.getResponseObject().getMessageRecord() == null) {
				System.out.println("No message found");
			} else {
				System.out.println("URI: "
						+ response.getResponseObject().getMessageRecord().getUri());
				System.out.println("Message UUID: "
						+ response.getResponseObject().getMessageRecord().getId());
				System.out.println("Message code: "
						+ response.getResponseObject().getMessageRecord()
								.getMessageCode());
				System.out.println("Target account UUID: "
						+ response.getResponseObject().getMessageRecord()
								.getTargetAccountId());
				System.out.println("Message enqueue date: "
						+ response.getResponseObject().getMessageRecord()
								.getEnqueueDate());
				System.out.println("Subject: "
						+ response.getResponseObject().getMessageRecord()
								.getSubject());
				System.out.println("Message: "
						+ response.getResponseObject().getMessageRecord()
								.getMessage());
				System.out.println("Parameters:");
				for (Map.Entry<String, String> entry : response
						.getResponseObject().getMessageRecord().getParameters()
						.entrySet()) {
					System.out.println("	Key: " + entry.getKey() + " Value: "
							+ entry.getValue());
				}
			}
		} catch (final Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}

package com.discoverydns.dnsapiclient.command.message;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the
 * DNSAPI server, to poll for the oldest outstanding Message in the Account's
 * message queue on the server.
 * 
 * A Message is a notification from the DNSAPI server, for the targeted sponsor
 * Account's Users, offering information about changes performed by the server
 * on a related object in the system.
 * 
 * @author Arnaud Dumont
 */
@JsonRootName("MessagePollCommand")
public class MessagePollCommand {

	public static class Builder {

		/**
		 * Builds the {@link MessagePollCommand} from the parameters set on the
		 * {@link Builder}.
		 * 
		 * @return The built {@link MessagePollCommand}
		 */
		public MessagePollCommand build() {
			MessagePollCommand messagePollCommand = new MessagePollCommand();
			return messagePollCommand;
		}
	}

	private MessagePollCommand() {
	}

}

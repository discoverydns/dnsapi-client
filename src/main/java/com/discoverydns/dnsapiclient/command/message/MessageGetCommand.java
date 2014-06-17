package com.discoverydns.dnsapiclient.command.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the
 * DNSAPI server, to get the details of a Message.
 * 
 * A Message is a notification from the DNSAPI server, for the targeted sponsor
 * Account's Users, offering information about changes performed by the server
 * on a related object in the system.
 * 
 * @author Chris Wright
 */
@JsonRootName("MessageGetCommand")
@JsonPropertyOrder({ "id" })
public class MessageGetCommand {

	@JsonProperty("id")
	private String id;

	/**
	 * Builder used to build the desired command.
	 */
	public static class Builder {
		private String id;

		/**
		 * Sets the UUID of the Message to look for.
		 * 
		 * @param id
		 *            The UUID of the Message to look for
		 * @return The {@link Builder}
		 */
		public Builder withId(final String id) {
			this.id = id;
			return this;
		}

		/**
		 * Builds the {@link MessageGetCommand} from the parameters set on the
		 * {@link Builder}.
		 * 
		 * @return The built {@link MessageGetCommand}
		 */
		public MessageGetCommand build() {
			final MessageGetCommand messageGetCommand = new MessageGetCommand();
			messageGetCommand.id = id;
			return messageGetCommand;
		}
	}

	private MessageGetCommand() {
	}

	/**
	 * @return The UUID of the Message to look for, set on the command.
	 */
	public String getId() {
		return id;
	}

}

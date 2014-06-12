package com.discoverydns.dnsapiclient.command.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the
 * DNSAPI server, to get the details of an Account.
 * 
 * An Account represents a company or organisation who has the ability to
 * connect to and interact with the DNSAPI server, through its users.
 * 
 * @author Chris Wright
 */
@JsonRootName("AccountGetCommand")
@JsonPropertyOrder({ "idOrIdentifier" })
public class AccountGetCommand {

	@JsonProperty("idOrIdentifier")
	private String idOrIdentifier;

	/**
	 * Builder used to build the desired command.
	 */
	public static class Builder {
		private String idOrIdentifier;

		/**
		 * Sets the UUID or identifier of the Account to look for.
		 * 
		 * @param idOrIdentifier
		 *            The UUID or identifier of the Account to look for
		 * @return The {@link Builder}
		 */
		public Builder withIdOrIdentifier(final String idOrIdentifier) {
			this.idOrIdentifier = idOrIdentifier;
			return this;
		}

		/**
		 * Builds the {@link AccountGetCommand} from the parameters set on the
		 * {@link Builder}.
		 * 
		 * @return The built {@link AccountGetCommand}
		 */
		public AccountGetCommand build() {
			final AccountGetCommand accountGetCommand = new AccountGetCommand();
			accountGetCommand.idOrIdentifier = idOrIdentifier;
			return accountGetCommand;
		}
	}

	private AccountGetCommand() {
	}

	/**
	 * @return The UUID or identifier of the Account to look for, set on the
	 *         command.
	 */
	public String getIdOrIdentifier() {
		return idOrIdentifier;
	}

}

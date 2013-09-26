package com.discoverydns.dnsapiclient.command.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("accountGetCommand")
@JsonPropertyOrder({ "idOrIdentifier" })
public class AccountGetCommand {

	@JsonProperty("idOrIdentifier")
	private String idOrIdentifier;

	public static class Builder {
		private String idOrIdentifier;

		public Builder withIdOrIdentifier(final String idOrIdentifier) {
			this.idOrIdentifier = idOrIdentifier;
			return this;
		}

		public AccountGetCommand build() {
			final AccountGetCommand accountGetCommand = new AccountGetCommand();
			accountGetCommand.idOrIdentifier = idOrIdentifier;
			return accountGetCommand;
		}
	}

	private AccountGetCommand() {
	}

	public String getIdOrIdentifier() {
		return idOrIdentifier;
	}

}

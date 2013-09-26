package com.discoverydns.dnsapiclient.command.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("userGetCommand")
@JsonPropertyOrder({ "idOrUsername" })
public class UserGetCommand {

	@JsonProperty("idOrUsername")
	private String idOrUsername;

	public static class Builder {
		private String idOrUsername;

		public Builder withIdOrUsername(final String idOrUsername) {
			this.idOrUsername = idOrUsername;
			return this;
		}

		public UserGetCommand build() {
			final UserGetCommand userGetCommand = new UserGetCommand();
			userGetCommand.idOrUsername = idOrUsername;
			return userGetCommand;
		}
	}

	private UserGetCommand() {
	}

	public String getIdOrUsername() {
		return idOrUsername;
	}

}

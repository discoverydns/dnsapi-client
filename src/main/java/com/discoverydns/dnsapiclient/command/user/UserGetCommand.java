package com.discoverydns.dnsapiclient.command.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to get the details of a User.
 *
 * A User is a member of an Account, who can connect to the DNSAPI server
 * to perform operations on his behalf.
 *
 * @author Chris Wright
 */
@JsonRootName("UserGetCommand")
@JsonPropertyOrder({ "idOrUsername" })
public class UserGetCommand {

	@JsonProperty("idOrUsername")
	private String idOrUsername;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		private String idOrUsername;

        /**
         * Sets the UUID or username of the User to look for.
         * @param idOrUsername The UUID or username of the User to look for
         * @return The {@link Builder}
         */
		public Builder withIdOrUsername(final String idOrUsername) {
			this.idOrUsername = idOrUsername;
			return this;
		}

        /**
         * Builds the {@link UserGetCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link UserGetCommand}
         */
		public UserGetCommand build() {
			final UserGetCommand userGetCommand = new UserGetCommand();
			userGetCommand.idOrUsername = idOrUsername;
			return userGetCommand;
		}
	}

	private UserGetCommand() {
	}

    /**
     * @return The UUID or username of the User to look for, set on the command.
     */
	public String getIdOrUsername() {
		return idOrUsername;
	}

}

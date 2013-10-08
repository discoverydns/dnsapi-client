package com.discoverydns.dnsapiclient.command.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to get a list of searched Users.
 *
 * A User is a member of an Account, who can connect to the DNSAPI server
 * to perform operations on his behalf.
 *
 * @author Chris Wright
 */
@JsonRootName("UserListCommand")
@JsonPropertyOrder({ "searchUsername", "searchStatus", "searchName", })
public class UserListCommand {

	@JsonProperty("searchUsername")
	private String searchUsername;
	@JsonProperty("searchStatus")
	private String searchStatus;
	@JsonProperty("searchName")
	private String searchName;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		private String searchUsername;
		private String searchStatus;
		private String searchName;

        /**
         * Sets a part of the username of the Users to look for.
         * @param searchUsername The name to look for
         * @return The {@link Builder}
         */
		public Builder withSearchUsername(final String searchUsername) {
			this.searchUsername = searchUsername;
			return this;
		}

        /**
         * Sets the status of the Users to look for.
         * @param searchStatus The status to look for
         * @return The {@link Builder}
         */
		public Builder withSearchStatus(final String searchStatus) {
			this.searchStatus = searchStatus;
			return this;
		}

        /**
         * Sets a part of the name of the Users to look for.
         * @param searchName The name to look for
         * @return The {@link Builder}
         */
		public Builder withSearchName(final String searchName) {
			this.searchName = searchName;
			return this;
		}

        /**
         * Builds the {@link UserListCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link UserListCommand}
         */
		public UserListCommand build() {
			final UserListCommand userListCommand = new UserListCommand();
			userListCommand.searchName = searchName;
			userListCommand.searchStatus = searchStatus;
			userListCommand.searchUsername = searchUsername;
			return userListCommand;
		}

	}

	private UserListCommand() {
	}

    /**
     * @return The part of the username of the Users to look for, set on the command.
     */
	public String getSearchUsername() {
		return searchUsername;
	}

    /**
     * @return The status of the Users to look for, set on the command.
     */
	public String getSearchStatus() {
		return searchStatus;
	}

    /**
     * @return The part of the name of the Users to look for, set on the command.
     */
	public String getSearchName() {
		return searchName;
	}

}

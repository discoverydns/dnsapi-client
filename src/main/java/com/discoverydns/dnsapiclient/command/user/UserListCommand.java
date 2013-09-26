package com.discoverydns.dnsapiclient.command.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("userListCommand")
@JsonPropertyOrder({ "searchUsername", "searchStatus", "searchName", })
public class UserListCommand {

	@JsonProperty("searchUsername")
	private String searchUsername;
	@JsonProperty("searchStatus")
	private String searchStatus;
	@JsonProperty("searchName")
	private String searchName;

	public static class Builder {
		private String searchUsername;
		private String searchStatus;
		private String searchName;

		public Builder withSearchUsername(final String searchUsername) {
			this.searchUsername = searchUsername;
			return this;
		}

		public Builder withSearchStatus(final String searchStatus) {
			this.searchStatus = searchStatus;
			return this;
		}

		public Builder withSearchName(final String searchName) {
			this.searchName = searchName;
			return this;
		}

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

	public String getSearchUsername() {
		return searchUsername;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public String getSearchName() {
		return searchName;
	}

}

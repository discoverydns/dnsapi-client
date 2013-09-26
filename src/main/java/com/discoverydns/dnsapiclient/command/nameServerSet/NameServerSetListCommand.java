package com.discoverydns.dnsapiclient.command.nameServerSet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("nameServerSetListCommand")
@JsonPropertyOrder({ "searchStatus", "searchName",
		"searchNameServerInterfaceSetId" })
public class NameServerSetListCommand {

	@JsonProperty("searchStatus")
	private String searchStatus;
	@JsonProperty("searchName")
	private String searchName;
	@JsonProperty("searchNameServerInterfaceSetId")
	private String searchNameServerInterfaceSetId;

	public static class Builder {
		private String searchStatus;
		private String searchName;
		private String searchNameServerInterfaceSetId;

		public Builder withSearchStatus(final String searchStatus) {
			this.searchStatus = searchStatus;
			return this;
		}

		public Builder withSearchName(final String searchName) {
			this.searchName = searchName;
			return this;
		}

		public Builder withSearchNameServerInterfaceSetId(
				final String searchNameServerInterfaceSetId) {
			this.searchNameServerInterfaceSetId = searchNameServerInterfaceSetId;
			return this;
		}

		public NameServerSetListCommand build() {
			final NameServerSetListCommand nameServerSetListCommand = new NameServerSetListCommand();
			nameServerSetListCommand.searchName = searchName;
			nameServerSetListCommand.searchStatus = searchStatus;
			nameServerSetListCommand.searchNameServerInterfaceSetId = searchNameServerInterfaceSetId;
			return nameServerSetListCommand;
		}

	}

	private NameServerSetListCommand() {
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public String getSearchName() {
		return searchName;
	}

	public String getSearchNameServerInterfaceSetId() {
		return searchNameServerInterfaceSetId;
	}

}

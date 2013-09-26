package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zoneListCommand")
@JsonPropertyOrder({ "searchNameServerInterfaceSetId", "searchName", })
public class ZoneListCommand {

	@JsonProperty("searchName")
	private String searchName;
	@JsonProperty("searchNameServerInterfaceSetId")
	private String searchNameServerInterfaceSetId;

	public static class Builder {
		private String searchName;
		private String searchNameServerInterfaceSetId;

		public Builder withSearchName(final String searchName) {
			this.searchName = searchName;
			return this;
		}

		public Builder withSearchNameServerInterfaceSetId(
				final String searchNameServerInterfaceSetId) {
			this.searchNameServerInterfaceSetId = searchNameServerInterfaceSetId;
			return this;
		}

		public ZoneListCommand build() {
			final ZoneListCommand zoneListCommand = new ZoneListCommand();
			zoneListCommand.searchName = searchName;
			zoneListCommand.searchNameServerInterfaceSetId = searchNameServerInterfaceSetId;
			return zoneListCommand;
		}

	}

	private ZoneListCommand() {
	}

	public String getSearchName() {
		return searchName;
	}

	public String getSearchNameServerInterfaceSetId() {
		return searchNameServerInterfaceSetId;
	}

}

package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ZoneListCommand")
@JsonPropertyOrder({ "searchName", "searchGroup",
		"searchNameServerInterfaceSetId", "searchBrandedNameServers",
		"searchDNSSECSigned" })
public class ZoneListCommand {

	@JsonProperty("searchName")
	private String searchName;
	@JsonProperty("searchNameServerInterfaceSetId")
	private String searchNameServerInterfaceSetId;
	@JsonProperty("searchGroup")
	private String searchGroup;
	@JsonProperty("searchBrandedNameServers")
	private Boolean searchBrandedNameServers;
	@JsonProperty("searchDNSSECSigned")
	private Boolean searchDNSSECSigned;

	public static class Builder {
		private String searchName;
		private String searchNameServerInterfaceSetId;
		private String searchGroup;
		private Boolean searchBrandedNameServers;
		private Boolean searchDNSSECSigned;

		public Builder withSearchName(final String searchName) {
			this.searchName = searchName;
			return this;
		}

		public Builder withSearchNameServerInterfaceSetId(
				final String searchNameServerInterfaceSetId) {
			this.searchNameServerInterfaceSetId = searchNameServerInterfaceSetId;
			return this;
		}

		public Builder withSearchGroup(final String searchGroup) {
			this.searchGroup = searchGroup;
			return this;
		}

		public Builder withSearchBrandedNameServers(
				final Boolean searchBrandedNameServers) {
			this.searchBrandedNameServers = searchBrandedNameServers;
			return this;
		}

		public Builder withSearchDNSSECSigned(final Boolean searchDNSSECSigned) {
			this.searchDNSSECSigned = searchDNSSECSigned;
			return this;
		}

		public ZoneListCommand build() {
			final ZoneListCommand zoneListCommand = new ZoneListCommand();
			zoneListCommand.searchName = searchName;
			zoneListCommand.searchGroup = searchGroup;
			zoneListCommand.searchNameServerInterfaceSetId = searchNameServerInterfaceSetId;
			zoneListCommand.searchBrandedNameServers = searchBrandedNameServers;
			zoneListCommand.searchDNSSECSigned = searchDNSSECSigned;

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

	public String getSearchGroup() {
		return searchGroup;
	}

	public Boolean getSearchBrandedNameServers() {
		return searchBrandedNameServers;
	}

	public Boolean getSearchDNSSECSigned() {
		return searchDNSSECSigned;
	}

}

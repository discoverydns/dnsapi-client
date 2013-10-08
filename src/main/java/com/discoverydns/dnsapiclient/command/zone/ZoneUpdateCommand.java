package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ZoneUpdateCommand")
@JsonPropertyOrder({ "id", "version", "planId", "group", "nameServerSetId",
		"brandedNameServers" })
public class ZoneUpdateCommand {

	@JsonProperty("id")
	private String id;
	@JsonProperty("version")
	private Long version;
	@JsonProperty("nameServerSetId")
	private String nameServerSetId;
	@JsonProperty("planId")
	private String planId;
	@JsonProperty("group")
	private String group;
	@JsonProperty("brandedNameServers")
	private Boolean brandedNameServers;

	public static class Builder {

		private String id;
		private Long version;
		private String nameServerSetId;
		private String planId;
		private String group;
		private Boolean brandedNameServers;

		public Builder withId(final String id) {
			this.id = id;
			return this;
		}

		public Builder withVersion(final Long version) {
			this.version = version;
			return this;
		}

		public Builder withNameServerSetId(final String nameServerSetId) {
			this.nameServerSetId = nameServerSetId;
			return this;
		}

		public Builder withPlanId(final String planId) {
			this.planId = planId;
			return this;
		}

		public Builder withGroup(final String group) {
			this.group = group;
			return this;
		}

		public Builder withBrandedNameServers(final Boolean brandedNameServers) {
			this.brandedNameServers = brandedNameServers;
			return this;
		}

		public ZoneUpdateCommand build() {
			final ZoneUpdateCommand zoneUpdateCommand = new ZoneUpdateCommand();
			zoneUpdateCommand.id = id;
			zoneUpdateCommand.version = version;
			zoneUpdateCommand.nameServerSetId = nameServerSetId;
			zoneUpdateCommand.planId = planId;
			zoneUpdateCommand.group = group;
			zoneUpdateCommand.brandedNameServers = brandedNameServers;

			return zoneUpdateCommand;
		}

	}

	private ZoneUpdateCommand() {
	}

	public String getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}

	public String getNameServerSetId() {
		return nameServerSetId;
	}

	public Boolean isBrandedNameServers() {
		return brandedNameServers;
	}

	public String getPlanId() {
		return planId;
	}

	public String getGroup() {
		return group;
	}

}

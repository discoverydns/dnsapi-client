package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zoneCreateCommand")
@JsonPropertyOrder({ "id", "planId", "nameServerSetId", "brandedNameServers" })
public class ZoneCreateCommand {

	@JsonProperty("name")
	private String name;
	@JsonProperty("brandedNameServers")
	private Boolean brandedNameServers;
	@JsonProperty("planId")
	private String planId;
	@JsonProperty("nameServerSetId")
	private String nameServerSetId;

	public static class Builder {
		private String name;
		private Boolean brandedNameServers;
		private String planId;
		private String nameServerSetId;

		public Builder withName(final String name) {
			this.name = name;
			return this;
		}

		public Builder withBrandedNameServers(final Boolean brandedNameServers) {
			this.brandedNameServers = brandedNameServers;
			return this;
		}

		public Builder withPlanId(final String planId) {
			this.planId = planId;
			return this;
		}

		public Builder withNameServerSetId(final String nameServerSetId) {
			this.nameServerSetId = nameServerSetId;
			return this;
		}

		public ZoneCreateCommand build() {
			final ZoneCreateCommand zoneCreateCommand = new ZoneCreateCommand();
			zoneCreateCommand.name = name;
			zoneCreateCommand.brandedNameServers = brandedNameServers;
			zoneCreateCommand.planId = planId;
			zoneCreateCommand.nameServerSetId = nameServerSetId;

			return zoneCreateCommand;
		}

	}

	private ZoneCreateCommand() {
	}

	public String getName() {
		return name;
	}

	public Boolean isBrandedNameServers() {
		return brandedNameServers;
	}

	public String getNameServerSetId() {
		return nameServerSetId;
	}

	public String getPlanId() {
		return planId;
	}

}

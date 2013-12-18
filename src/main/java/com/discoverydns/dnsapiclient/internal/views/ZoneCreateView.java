package com.discoverydns.dnsapiclient.internal.views;

import com.discoverydns.dnsapiclient.command.zone.ZoneCreateCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zoneCreate")
public class ZoneCreateView {

	private final ZoneCreateCommand zoneCreateCommand;

	public ZoneCreateView(final ZoneCreateCommand zoneCreateCommand) {
		this.zoneCreateCommand = zoneCreateCommand;
	}

	@JsonProperty("dnssecSigned")
	public Boolean getDnssecSigned() {
		return zoneCreateCommand.isDnssecSigned();
	}

	@JsonProperty("brandedNameServers")
	public Boolean getBrandedNameServers() {
		return zoneCreateCommand.isBrandedNameServers();
	}

	@JsonProperty("nameServerSetId")
	public String getNameServerSetId() {
		return zoneCreateCommand.getNameServerSetId();
	}

	@JsonProperty("name")
	public String getName() {
		return zoneCreateCommand.getName();
	}

	@JsonProperty("planId")
	public String getPlanId() {
		return zoneCreateCommand.getPlanId();
	}

	@JsonProperty("group")
	public String getGroup() {
		return zoneCreateCommand.getGroup();
	}
}

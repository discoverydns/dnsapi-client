package com.discoverydns.dnsapiclient.internal.views;

import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zoneUpdate")
public class ZoneUpdateView {

	private final ZoneUpdateCommand zoneUpdateCommand;

	public ZoneUpdateView(final ZoneUpdateCommand zoneUpdateCommand) {
		this.zoneUpdateCommand = zoneUpdateCommand;
	}

    @JsonProperty("dnssecSigned")
    public Boolean getDnssecSigned() {
        return zoneUpdateCommand.isDnssecSigned();
    }

	@JsonProperty("brandedNameServers")
	public Boolean getBrandedNameServers() {
		return zoneUpdateCommand.isBrandedNameServers();
	}

	@JsonProperty("nameServerSetId")
	public String getNameServerSetId() {
		return zoneUpdateCommand.getNameServerSetId();
	}

	@JsonProperty("version")
	public Long getVersion() {
		return zoneUpdateCommand.getVersion();
	}

	@JsonProperty("planId")
	public String getPlanId() {
		return zoneUpdateCommand.getPlanId();
	}

	@JsonProperty("group")
	public String getGroup() {
		return zoneUpdateCommand.getGroup();
	}

}

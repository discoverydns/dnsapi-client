package com.discoverydns.dnsapiclient.internal.views;

import java.util.Set;

import org.xbill.DNS.Record;

import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateResourceRecordsCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zoneUpdateResourceRecords")
public class ZoneUpdateResourceRecordsView {

	private final ZoneUpdateResourceRecordsCommand zoneUpdateResourceRecordsCommand;

	public ZoneUpdateResourceRecordsView(
			final ZoneUpdateResourceRecordsCommand zoneUpdateResourceRecordsCommand) {
		this.zoneUpdateResourceRecordsCommand = zoneUpdateResourceRecordsCommand;
	}

	@JsonProperty("resourceRecords")
	public Set<Record> getResourceRecords() {
		return zoneUpdateResourceRecordsCommand.getRecords();
	}

	@JsonProperty("version")
	public Long getVersion() {
		return zoneUpdateResourceRecordsCommand.getVersion();
	}

}

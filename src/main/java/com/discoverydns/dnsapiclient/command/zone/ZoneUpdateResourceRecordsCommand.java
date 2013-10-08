package com.discoverydns.dnsapiclient.command.zone;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.xbill.DNS.Record;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ZoneUpdateResourceRecordsCommand")
@JsonPropertyOrder({ "id", "version", "resourceRecords" })
public class ZoneUpdateResourceRecordsCommand {

	@JsonProperty("id")
	public String id;
	@JsonProperty("version")
	private Long version;
	@JsonProperty("resourceRecords")
	public Set<Record> records;

	public static class Builder {
		public String id;
		private Long version;
		public Set<Record> records = new HashSet<Record>();

		public Builder withId(final String id) {
			this.id = id;
			return this;
		}

		public Builder withVersion(final Long version) {
			this.version = version;
			return this;
		}

		public Builder withResourceRecords(final Set<Record> records) {
			if (records != null) {
				this.records = Collections.unmodifiableSet(records);
			}
			return this;
		}

		public ZoneUpdateResourceRecordsCommand build() {
			final ZoneUpdateResourceRecordsCommand zoneUpdateResourceRecordsCommand = new ZoneUpdateResourceRecordsCommand();
			zoneUpdateResourceRecordsCommand.id = id;
			zoneUpdateResourceRecordsCommand.version = version;
			zoneUpdateResourceRecordsCommand.records = records;

			return zoneUpdateResourceRecordsCommand;
		}

	}

	private ZoneUpdateResourceRecordsCommand() {
	}

	public String getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}

	public Set<Record> getRecords() {
		return records;
	}

}

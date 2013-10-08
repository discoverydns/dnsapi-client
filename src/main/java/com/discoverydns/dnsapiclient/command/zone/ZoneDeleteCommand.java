package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ZoneDeleteCommand")
@JsonPropertyOrder({ "id" })
public class ZoneDeleteCommand {

	@JsonProperty("id")
	private String id;

	public static class Builder {
		private String id;

		public Builder withId(final String id) {
			this.id = id;
			return this;
		}

		public ZoneDeleteCommand build() {
			final ZoneDeleteCommand zoneDeleteCommand = new ZoneDeleteCommand();
			zoneDeleteCommand.id = id;
			return zoneDeleteCommand;
		}
	}

	private ZoneDeleteCommand() {
	}

	public String getId() {
		return id;
	}

}

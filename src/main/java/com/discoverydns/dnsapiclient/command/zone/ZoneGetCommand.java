package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zoneGetCommand")
@JsonPropertyOrder({ "id" })
public class ZoneGetCommand {

	@JsonProperty("id")
	private String id;

	public static class Builder {
		private String id;

		public Builder withId(final String id) {
			this.id = id;
			return this;
		}

		public ZoneGetCommand build() {
			final ZoneGetCommand zoneGetCommand = new ZoneGetCommand();
			zoneGetCommand.id = id;
			return zoneGetCommand;
		}
	}

	private ZoneGetCommand() {
	}

	public String getId() {
		return id;
	}

}

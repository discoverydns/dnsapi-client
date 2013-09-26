package com.discoverydns.dnsapiclient.command.nameServerSet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("nameServerSetGetCommand")
@JsonPropertyOrder({ "idOrName" })
public class NameServerSetGetCommand {

	@JsonProperty("idOrName")
	private String idOrName;

	public static class Builder {
		private String idOrName;

		public Builder withIdOrName(final String idOrName) {
			this.idOrName = idOrName;
			return this;
		}

		public NameServerSetGetCommand build() {
			final NameServerSetGetCommand nameServerSetCommand = new NameServerSetGetCommand();
			nameServerSetCommand.idOrName = idOrName;
			return nameServerSetCommand;
		}
	}

	private NameServerSetGetCommand() {
	}

	public String getIdOrName() {
		return idOrName;
	}

}

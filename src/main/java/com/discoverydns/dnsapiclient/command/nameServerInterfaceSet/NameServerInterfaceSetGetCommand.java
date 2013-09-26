package com.discoverydns.dnsapiclient.command.nameServerInterfaceSet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("nameServerInterfaceSetGetCommand")
@JsonPropertyOrder({ "idOrName" })
public class NameServerInterfaceSetGetCommand {

	@JsonProperty("idOrName")
	private String idOrName;

	public static class Builder {
		private String idOrName;

		public Builder withIdOrName(final String idOrName) {
			this.idOrName = idOrName;
			return this;
		}

		public NameServerInterfaceSetGetCommand build() {
			final NameServerInterfaceSetGetCommand nameServerInterfaceSetGetCommand = new NameServerInterfaceSetGetCommand();
			nameServerInterfaceSetGetCommand.idOrName = idOrName;
			return nameServerInterfaceSetGetCommand;
		}
	}

	private NameServerInterfaceSetGetCommand() {
	}

	public String getIdOrName() {
		return idOrName;
	}

}

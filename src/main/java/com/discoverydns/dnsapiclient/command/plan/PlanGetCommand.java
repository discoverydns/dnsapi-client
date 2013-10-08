package com.discoverydns.dnsapiclient.command.plan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PlanGetCommand")
@JsonPropertyOrder({ "idOrName" })
public class PlanGetCommand {

	@JsonProperty("idOrName")
	private String idOrName;

	public static class Builder {
		private String idOrName;

		public Builder withIdOrName(final String idOrName) {
			this.idOrName = idOrName;
			return this;
		}

		public PlanGetCommand build() {
			final PlanGetCommand planGetCommand = new PlanGetCommand();
			planGetCommand.idOrName = idOrName;
			return planGetCommand;
		}
	}

	private PlanGetCommand() {
	}

	public String getIdOrName() {
		return idOrName;
	}

}

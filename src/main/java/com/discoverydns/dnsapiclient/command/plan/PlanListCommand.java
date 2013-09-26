package com.discoverydns.dnsapiclient.command.plan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("planListCommand")
@JsonPropertyOrder({ "searchStatus", "searchName", })
public class PlanListCommand {

	@JsonProperty("searchStatus")
	private String searchStatus;
	@JsonProperty("searchName")
	private String searchName;

	public static class Builder {
		private String searchStatus;
		private String searchName;

		public Builder withSearchStatus(final String searchStatus) {
			this.searchStatus = searchStatus;
			return this;
		}

		public Builder withSearchName(final String searchName) {
			this.searchName = searchName;
			return this;
		}

		public PlanListCommand build() {
			final PlanListCommand planListCommand = new PlanListCommand();
			planListCommand.searchName = searchName;
			planListCommand.searchStatus = searchStatus;
			return planListCommand;
		}

	}

	private PlanListCommand() {
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public String getSearchName() {
		return searchName;
	}

}

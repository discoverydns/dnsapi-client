package com.discoverydns.dnsapiclient.command.plan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to get a list of searched Plans.
 *
 * A Plan describes the available application features granted to an Account,
 * as well as the details and units used for billing this Account.
 *
 * @author Chris Wright
 */
@JsonRootName("PlanListCommand")
@JsonPropertyOrder({ "searchStatus", "searchName", })
public class PlanListCommand {

	@JsonProperty("searchStatus")
	private String searchStatus;
	@JsonProperty("searchName")
	private String searchName;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		private String searchStatus;
		private String searchName;

        /**
         * Sets the status of the Plans to look for.
         * @param searchStatus The status to look for
         * @return The {@link Builder}
         */
		public Builder withSearchStatus(final String searchStatus) {
			this.searchStatus = searchStatus;
			return this;
		}

        /**
         * Sets a part of the name of the Plans to look for.
         * @param searchName The name to look for
         * @return The {@link Builder}
         */
		public Builder withSearchName(final String searchName) {
			this.searchName = searchName;
			return this;
		}

        /**
         * Builds the {@link PlanListCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link PlanListCommand}
         */
		public PlanListCommand build() {
			final PlanListCommand planListCommand = new PlanListCommand();
			planListCommand.searchName = searchName;
			planListCommand.searchStatus = searchStatus;
			return planListCommand;
		}

	}

	private PlanListCommand() {
	}

    /**
     * @return The status of the Plans to look for, set on the command.
     */
	public String getSearchStatus() {
		return searchStatus;
	}

    /**
     * @return The part of the name of the Plans to look for, set on the command.
     */
	public String getSearchName() {
		return searchName;
	}

}

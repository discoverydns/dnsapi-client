package com.discoverydns.dnsapiclient.command.plan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to get the details of a Plan.
 *
 * A Plan describes the available application features granted to an Account,
 * as well as the details and units used for billing this Account.
 *
 * @author Chris Wright
 */
@JsonRootName("PlanGetCommand")
@JsonPropertyOrder({ "idOrName" })
public class PlanGetCommand {

	@JsonProperty("idOrName")
	private String idOrName;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		private String idOrName;

        /**
         * Sets the UUID or name of the Plan to look for.
         * @param idOrName The UUID or name of the Plan to look for
         * @return The {@link Builder}
         */
		public Builder withIdOrName(final String idOrName) {
			this.idOrName = idOrName;
			return this;
		}

        /**
         * Builds the {@link PlanGetCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link PlanGetCommand}
         */
		public PlanGetCommand build() {
			final PlanGetCommand planGetCommand = new PlanGetCommand();
			planGetCommand.idOrName = idOrName;
			return planGetCommand;
		}
	}

	private PlanGetCommand() {
	}

    /**
     * @return The UUID or name of the Plan to look for, set on the command.
     */
	public String getIdOrName() {
		return idOrName;
	}

}

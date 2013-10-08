package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to delete an existing Zone.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Chris Wright
 */
@JsonRootName("ZoneDeleteCommand")
@JsonPropertyOrder({ "id" })
public class ZoneDeleteCommand {

	@JsonProperty("id")
	private String id;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		private String id;

        /**
         * Sets the UUID of the Zone to delete.
         * @param id The UUID of the Zone to delete
         * @return The {@link Builder}
         */
		public Builder withId(final String id) {
			this.id = id;
			return this;
		}

        /**
         * Builds the {@link ZoneDeleteCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link ZoneDeleteCommand}
         */
		public ZoneDeleteCommand build() {
			final ZoneDeleteCommand zoneDeleteCommand = new ZoneDeleteCommand();
			zoneDeleteCommand.id = id;
			return zoneDeleteCommand;
		}
	}

	private ZoneDeleteCommand() {
	}

    /**
     * @return The UUID of the Zone to delete, set on the command
     */
	public String getId() {
		return id;
	}

}

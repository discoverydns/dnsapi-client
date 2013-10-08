package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to get the details of a Zone.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Chris Wright
 */
@JsonRootName("ZoneGetCommand")
@JsonPropertyOrder({ "id" })
public class ZoneGetCommand {

	@JsonProperty("id")
	private String id;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		private String id;

        /**
         * Sets the UUID of the Zone to look for.
         * @param id The UUID of the Zone to look for
         * @return The {@link Builder}
         */
		public Builder withId(final String id) {
			this.id = id;
			return this;
		}

        /**
         * Builds the {@link ZoneGetCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link ZoneGetCommand}
         */
		public ZoneGetCommand build() {
			final ZoneGetCommand zoneGetCommand = new ZoneGetCommand();
			zoneGetCommand.id = id;
			return zoneGetCommand;
		}
	}

	private ZoneGetCommand() {
	}

    /**
     * @return The UUID of the Zone to look for, set on the command.
     */
	public String getId() {
		return id;
	}

}

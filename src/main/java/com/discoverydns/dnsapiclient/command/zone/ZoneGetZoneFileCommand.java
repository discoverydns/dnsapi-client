package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to get the zone file of a Zone.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Arnaud Dumont
 */
@JsonRootName("ZoneGetZoneFileCommand")
@JsonPropertyOrder({ "id" })
public class ZoneGetZoneFileCommand {

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
         * Builds the {@link ZoneGetZoneFileCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link ZoneGetZoneFileCommand}
         */
		public ZoneGetZoneFileCommand build() {
			final ZoneGetZoneFileCommand ZoneGetZoneFileCommand = new ZoneGetZoneFileCommand();
			ZoneGetZoneFileCommand.id = id;
			return ZoneGetZoneFileCommand;
		}
	}

	private ZoneGetZoneFileCommand() {
	}

    /**
     * @return The UUID of the Zone to look for, set on the command.
     */
	public String getId() {
		return id;
	}

}

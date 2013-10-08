package com.discoverydns.dnsapiclient.command.nameServerSet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to get the details of an NameServer Set.
 *
 * A NameServer Set represents a logical entity of the DNSAPI architecture,
 * to link an Account with a NameServer Interface Set,
 * through the configuration of the Name Server names that will be provided to the Account's Zones.
 *
 * @author Chris Wright
 */
@JsonRootName("NameServerSetGetCommand")
@JsonPropertyOrder({ "idOrName" })
public class NameServerSetGetCommand {

	@JsonProperty("idOrName")
	private String idOrName;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		private String idOrName;

        /**
         * Sets the UUID or name of the NameServer Set to look for.
         * @param idOrName The UUID or name of the NameServer Set to look for
         * @return The {@link Builder}
         */
		public Builder withIdOrName(final String idOrName) {
			this.idOrName = idOrName;
			return this;
		}

        /**
         * Builds the {@link NameServerSetGetCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link NameServerSetGetCommand}
         */
		public NameServerSetGetCommand build() {
			final NameServerSetGetCommand nameServerSetCommand = new NameServerSetGetCommand();
			nameServerSetCommand.idOrName = idOrName;
			return nameServerSetCommand;
		}
	}

	private NameServerSetGetCommand() {
	}

    /**
     * @return The UUID or name of the NameServer Set to look for, set on the command.
     */
	public String getIdOrName() {
		return idOrName;
	}

}

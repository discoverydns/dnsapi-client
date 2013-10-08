package com.discoverydns.dnsapiclient.command.nameServerInterfaceSet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to get the details of an NameServer Interface Set.
 *
 * A NameServer Interface Set represents a physical entity of the DNSAPI architecture,
 * capable of answering to DNS queries.
 *
 * @author Chris Wright
 */
@JsonRootName("NameServerInterfaceSetGetCommand")
@JsonPropertyOrder({ "idOrName" })
public class NameServerInterfaceSetGetCommand {

	@JsonProperty("idOrName")
	private String idOrName;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		private String idOrName;

        /**
         * Sets the UUID or name of the NameServer Interface Set to look for.
         * @param idOrName The UUID or name of the NameServer Interface Set to look for
         * @return The {@link Builder}
         */
		public Builder withIdOrName(final String idOrName) {
			this.idOrName = idOrName;
			return this;
		}

        /**
         * Builds the {@link NameServerInterfaceSetGetCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link NameServerInterfaceSetGetCommand}
         */
		public NameServerInterfaceSetGetCommand build() {
			final NameServerInterfaceSetGetCommand nameServerInterfaceSetGetCommand = new NameServerInterfaceSetGetCommand();
			nameServerInterfaceSetGetCommand.idOrName = idOrName;
			return nameServerInterfaceSetGetCommand;
		}
	}

	private NameServerInterfaceSetGetCommand() {
	}

    /**
     * @return The UUID or name of the NameServer Interface Set to look for, set on the command.
     */
	public String getIdOrName() {
		return idOrName;
	}

}

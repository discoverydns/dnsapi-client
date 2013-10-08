package com.discoverydns.dnsapiclient.command.nameServerSet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to get a list of searched NameServer Sets.
 *
 * A NameServer Set represents a logical entity of the DNSAPI architecture,
 * to link an Account with a NameServer Interface Set,
 * through the configuration of the Name Server names that will be provided to the Account's zones.
 *
 * @author Chris Wright
 */
@JsonRootName("NameServerSetListCommand")
@JsonPropertyOrder({ "searchStatus", "searchName",
		"searchNameServerInterfaceSetId" })
public class NameServerSetListCommand {

	@JsonProperty("searchStatus")
	private String searchStatus;
	@JsonProperty("searchName")
	private String searchName;
	@JsonProperty("searchNameServerInterfaceSetId")
	private String searchNameServerInterfaceSetId;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		private String searchStatus;
		private String searchName;
		private String searchNameServerInterfaceSetId;

        /**
         * Sets the status of the NameServer Sets to look for.
         * @param searchStatus The status to look for
         * @return The {@link Builder}
         */
		public Builder withSearchStatus(final String searchStatus) {
			this.searchStatus = searchStatus;
			return this;
		}

        /**
         * Sets a part of the name of the NameServer Sets to look for.
         * @param searchName The part of the name to look for
         * @return The {@link Builder}
         */
		public Builder withSearchName(final String searchName) {
			this.searchName = searchName;
			return this;
		}

        /**
         * Sets the UUID of the NameServer Interface Set that the NameServer Sets to look for are associated with.
         * @param searchNameServerInterfaceSetId The UUID of the associated NameServer Interface Set
         * @return The {@link Builder}
         */
		public Builder withSearchNameServerInterfaceSetId(
				final String searchNameServerInterfaceSetId) {
			this.searchNameServerInterfaceSetId = searchNameServerInterfaceSetId;
			return this;
		}

        /**
         * Builds the {@link NameServerSetListCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link NameServerSetListCommand}
         */
		public NameServerSetListCommand build() {
			final NameServerSetListCommand nameServerSetListCommand = new NameServerSetListCommand();
			nameServerSetListCommand.searchName = searchName;
			nameServerSetListCommand.searchStatus = searchStatus;
			nameServerSetListCommand.searchNameServerInterfaceSetId = searchNameServerInterfaceSetId;
			return nameServerSetListCommand;
		}

	}

	private NameServerSetListCommand() {
	}

    /**
     * @return The status of the NameServer Sets to look for, set on the command.
     */
	public String getSearchStatus() {
		return searchStatus;
	}

    /**
     * @return The part of the name of the NameServer Sets to look for, set on the command.
     */
	public String getSearchName() {
		return searchName;
	}

    /**
     * @return The UUID of the NameServer Interface Set that the NameServer Sets to look for are associated with,
     * set on the command.
     */
	public String getSearchNameServerInterfaceSetId() {
		return searchNameServerInterfaceSetId;
	}

}

package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to create a new Zone.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Chris Wright
 */
@JsonRootName("ZoneCreateCommand")
@JsonPropertyOrder({ "name", "planId", "group", "nameServerSetId",
        "dnssecSigned", "brandedNameServers" })
public class ZoneCreateCommand {

	@JsonProperty("name")
	private String name;
	@JsonProperty("dnssecSigned")
	private Boolean dnssecSigned;
	@JsonProperty("brandedNameServers")
	private Boolean brandedNameServers;
	@JsonProperty("planId")
	private String planId;
	@JsonProperty("group")
	private String group;
	@JsonProperty("nameServerSetId")
	private String nameServerSetId;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		private String name;
		private Boolean dnssecSigned;
		private Boolean brandedNameServers;
		private String planId;
		private String group;
		private String nameServerSetId;

        /**
         * Sets the name of the Zone to create.
         * @param name The name of the Zone to create
         * @return The {@link Builder}
         */
		public Builder withName(final String name) {
			this.name = name;
			return this;
		}

        /**
         * Sets if the Zone to create will be DNSSEC signed.
         * @param dnssecSigned true if the Zone to create should be DNSSEC signed, false otherwise
         * @return The {@link Builder}
         */
        public Builder withDnssecSigned(final Boolean dnssecSigned) {
            this.dnssecSigned = dnssecSigned;
            return this;
        }

        /**
         * Sets if the Zone to create uses branded nameServers.
         * @param brandedNameServers true if the Zone to create should use branded nameServers, false otherwise
         * @return The {@link Builder}
         */
		public Builder withBrandedNameServers(final Boolean brandedNameServers) {
			this.brandedNameServers = brandedNameServers;
			return this;
		}

        /**
         * Sets the UUID of the Plan that the Zone to create should use.
         * @param planId The UUID of the Plan to use
         * @return The {@link Builder}
         */
		public Builder withPlanId(final String planId) {
			this.planId = planId;
			return this;
		}

        /**
         * Sets the group of the Zone to create, if the used Plan has the "grouping" feature. Leave 'null' otherwise.
         * @param group The group of the Zone to create
         * @return The {@link Builder}
         */
		public Builder withGroup(final String group) {
			this.group = group;
			return this;
		}

        /**
         * Sets the UUID of the NameServer Set that the Zone to create should be associated with.
         * @param nameServerSetId The UUID of the NameServer Set to associate the Zone with
         * @return The {@link Builder}
         */
		public Builder withNameServerSetId(final String nameServerSetId) {
			this.nameServerSetId = nameServerSetId;
			return this;
		}

        /**
         * Builds the {@link ZoneCreateCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link ZoneCreateCommand}
         */
		public ZoneCreateCommand build() {
			final ZoneCreateCommand zoneCreateCommand = new ZoneCreateCommand();
			zoneCreateCommand.name = name;
			zoneCreateCommand.dnssecSigned = dnssecSigned;
			zoneCreateCommand.brandedNameServers = brandedNameServers;
			zoneCreateCommand.planId = planId;
			zoneCreateCommand.group = group;
			zoneCreateCommand.nameServerSetId = nameServerSetId;

			return zoneCreateCommand;
		}

	}

	private ZoneCreateCommand() {
	}

    /**
     * @return The name of the Zone to create, set on the command.
     */
	public String getName() {
		return name;
	}

    /**
     * @return true if the Zone to create should be DNSSEC signed, false otherwise
     */
    public Boolean isDnssecSigned() {
        return dnssecSigned;
    }

    /**
     * @return true if the Zone to create should use branded nameServers, false otherwise, set on the command.
     */
	public Boolean isBrandedNameServers() {
		return brandedNameServers;
	}

    /**
     * @return The UUID of the NameServer Set to associate the Zone with, set on the command.
     */
	public String getNameServerSetId() {
		return nameServerSetId;
	}

    /**
     * @return The UUID of the Plan to use, set on the command.
     */
	public String getPlanId() {
		return planId;
	}

    /**
     * @return The group of the Zone to create, set on the command, if the used Plan has the "grouping" feature. 'null' otherwise.
     */
	public String getGroup() {
		return group;
	}

}

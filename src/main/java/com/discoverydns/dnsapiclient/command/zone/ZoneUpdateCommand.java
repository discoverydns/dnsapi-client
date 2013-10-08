package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to update an existing Zone.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Chris Wright
 */
@JsonRootName("ZoneUpdateCommand")
@JsonPropertyOrder({ "id", "version", "planId", "group", "nameServerSetId",
		"brandedNameServers" })
public class ZoneUpdateCommand {

	@JsonProperty("id")
	private String id;
	@JsonProperty("version")
	private Long version;
	@JsonProperty("nameServerSetId")
	private String nameServerSetId;
	@JsonProperty("planId")
	private String planId;
	@JsonProperty("group")
	private String group;
	@JsonProperty("brandedNameServers")
	private Boolean brandedNameServers;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {

		private String id;
		private Long version;
		private String nameServerSetId;
		private String planId;
		private String group;
		private Boolean brandedNameServers;

        /**
         * Sets the UUID of the Zone to update.
         * @param id The UUID of the Zone to update
         * @return The {@link Builder}
         */
		public Builder withId(final String id) {
			this.id = id;
			return this;
		}

        /**
         * Sets the modification version of the Zone to update.
         * If it's not the latest one, an error will be thrown.
         * @param version The modification version of the Zone to update
         * @return The {@link Builder}
         */
		public Builder withVersion(final Long version) {
			this.version = version;
			return this;
		}

        /**
         * Sets the UUID of the NameServer Set that the Zone to update should be associated with.
         * The NameServer Set should belong to the same underlying NameServer Interface Set
         * the Zone's previous NameServerSet was using.
         * @param nameServerSetId The UUID of the NameServer Set to update the Zone with
         * @return The {@link Builder}
         */
		public Builder withNameServerSetId(final String nameServerSetId) {
			this.nameServerSetId = nameServerSetId;
			return this;
		}

        /**
         * Sets the UUID of the Plan that the Zone to update should use.
         * @param planId The UUID of the Plan to use
         * @return The {@link Builder}
         */
		public Builder withPlanId(final String planId) {
			this.planId = planId;
			return this;
		}

        /**
         * Sets the group of the Zone to update, if the used Plan has the "grouping" feature. Leave 'null' otherwise.
         * @param group The group of the Zone to update
         * @return The {@link Builder}
         */
		public Builder withGroup(final String group) {
			this.group = group;
			return this;
		}

        /**
         * Sets if the Zone to update uses branded nameServers.
         * @param brandedNameServers true if the Zone to update should use branded nameServers, false otherwise
         * @return The {@link Builder}
         */
		public Builder withBrandedNameServers(final Boolean brandedNameServers) {
			this.brandedNameServers = brandedNameServers;
			return this;
		}

        /**
         * Builds the {@link ZoneUpdateCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link ZoneUpdateCommand}
         */
		public ZoneUpdateCommand build() {
			final ZoneUpdateCommand zoneUpdateCommand = new ZoneUpdateCommand();
			zoneUpdateCommand.id = id;
			zoneUpdateCommand.version = version;
			zoneUpdateCommand.nameServerSetId = nameServerSetId;
			zoneUpdateCommand.planId = planId;
			zoneUpdateCommand.group = group;
			zoneUpdateCommand.brandedNameServers = brandedNameServers;

			return zoneUpdateCommand;
		}

	}

	private ZoneUpdateCommand() {
	}

    /**
     * @return The UUID of the Zone to update, set on the command
     */
	public String getId() {
		return id;
	}

    /**
     * @return The modification version of the Zone to update, set on the command
     */
	public Long getVersion() {
		return version;
	}

    /**
     * @return The UUID of the NameServer Set to update the Zone with, set on the command
     */
	public String getNameServerSetId() {
		return nameServerSetId;
	}

    /**
     * @return true if the Zone to update should use branded nameServers, false otherwise
     */
	public Boolean isBrandedNameServers() {
		return brandedNameServers;
	}

    /**
     * @return The UUID of the Plan to update the Zone with, set on the command
     */
	public String getPlanId() {
		return planId;
	}

    /**
     * @return The group of the Zone to update, if the used Plan has the "grouping" feature. 'null' otherwise.
     */
	public String getGroup() {
		return group;
	}

}

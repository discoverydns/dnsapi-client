package com.discoverydns.dnsapiclient.command.zone;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.xbill.DNS.Record;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to update the resource records an existing Zone.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Chris Wright
 */
@JsonRootName("ZoneUpdateResourceRecordsCommand")
@JsonPropertyOrder({ "id", "version", "resourceRecords" })
public class ZoneUpdateResourceRecordsCommand {

	@JsonProperty("id")
	public String id;
	@JsonProperty("version")
	private Long version;
	@JsonProperty("resourceRecords")
	public Set<Record> records;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		public String id;
		private Long version;
		public Set<Record> records = new HashSet<Record>();

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
         * Sets the full list of user resource records of the Zone to update.
         * The operation is a 'replace', not an 'update'.
         * @param records The full list of user resource records to set
         * @return The {@link Builder}
         */
		public Builder withResourceRecords(final Set<Record> records) {
			if (records != null) {
				this.records = Collections.unmodifiableSet(records);
			}
			return this;
		}

        /**
         * Builds the {@link ZoneUpdateResourceRecordsCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link ZoneUpdateResourceRecordsCommand}
         */
		public ZoneUpdateResourceRecordsCommand build() {
			final ZoneUpdateResourceRecordsCommand zoneUpdateResourceRecordsCommand = new ZoneUpdateResourceRecordsCommand();
			zoneUpdateResourceRecordsCommand.id = id;
			zoneUpdateResourceRecordsCommand.version = version;
			zoneUpdateResourceRecordsCommand.records = records;

			return zoneUpdateResourceRecordsCommand;
		}

	}

	private ZoneUpdateResourceRecordsCommand() {
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
     * @return The full list of user resource records to set
     */
	public Set<Record> getRecords() {
		return records;
	}

}

package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.joda.time.LocalDateTime;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to get the details of the query usage of a Zone.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Arnaud Dumont
 */
@JsonRootName("ZoneGetQueryUsageCommand")
@JsonPropertyOrder({ "id", "searchStartDate", "searchEndDate", "searchGranularity" })
public class ZoneGetQueryUsageCommand {

	@JsonProperty("id")
	private String id;

    @JsonProperty("searchStartDate")
    private LocalDateTime searchStartDate;

    @JsonProperty("searchEndDate")
    private LocalDateTime searchEndDate;

    @JsonProperty("searchGranularity")
    private ZoneQueryUsageGranularity searchGranularity;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		private String id;
        private LocalDateTime searchStartDate;
        private LocalDateTime searchEndDate;
        private ZoneQueryUsageGranularity searchGranularity;

        /**
         * Sets the UUID of the Zone to retrieve the query usage for.
         * @param id The UUID of the Zone to retrieve the query usage for
         * @return The {@link Builder}
         */
		public Builder withId(final String id) {
			this.id = id;
			return this;
		}

        /**
         * Sets the date after which to get the query usage records.
         * @param searchStartDate The date after which to get the query usage records
         * @return The {@link Builder}
         */
        public Builder withSearchStartDate(LocalDateTime searchStartDate) {
           this.searchStartDate = searchStartDate;
           return this;
        }

        /**
         * Sets the date before which to get the query usage records.
         * @param searchEndDate The date before which to get the query usage records
         * @return The {@link Builder}
         */
        public Builder withSearchEndDate(LocalDateTime searchEndDate) {
            this.searchEndDate = searchEndDate;
            return this;
        }

        /**
         * Sets the search granularity (hourly, daily or monthly).
         * @param searchGranularity The search granularity (hourly, daily or monthly)
         * @return The {@link Builder}
         */
        public Builder withSearchGranularity(ZoneQueryUsageGranularity searchGranularity) {
            this.searchGranularity = searchGranularity;
            return this;
        }

        /**
         * Builds the {@link ZoneGetQueryUsageCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link ZoneGetQueryUsageCommand}
         */
		public ZoneGetQueryUsageCommand build() {
			final ZoneGetQueryUsageCommand zoneGetCommand = new ZoneGetQueryUsageCommand();
			zoneGetCommand.id = id;
			zoneGetCommand.searchStartDate = searchStartDate;
			zoneGetCommand.searchEndDate = searchEndDate;
			zoneGetCommand.searchGranularity = searchGranularity;
			return zoneGetCommand;
		}
	}

	private ZoneGetQueryUsageCommand() {
	}

    /**
     * @return The UUID of the Zone to look for, set on the command.
     */
	public String getId() {
		return id;
	}

    /**
     * @return The date after which to get the query usage records, set on the command.
     */
    public LocalDateTime getSearchStartDate() {
        return searchStartDate;
    }

    /**
     * @return The date before which to get the query usage records, set on the command.
     */
    public LocalDateTime getSearchEndDate() {
        return searchEndDate;
    }

    /**
     * @return The search granularity (hourly, daily or monthly), set on the command.
     */
    public ZoneQueryUsageGranularity getSearchGranularity() {
        return searchGranularity;
    }
}

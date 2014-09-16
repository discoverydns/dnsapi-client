package com.discoverydns.dnsapiclient.command.zone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to create a new AXFR-managed Zone.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Arnaud Dumont
 */
@JsonRootName("ZoneCreateCommand")
@JsonPropertyOrder({ "name", "planId", "group", "nameServerSetId",
        "axfrServers", "tsigKey", "tsigName", "tsigAlgorithm" })
public class ZoneCreateAXFRCommand {

	@JsonProperty("name")
	private String name;
	@JsonProperty("planId")
	private String planId;
	@JsonProperty("group")
	private String group;
	@JsonProperty("nameServerSetId")
	private String nameServerSetId;
	@JsonProperty("axfrServers")
    private List<String> axfrServers;
	@JsonProperty("tsigKey")
    private String tsigKey;
	@JsonProperty("tsigName")
    private String tsigName;
	@JsonProperty("tsigAlgorithm")
    private String tsigAlgorithm;

    /**
     * Builder used to build the desired command.
     */
	public static class Builder {
		private String name;
		private String planId;
		private String group;
		private String nameServerSetId;
        private List<String> axfrServers = new ArrayList<String>();
        private String tsigKey;
        private String tsigName;
        private String tsigAlgorithm;

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
         * Sets the list of servers from where the zone records will be updated by AXFR.
         * @param axfrServers The AXFR servers
         * @return The {@link Builder}
         */
        public Builder withAXFRServers(final List<String> axfrServers) {
            if (axfrServers != null) {
                this.axfrServers = Collections.unmodifiableList(axfrServers);
            }
            return this;
        }

        /**
         * Sets the TSIG key to validate the AXFR operation.
         * @param tsigKey The TSIG key
         * @return The {@link Builder}
         */
        public Builder withTsigKey(final String tsigKey) {
            this.tsigKey = tsigKey;
            return this;
        }

        /**
         * Sets the name of the TSIG key to validate the AXFR operation.
         * @param tsigName The TSIG key name
         * @return The {@link Builder}
         */
        public Builder withTsigName(final String tsigName) {
            this.tsigName = tsigName;
            return this;
        }

        /**
         * Sets the algorithm of the TSIG key to validate the AXFR operation.
         * @param tsigAlgorithm The TSIG key algorithm
         * @return The {@link Builder}
         */
        public Builder withTsigAlgorithm(final String tsigAlgorithm) {
            this.tsigAlgorithm = tsigAlgorithm;
            return this;
        }

        /**
         * Builds the {@link com.discoverydns.dnsapiclient.command.zone.ZoneCreateAXFRCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link com.discoverydns.dnsapiclient.command.zone.ZoneCreateAXFRCommand}
         */
		public ZoneCreateAXFRCommand build() {
			final ZoneCreateAXFRCommand zoneCreateCommand = new ZoneCreateAXFRCommand();
			zoneCreateCommand.name = name;
			zoneCreateCommand.planId = planId;
			zoneCreateCommand.group = group;
			zoneCreateCommand.nameServerSetId = nameServerSetId;
            zoneCreateCommand.axfrServers = axfrServers;
            zoneCreateCommand.tsigKey = tsigKey;
            zoneCreateCommand.tsigName = tsigName;
            zoneCreateCommand.tsigAlgorithm = tsigAlgorithm;

			return zoneCreateCommand;
		}

	}

	private ZoneCreateAXFRCommand() {
	}

    /**
     * @return The name of the Zone to create, set on the command.
     */
	public String getName() {
		return name;
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

    /**
     * @return The list of servers from where the zone records will be updated by AXFR.
     */
    public List<String> getAxfrServers() {
        return axfrServers;
    }

    /**
     * @return The TSIG key to validate the AXFR operation.
     */
    public String getTsigKey() {
        return tsigKey;
    }

    /**
     * @return The name of the TSIG key to validate the AXFR operation.
     */
    public String getTsigName() {
        return tsigName;
    }

    /**
     * @return The algorithm of the TSIG key to validate the AXFR operation.
     */
    public String getTsigAlgorithm() {
        return tsigAlgorithm;
    }
}

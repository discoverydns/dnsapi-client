package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to update the plan of a group of existing Zones.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Arnaud Dumont
 */
@JsonRootName("ZoneUpdateGroupPlanCommand")
@JsonPropertyOrder({ "group" , "planId"})
public class ZoneUpdateGroupPlanCommand {
    @JsonProperty("group")
    private String group;
    @JsonProperty("planId")
    private String planId;

    /**
     * Builder used to build the desired command.
     */
    public static class Builder {
        private String group;
        private String planId;

        /**
         * The name of the group of Zones to update.
         * @param group The name of the group of Zones to update
         * @return The {@link Builder}
         */
        public Builder withGroup(final String group) {
            this.group = group;
            return this;
        }

        /**
         * Sets the UUID of the Plan that the group of Zones to update should use.
         * @param planId The UUID of the Plan to use
         * @return The {@link Builder}
         */
        public Builder withPlanId(final String planId) {
            this.planId = planId;
            return this;
        }

        /**
         * Builds the {@link ZoneUpdateGroupPlanCommand} from the parameters set on the {@link Builder}.
         * @return The built {@link ZoneUpdateGroupPlanCommand}
         */
        public ZoneUpdateGroupPlanCommand build() {
            final ZoneUpdateGroupPlanCommand zoneUpdateGroupPlanCommand = new ZoneUpdateGroupPlanCommand();

            zoneUpdateGroupPlanCommand.group = group;
            zoneUpdateGroupPlanCommand.planId = planId;

            return zoneUpdateGroupPlanCommand;
        }
    }

    private ZoneUpdateGroupPlanCommand() {
    }

    /**
     * @return The name of the group of Zones to update.
     */
    public String getGroup() {
        return group;
    }

    /**
     * @return The UUID of the Plan to update the group of Zones with, set on the command
     */
    public String getPlanId() {
        return planId;
    }
}

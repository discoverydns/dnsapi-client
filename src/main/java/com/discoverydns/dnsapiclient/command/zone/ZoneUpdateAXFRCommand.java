package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Command sent from a {@link com.discoverydns.dnsapiclient.DNSAPIClient} to the DNSAPI server,
 * to update AXFR details for an existing Zone.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Bin Chen
 */
@JsonRootName("ZoneCreateCommand")
@JsonPropertyOrder({ "id", "version", "tsigKey", "tsigName", "tsigAlgorithm", "axfrServers" })
public class ZoneUpdateAXFRCommand {

    @JsonProperty("id")
    private String id;
    @JsonProperty("version")
    private Long version;
    @JsonProperty("tsigKey")
    private String tsigKey;
    @JsonProperty("tsigName")
    private String tsigName;
    @JsonProperty("tsigAlgorithm")
    private String tsigAlgorithm;
    @JsonProperty("axfrServers")
    private List<String> axfrServers;

    /**
     * @return The UUID of the Zone to update, set on the command
     */
    public String getId() {
        return id;
    }

    /**
     * @return The version of the Zone to update, set on the command
     */
    public Long getVersion() {
        return version;
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
     * @return The algorithm of the TSIG key to validate the AXFR operation, e.g. "hmac-md5".
     */
    public String getTsigAlgorithm() {
        return tsigAlgorithm;
    }

    /**
     * @return The list of servers I.P. addresses from where the zone records will be updated by AXFR.
     */
    public List<String> getAxfrServers() {
        return axfrServers;
    }

    /**
     * Builder used to build the desired command.
     */
    public static final class Builder {
        private String id;
        private Long version;
        private String tsigKey;
        private String tsigName;
        private String tsigAlgorithm;
        private List<String> axfrServers = new ArrayList<String>();

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
         * Sets the TSIG key to validate the AXFR operation.
         *
         * @param tsigKey The TSIG key
         * @return The {@link Builder}
         */
        public Builder withTsigKey(final String tsigKey) {
            this.tsigKey = tsigKey;
            return this;
        }

        /**
         * Sets the name of the TSIG key to validate the AXFR operation.
         *
         * @param tsigName The TSIG key name
         * @return The {@link Builder}
         */
        public Builder withTsigName(final String tsigName) {
            this.tsigName = tsigName;
            return this;
        }

        /**
         * Sets the algorithm of the TSIG key to validate the AXFR operation.
         *
         * @param tsigAlgorithm The TSIG key algorithm
         * @return The {@link Builder}
         */
        public Builder withTsigAlgorithm(final String tsigAlgorithm) {
            this.tsigAlgorithm = tsigAlgorithm;
            return this;
        }

        /**
         * Sets the list of servers I.P. addresses from where the zone records will be updated by AXFR.
         *
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
         * Builds the {@link com.discoverydns.dnsapiclient.command.zone.ZoneUpdateAXFRCommand} from the parameters
         * set on the {@link Builder}.
         *
         * @return The built {@link com.discoverydns.dnsapiclient.command.zone.ZoneUpdateAXFRCommand}
         */
        public ZoneUpdateAXFRCommand build() {
            ZoneUpdateAXFRCommand zoneUpdateAXFRCommand = new ZoneUpdateAXFRCommand();
            zoneUpdateAXFRCommand.id = id;
            zoneUpdateAXFRCommand.version = version;
            zoneUpdateAXFRCommand.tsigName = tsigName;
            zoneUpdateAXFRCommand.tsigKey = tsigKey;
            zoneUpdateAXFRCommand.tsigAlgorithm = tsigAlgorithm;
            zoneUpdateAXFRCommand.axfrServers = axfrServers;
            return zoneUpdateAXFRCommand;
        }
    }
}

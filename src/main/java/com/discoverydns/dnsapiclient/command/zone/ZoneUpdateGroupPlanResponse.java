package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link com.discoverydns.dnsapiclient.command.zone.ZoneUpdateGroupPlanCommand},
 * describing the result of the update of the group of Zones.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Arnaud Dumont
 */
@JsonRootName("ZoneUpdateGroupPlanResponse")
public class ZoneUpdateGroupPlanResponse {

}

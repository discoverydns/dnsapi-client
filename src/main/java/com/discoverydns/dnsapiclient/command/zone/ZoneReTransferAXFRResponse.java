package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link ZoneReTransferAXFRCommand},
 * describing the result of the Zone refresh.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 */
@JsonRootName("ZoneRefreshResponse")
public class ZoneReTransferAXFRResponse {

}

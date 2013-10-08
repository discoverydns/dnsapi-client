package com.discoverydns.dnsapiclient.command.zone;

import com.discoverydns.dnsapiclient.internal.views.ZoneGetView;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link ZoneUpdateResourceRecordsCommand},
 * describing the result of the Zone resource records update.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Chris Wright
 */
@JsonRootName("ZoneUpdateResourceRecordsResponse")
public class ZoneUpdateResourceRecordsResponse extends ZoneGetResponse {

	public ZoneUpdateResourceRecordsResponse(final ZoneGetView zoneGetView) {
		super(zoneGetView);
	}

}

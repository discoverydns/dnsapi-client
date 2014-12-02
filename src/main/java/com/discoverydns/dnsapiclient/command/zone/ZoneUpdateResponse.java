package com.discoverydns.dnsapiclient.command.zone;

import com.discoverydns.dnsapiclient.internal.views.response.ZoneGetView;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link ZoneUpdateCommand},
 * describing the result of the Zone update.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Chris Wright
 */
@JsonRootName("ZoneUpdateResponse")
public class ZoneUpdateResponse extends ZoneGetResponse {

	public ZoneUpdateResponse(final ZoneGetView zoneGetView) {
		super(zoneGetView);
	}

}

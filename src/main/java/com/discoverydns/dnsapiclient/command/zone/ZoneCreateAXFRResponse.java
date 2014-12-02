package com.discoverydns.dnsapiclient.command.zone;

import com.discoverydns.dnsapiclient.internal.views.response.ZoneGetView;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link com.discoverydns.dnsapiclient.command.zone.ZoneCreateAXFRCommand},
 * describing the result of the Zone creation.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Arnaud Dumont
 */
@JsonRootName("ZoneCreateResponse")
public class ZoneCreateAXFRResponse extends ZoneGetResponse {

	public ZoneCreateAXFRResponse(final ZoneGetView zoneGetView) {
		super(zoneGetView);
	}

}

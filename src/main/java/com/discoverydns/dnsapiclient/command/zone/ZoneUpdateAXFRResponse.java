package com.discoverydns.dnsapiclient.command.zone;

import com.discoverydns.dnsapiclient.internal.views.response.ZoneGetView;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link ZoneUpdateAXFRCommand},
 * describing the result of the Zone update.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Bin Chen
 */
public class ZoneUpdateAXFRResponse extends ZoneGetResponse {

    public ZoneUpdateAXFRResponse(final ZoneGetView zoneGetView) {
        super(zoneGetView);
    }
}

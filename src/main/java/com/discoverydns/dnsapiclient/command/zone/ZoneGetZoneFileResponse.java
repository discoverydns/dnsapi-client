package com.discoverydns.dnsapiclient.command.zone;

import com.discoverydns.dnsapiclient.internal.views.ZoneGetZoneFileView;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link ZoneGetZoneFileCommand},
 * containing the zone file of the specified Zone.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Arnaud Dumont
 */
public class ZoneGetZoneFileResponse {
	private final ZoneGetZoneFileView zoneGetZoneFileView;

	public ZoneGetZoneFileResponse(ZoneGetZoneFileView zoneGetZoneFileView) {
		super();
		this.zoneGetZoneFileView = zoneGetZoneFileView;
	}
	
	public byte[] getZoneFile() {
		return zoneGetZoneFileView.getZoneFile();
	}
}

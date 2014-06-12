package com.discoverydns.dnsapiclient.command.zone;


/**
 * Response object received by the
 * {@link com.discoverydns.dnsapiclient.DNSAPIClient} from the DNSAPI server
 * subsequently to the sending of a {@link ZoneGetZoneFileCommand}, containing
 * the zone file of the specified Zone.
 * 
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 * 
 * @author Arnaud Dumont
 */
public class ZoneGetZoneFileResponse {

	private final byte[] zoneFile;

	public ZoneGetZoneFileResponse(byte[] zoneFile) {
		this.zoneFile = zoneFile;
	}

	public byte[] getZoneFile() {
		return zoneFile;
	}

}

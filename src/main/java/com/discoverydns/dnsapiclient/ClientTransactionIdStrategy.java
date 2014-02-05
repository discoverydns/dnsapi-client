package com.discoverydns.dnsapiclient;

/**
 * Bean, used when a command is sent from a {@link DNSAPIClient} to the DNSAPI server,
 * to generate a client transaction id to be put in the meta-data.
 * @author Chris Wright
 * @see com.discoverydns.dnsapiclient.DefaultClientTransactionIdStrategy
 */
public interface ClientTransactionIdStrategy {

    /**
     * Method called when a command is sent from a {@link DNSAPIClient} to the DNSAPI server,
     * to generate the client transaction id to be put in the command meta-data.
     * @return The generated unique client transaction id
     */
	public String generateTransactionId();

}

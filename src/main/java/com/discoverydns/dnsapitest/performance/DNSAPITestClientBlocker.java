package com.discoverydns.dnsapitest.performance;

public class DNSAPITestClientBlocker {

	static public DNSAPITestClientBlocker getInstance() {
		return dnsapiTestClientBlocker;
	}

	static private DNSAPITestClientBlocker dnsapiTestClientBlocker = new DNSAPITestClientBlocker();

	public synchronized void block() {
		try {
			dnsapiTestClientBlocker.wait();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void unblockAll() {
		dnsapiTestClientBlocker.notifyAll();
	}

}

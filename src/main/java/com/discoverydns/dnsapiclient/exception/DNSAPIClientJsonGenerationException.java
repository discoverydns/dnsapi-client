package com.discoverydns.dnsapiclient.exception;

public class DNSAPIClientJsonGenerationException extends DNSAPIClientException {

	private static final long serialVersionUID = 3792775686625733076L;

	public enum DNSAPIClientJsonGenerationExceptionCode {
		unexpectedGenerationError
	}

	public DNSAPIClientJsonGenerationException(
			final DNSAPIClientJsonGenerationExceptionCode exceptionCode,
			final Throwable cause, final Object... objects) {
		super(exceptionCode, cause, objects);
	}

	public DNSAPIClientJsonGenerationException(
			final DNSAPIClientJsonGenerationExceptionCode exceptionCode,
			final Object... objects) {
		super(exceptionCode, objects);
	}

}

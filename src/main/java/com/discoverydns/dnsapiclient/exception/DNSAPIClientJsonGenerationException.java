package com.discoverydns.dnsapiclient.exception;

/**
 * A DNSAPIClient exception that has occurred during JSON serialization.
 *
 * @author Chris Wright
 */
public class DNSAPIClientJsonGenerationException extends DNSAPIClientException {

	private static final long serialVersionUID = 3792775686625733076L;

	public enum DNSAPIClientJsonGenerationExceptionCode {
        /**
         * Thrown when an unexpected error occurs during JSON serialization
         */
        unexpectedGenerationError,
        /**
         * Thrown when an unexpected error occurs during JSON serialization of resource records
         */
        unexpectedResourceRecordGenerationError
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

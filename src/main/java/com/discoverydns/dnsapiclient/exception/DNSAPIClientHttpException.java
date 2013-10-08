package com.discoverydns.dnsapiclient.exception;

/**
 * A DNSAPIClient exception that has occurred during client-server HTTP communication.
 *
 * @author Chris Wright
 */
public class DNSAPIClientHttpException extends DNSAPIClientException {

	private static final long serialVersionUID = 3792775686625733076L;

	public enum DNSAPIClientHttpExceptionCode {
        /**
         * Thrown when the HTTP status code received from the DNSAPI server is not the expected one
         */
		serverError
	}

	private final int statusCode;

	public DNSAPIClientHttpException(
			final DNSAPIClientHttpExceptionCode exceptionCode,
			final int statusCode, final Throwable cause,
			final Object... objects) {
		super(exceptionCode, cause, objects);
		this.statusCode = statusCode;
	}

	public DNSAPIClientHttpException(
			final DNSAPIClientHttpExceptionCode exceptionCode,
			final int statusCode, final Object... objects) {
		super(exceptionCode, objects);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

}

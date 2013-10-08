package com.discoverydns.dnsapiclient.exception;

import com.discoverydns.dnsapiclient.framework.command.BaseException;

/**
 * A generic DNSAPIClient exception.
 *
 * @author Chris Wright
 */
public class DNSAPIClientException extends BaseException {

	private static final long serialVersionUID = -2062084248122622976L;

	public enum DNSAPIClientExceptionCode {
        /**
         * Sent if the response from the DNSAPI server does not contain any entity object
         */
		expectedEntity,
        /**
         * Sent when trying to use an already closed {@link com.discoverydns.dnsapiclient.DNSAPIClient}
         */
        clientClosed,
        /**
         * Sent when a required parameter is missing when building the target URI
         */
        requiredParameterMissing
	}

	public DNSAPIClientException(final Enum<?> exceptionCode,
			final Object[] objects) {
		super(exceptionCode, objects);
	}

	public DNSAPIClientException(final DNSAPIClientExceptionCode exceptionCode,
			final Object... objects) {
		super(exceptionCode, objects);
	}

	public DNSAPIClientException(final Enum<?> exceptionCode,
			final Throwable cause, final Object[] objects) {
		super(exceptionCode, cause, objects);
	}

	public DNSAPIClientException(final DNSAPIClientExceptionCode exceptionCode,
			final Throwable cause, final Object... objects) {
		super(exceptionCode, cause, objects);
	}

}

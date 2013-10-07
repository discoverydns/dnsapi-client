package com.discoverydns.dnsapiclient.exception;

import com.discoverydns.dnsapiclient.framework.command.BaseException;

public class DNSAPIClientException extends BaseException {

	private static final long serialVersionUID = -2062084248122622976L;

	public enum DNSAPIClientExceptionCode {
		expectedEntity, clientClosed, requiredParameterMissing

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

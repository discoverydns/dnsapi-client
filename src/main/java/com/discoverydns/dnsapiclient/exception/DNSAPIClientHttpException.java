package com.discoverydns.dnsapiclient.exception;

public class DNSAPIClientHttpException extends DNSAPIClientException {

	private static final long serialVersionUID = 3792775686625733076L;

	public enum DNSAPIClientHttpExceptionCode {
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

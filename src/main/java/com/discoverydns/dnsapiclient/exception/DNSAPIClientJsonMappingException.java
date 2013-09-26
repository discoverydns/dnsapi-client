package com.discoverydns.dnsapiclient.exception;

public class DNSAPIClientJsonMappingException extends DNSAPIClientException {

	private static final long serialVersionUID = 3792775686625733076L;

	public enum DNSAPIClientJsonMappingExceptionCode {
		missingField, invalidFieldValue, unexpectedMappingError, unknownResourceRecordType

	}

	public DNSAPIClientJsonMappingException(
			final DNSAPIClientJsonMappingExceptionCode exceptionCode,
			final Throwable cause, final Object... objects) {
		super(exceptionCode, cause, objects);
	}

	public DNSAPIClientJsonMappingException(
			final DNSAPIClientJsonMappingExceptionCode exceptionCode,
			final Object... objects) {
		super(exceptionCode, objects);
	}

}

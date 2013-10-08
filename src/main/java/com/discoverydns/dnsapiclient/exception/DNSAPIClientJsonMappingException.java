package com.discoverydns.dnsapiclient.exception;

/**
 * A DNSAPIClient exception that has occurred during JSON deserialization.
 *
 * @author Chris Wright
 */
public class DNSAPIClientJsonMappingException extends DNSAPIClientException {

	private static final long serialVersionUID = 3792775686625733076L;

	public enum DNSAPIClientJsonMappingExceptionCode {
        /**
         * Thrown when a required field is missing in the incoming JSON stream
         */
		missingField,
        /**
         * Thrown when an invalid value is received for a field in the incoming JSON stream
         */
        invalidFieldValue,
        /**
         * Thrown when an unexpected error occurs during JSON deserialization
         */
        unexpectedMappingError,
        /**
         * Thrown when a resource record of unknown type is received in the incoming JSON stream
         */
        unknownResourceRecordType
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

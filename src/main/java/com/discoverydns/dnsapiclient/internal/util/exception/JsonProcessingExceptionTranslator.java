package com.discoverydns.dnsapiclient.internal.util.exception;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonGenerationException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.xbill.DNS.utils.json.exception.BaseJsonException;
import org.xbill.DNS.utils.json.exception.JsonSerializationException;

public class JsonProcessingExceptionTranslator {

    public static Exception translateJsonProcessingException(
            JsonProcessingException originalException) {
        if (originalException.getCause() == null
                || !BaseJsonException.class.isAssignableFrom(originalException.getCause().getClass())) {
            if (JsonGenerationException.class.isAssignableFrom(originalException.getClass())) {
                return new DNSAPIClientJsonGenerationException(
                        DNSAPIClientJsonGenerationException.DNSAPIClientJsonGenerationExceptionCode.unexpectedGenerationError,
                        originalException.getMessage());
            } else if (JsonMappingException.class.isAssignableFrom(originalException.getClass())
                    || JsonParseException.class.isAssignableFrom(originalException.getClass())) {
                return new DNSAPIClientJsonMappingException(
                        DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode.unexpectedMappingError,
                        "unknown", originalException.getMessage());
            } else {
                return originalException;
            }
        }

        return translateBaseJsonException((BaseJsonException) originalException.getCause());
    }

    public static Exception translateBaseJsonException(BaseJsonException baseJsonException) {
        if (JsonSerializationException.class.isAssignableFrom(baseJsonException.getClass())) {
            return new DNSAPIClientJsonGenerationException(
                    DNSAPIClientJsonGenerationException.DNSAPIClientJsonGenerationExceptionCode.valueOf(
                            baseJsonException.getExceptionCode().name()), baseJsonException.getObjects());
        } else {
            return new DNSAPIClientJsonMappingException(
                    DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode.valueOf(
                            baseJsonException.getExceptionCode().name()), baseJsonException.getObjects());
        }
    }
}

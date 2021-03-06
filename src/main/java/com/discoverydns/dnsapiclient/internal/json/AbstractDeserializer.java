package com.discoverydns.dnsapiclient.internal.json;

import java.net.InetAddress;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.net.InetAddresses;

public abstract class AbstractDeserializer<T> extends StdDeserializer<T> {
	private static final long serialVersionUID = 5081830517002430714L;

	protected AbstractDeserializer(final Class<?> vc) {
		super(vc);
	}

	protected JsonNode findFieldNode(final ObjectNode recordNode,
			final String fieldName) {
		final JsonNode fieldNode = recordNode.get(fieldName);
		if (fieldNode == null) {
			throw new DNSAPIClientJsonMappingException(
					DNSAPIClientJsonMappingExceptionCode.missingField,
					fieldName, getTextualBeanType());
		}
		return fieldNode;
	}

	protected String getNodeStringValue(final ObjectNode recordNode,
			final String fieldName) {
		return findFieldNode(recordNode, fieldName).textValue();
	}

	protected Number getNodeNumberValue(final ObjectNode recordNode,
			final String fieldName) {
        JsonNode fieldNode = findFieldNode(recordNode, fieldName);
        switch (fieldNode.getNodeType()) {
            case NUMBER:
                return fieldNode.numberValue();
            case STRING:
                try {
                    return NumberFormat.getInstance(Locale.getDefault()).parse(
                        fieldNode.textValue());
                } catch (final ParseException e) {
                    throw new DNSAPIClientJsonMappingException(
                            DNSAPIClientJsonMappingExceptionCode.invalidFieldValue, e,
                            fieldName, getTextualBeanType(), e.getMessage());
                }
            default:
                throw new DNSAPIClientJsonMappingException(
                        DNSAPIClientJsonMappingExceptionCode.invalidFieldValue,
                        fieldName, getTextualBeanType(), "Field cannot be read as a number");
        }
	}

	protected Long getNodeLongValue(final ObjectNode recordNode,
			final String fieldName) {
		return getNodeNumberValue(recordNode, fieldName).longValue();
	}

	public Integer getNodeIntegerValue(final ObjectNode recordNode,
			final String fieldName) {
		return getNodeNumberValue(recordNode, fieldName).intValue();
	}

	protected Double getNodeDoubleValue(final ObjectNode recordNode,
			final String fieldName) {
		return getNodeNumberValue(recordNode, fieldName).doubleValue();
	}

	public InetAddress getNodeAddressValue(final ObjectNode recordNode,
			final String fieldName) {
		final JsonNode addressNode = findFieldNode(recordNode, fieldName);
		try {
			if (addressNode.textValue() != null) {
				return getAddressFromString(addressNode.textValue());
			} else {
				return null;
			}
		} catch (final Throwable e) {
			throw new DNSAPIClientJsonMappingException(
					DNSAPIClientJsonMappingExceptionCode.invalidFieldValue, e,
					fieldName, getTextualBeanType(), e.getMessage());
		}
	}

	public InetAddress getAddressFromString(final String address) {
		return InetAddresses.forString(address);
	}

    public LocalDateTime getNodeLocalDateTimeValue(final ObjectNode recordNode,
                                                   final String fieldName) {
        try {
            return LocalDateTime.parse(getNodeStringValue(recordNode, fieldName));
        } catch (final Throwable e) {
            throw new DNSAPIClientJsonMappingException(
                    DNSAPIClientJsonMappingExceptionCode.invalidFieldValue, e,
                    fieldName, getTextualBeanType(), e.getMessage());
        }
    }

    public LocalDateTime getOptionalNodeLocalDateTimeValue(final ObjectNode recordNode,
                                                           final String fieldName) {
        try {
            String nodeStringValue = getNodeStringValue(recordNode, fieldName);
            if (nodeStringValue == null) {
                return null;
            }
            return LocalDateTime.parse(nodeStringValue);
        } catch (final Throwable e) {
            throw new DNSAPIClientJsonMappingException(
                    DNSAPIClientJsonMappingExceptionCode.invalidFieldValue, e,
                    fieldName, getTextualBeanType(), e.getMessage());
        }
    }

	protected abstract String getTextualBeanType();
}

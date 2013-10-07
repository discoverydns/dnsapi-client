package com.discoverydns.dnsapiclient.internal.json;

import java.net.InetAddress;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.xbill.DNS.Name;
import org.xbill.DNS.TextParseException;

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

	public Name getNameFromString(final String nodeValue)
			throws TextParseException {
		return Name.fromString(nodeValue);
	}

	protected Name getNodeNameValue(final ObjectNode recordNode,
			final String fieldName) {
		try {
			return getNameFromString(getNodeStringValue(recordNode, fieldName));
		} catch (final TextParseException e) {
			throw new DNSAPIClientJsonMappingException(
					DNSAPIClientJsonMappingExceptionCode.invalidFieldValue, e,
					fieldName, getTextualBeanType(), e.getMessage());
		}
	}

	protected Number getNodeNumberValue(final ObjectNode recordNode,
			final String fieldName) {
		try {
			return NumberFormat.getInstance(Locale.getDefault()).parse(
					getNodeStringValue(recordNode, fieldName));
		} catch (final ParseException e) {
			throw new DNSAPIClientJsonMappingException(
					DNSAPIClientJsonMappingExceptionCode.invalidFieldValue, e,
					fieldName, getTextualBeanType(), e.getMessage());
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

	protected abstract String getTextualBeanType();
}

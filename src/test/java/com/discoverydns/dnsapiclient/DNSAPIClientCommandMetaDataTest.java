package com.discoverydns.dnsapiclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DNSAPIClientCommandMetaDataTest {
    private DNSAPIClientCommandMetaData dnsapiClientCommandMetaData;

    private String key = "key";
    private Object value = new Object();

    @Before
    public void setup() {
        dnsapiClientCommandMetaData = new DNSAPIClientCommandMetaData();
    }

    @Test
    public void shouldAddMetaDataKeyValuePair() {
        assertFalse(dnsapiClientCommandMetaData.contains(key));
        assertNull(dnsapiClientCommandMetaData.get(key));

        dnsapiClientCommandMetaData.put(key, value);

        assertTrue(dnsapiClientCommandMetaData.contains(key));
        assertEquals(value, dnsapiClientCommandMetaData.get(key));
    }

    @Test
    public void shouldReplaceMetaDataKeyValuePair() {
        dnsapiClientCommandMetaData.put(key, value);

        assertTrue(dnsapiClientCommandMetaData.contains(key));
        assertEquals(value, dnsapiClientCommandMetaData.get(key));

        Object value2 = new Object();
        dnsapiClientCommandMetaData.put(key, value2);

        assertTrue(dnsapiClientCommandMetaData.contains(key));
        assertEquals(value2, dnsapiClientCommandMetaData.get(key));
    }
}

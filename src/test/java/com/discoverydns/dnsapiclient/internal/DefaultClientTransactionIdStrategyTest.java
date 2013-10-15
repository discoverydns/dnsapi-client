package com.discoverydns.dnsapiclient.internal;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class DefaultClientTransactionIdStrategyTest {
    private DefaultClientTransactionIdStrategy defaultClientTransactionIdStrategy;

    @Before
    public void setup() {
        defaultClientTransactionIdStrategy = new DefaultClientTransactionIdStrategy();
    }

    @Test
    public void shouldReturnARandomUUIDString() {
        assertTrue(
                Pattern.matches(
                        "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                        defaultClientTransactionIdStrategy.generateTransactionId()));
    }

    @Test
    public void shouldReturnANewRandomUUIDStringEveryTime() {
        assertNotSame(defaultClientTransactionIdStrategy.generateTransactionId(),
                defaultClientTransactionIdStrategy.generateTransactionId());
    }
}

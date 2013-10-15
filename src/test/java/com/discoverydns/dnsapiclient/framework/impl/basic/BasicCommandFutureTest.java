package com.discoverydns.dnsapiclient.framework.impl.basic;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import com.discoverydns.dnsapiclient.framework.command.CommandFutureListener;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BasicCommandFutureTest {
    @Mock
    private Object mockResponse;
    @Mock
    private Object mockCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;
    @Mock
    private Throwable mockCause;
    @Mock
    private CommandFutureListener mockCommandFutureListener;

    @Test
    public void shouldCreateExpectedSuccessfulFuture() {
        BasicCommandFuture successfulFuture = BasicCommandFuture.createSuccessfulFuture(
                mockCommand, mockCommandMetaData, mockResponse);

        assertEquals(mockCommand, successfulFuture.getCommand());
        assertEquals(mockCommandMetaData, successfulFuture.getCommandMetaData());
        assertEquals(mockResponse, successfulFuture.getResponse());
        assertNull(successfulFuture.getCause());
        assertTrue(successfulFuture.isSuccess());
        assertFalse(successfulFuture.isFailure());
        assertTrue(successfulFuture.isDone());
    }

    @Test
    public void shouldCreateExpectedFailureFuture() {
        BasicCommandFuture failedFuture = BasicCommandFuture.createFailedFuture(
                mockCommand, mockCommandMetaData, mockCause);

        assertEquals(mockCommand, failedFuture.getCommand());
        assertEquals(mockCommandMetaData, failedFuture.getCommandMetaData());
        assertNull(failedFuture.getResponse());
        assertEquals(mockCause, failedFuture.getCause());
        assertFalse(failedFuture.isSuccess());
        assertTrue(failedFuture.isFailure());
        assertTrue(failedFuture.isDone());
    }

    @Test
    public void shouldAddCommandFutureListener() {
        BasicCommandFuture successfulFuture = BasicCommandFuture.createSuccessfulFuture(
                mockCommand, mockCommandMetaData, mockResponse);

        successfulFuture.addListener(mockCommandFutureListener);

        verify(mockCommandFutureListener).operationComplete(successfulFuture);
    }
}

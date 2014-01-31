package com.discoverydns.dnsapiclient.framework.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BlockingCommandExecutorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private CommandProcessor mockCommandProcessor;
    @Mock
    private Object mockCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;
    @Mock
    private CommandFuture mockCommandFuture;
    @Mock
    private Object mockResponse;
    @Mock
    private Throwable mockCause;

    private BlockingCommandExecutor blockingCommandExecutor;

    @Before
    public void setup() throws Throwable {
        when(mockCommandProcessor.process(mockCommand, mockCommandMetaData))
                .thenReturn(mockCommandFuture);
        when(mockCommandFuture.isSuccess()).thenReturn(true);
        when(mockCommandFuture.getResponse()).thenReturn(mockResponse);
        when(mockCommandFuture.getCause()).thenReturn(mockCause);

        blockingCommandExecutor = new BlockingCommandExecutor(mockCommandProcessor);
    }

    @Test
    public void shouldDelegateToCommandProcessorToProcessCommand() throws Throwable {
        blockingCommandExecutor.process(mockCommand, mockCommandMetaData);

        verify(mockCommandProcessor).process(mockCommand, mockCommandMetaData);
    }

    @Test
    public void shouldWaitForCommandCompletion() throws Throwable {
        blockingCommandExecutor.process(mockCommand, mockCommandMetaData);

        verify(mockCommandFuture).awaitUninterruptibly();
    }

    @Test
    public void shouldReturnResponseIfWasSuccessful() throws Throwable {
        when(mockCommandFuture.isSuccess()).thenReturn(true);

        assertEquals(mockResponse,
                blockingCommandExecutor.process(mockCommand, mockCommandMetaData));
    }

    @Test
    public void shouldThrowCauseIfWasNotSuccessful() throws Throwable {
        when(mockCommandFuture.isSuccess()).thenReturn(false);

        thrown.expect(new BaseMatcher<Object>() {
            @Override
            public boolean matches(Object item) {
                return mockCause.equals(item);
            }

            @Override
            public void describeTo(Description description) { }
        });

        blockingCommandExecutor.process(mockCommand, mockCommandMetaData);
    }
}

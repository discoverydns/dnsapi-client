package com.discoverydns.dnsapiclient.internal.commandinterceptors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverydns.dnsapiclient.framework.command.CommandInterceptorChain;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
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
public class StopwatchCommandInterceptorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private Object mockCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;
    @Mock
    private CommandInterceptorChain mockCommandInterceptorChain;

    private StopwatchCommandInterceptor stopwatchCommandInterceptor;

    @Before
    public void setup() throws Throwable {

        stopwatchCommandInterceptor = new StopwatchCommandInterceptor();
    }

    @Test
    public void shouldProceedRestOfTheChain() throws Throwable {
        stopwatchCommandInterceptor.intercept(
                mockCommand, mockCommandMetaData, mockCommandInterceptorChain);

        verify(mockCommandInterceptorChain).proceed();
    }

    @Test
    public void shouldAddTimeElapsedToCommandMetadata() throws Throwable {
        stopwatchCommandInterceptor.intercept(
                mockCommand, mockCommandMetaData, mockCommandInterceptorChain);

        verify(mockCommandMetaData).put(
                eq(StopwatchCommandInterceptor.COMMAND_TIME_MS), anyDouble());
    }

    @Test
    public void shouldReturnResponseIfCommandWasSuccessful() throws Throwable {
        Object mockResponse = mock(Object.class);
        when(mockCommandInterceptorChain.proceed()).thenReturn(mockResponse);

        assertEquals(mockResponse,
                stopwatchCommandInterceptor.intercept(
                        mockCommand, mockCommandMetaData, mockCommandInterceptorChain));
    }

    @Test
    public void shouldRethrowExceptionIfCommandWasUnsuccessful() throws Throwable {
        final RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockCommandInterceptorChain.proceed()).thenThrow(mockRuntimeException);

        thrown.expect(new BaseMatcher() {
            @Override
            public boolean matches(Object item) {
                return mockRuntimeException.equals(item);
            }

            @Override
            public void describeTo(Description description) { }
        });

        stopwatchCommandInterceptor.intercept(
                mockCommand, mockCommandMetaData, mockCommandInterceptorChain);
    }
}

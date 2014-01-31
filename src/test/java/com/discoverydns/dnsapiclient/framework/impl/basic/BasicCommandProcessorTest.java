package com.discoverydns.dnsapiclient.framework.impl.basic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import com.discoverydns.dnsapiclient.command.account.AccountGetCommand;
import com.discoverydns.dnsapiclient.framework.command.CommandFuture;
import com.discoverydns.dnsapiclient.framework.command.CommandInterceptor;
import com.discoverydns.dnsapiclient.framework.command.CommandInterceptorChain;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.account.AccountGetCommandHandler;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BasicCommandProcessorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Mock
    private CommandMetaData mockCommandMetaData;
    @Mock
    private AccountGetCommandHandler mockAccountGetCommandHandler;
    @Mock
    private CommandInterceptor mockCommandInterceptor;

    private BasicCommandProcessor basicCommandProcessor;

    private AccountGetCommand accountGetCommand;

    @Before
    public void setup() throws Throwable {
        accountGetCommand =  new AccountGetCommand.Builder().build();

        basicCommandProcessor = new BasicCommandProcessor();
    }

    @Test
    public void shouldFailIfNoCommandHandlerIsFoundForCommand() {
        CommandFuture commandFuture = basicCommandProcessor.process(
                accountGetCommand, mockCommandMetaData);

        assertEquals(accountGetCommand, commandFuture.getCommand());
        assertEquals(mockCommandMetaData, commandFuture.getCommandMetaData());
        assertFalse(commandFuture.isSuccess());
        Throwable cause = commandFuture.getCause();
        assertThat(cause, instanceOf(IllegalArgumentException.class));
        assertEquals("No command handler for command type [AccountGetCommand]", cause.getMessage());
    }

    @Test
    public void shouldDelegateToSubscribedCommandHandlerToProceedCommand() throws Throwable {
        Object mockResponse = mock(Object.class);
        when(mockAccountGetCommandHandler.execute(
                accountGetCommand, mockCommandMetaData)).thenReturn(mockResponse);

        basicCommandProcessor.subscribe(AccountGetCommand.class, mockAccountGetCommandHandler);

        CommandFuture commandFuture = basicCommandProcessor.process(
                accountGetCommand, mockCommandMetaData);

        assertEquals(accountGetCommand, commandFuture.getCommand());
        assertEquals(mockCommandMetaData, commandFuture.getCommandMetaData());
        assertTrue(commandFuture.isSuccess());
        assertEquals(mockResponse, commandFuture.getResponse());
    }

    @Test
    public void shouldReturnFailureIfSubscribedCommandHandlerThrowsAnException() throws Throwable {
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockAccountGetCommandHandler.execute(
                accountGetCommand, mockCommandMetaData)).thenThrow(mockRuntimeException);

        basicCommandProcessor.subscribe(AccountGetCommand.class, mockAccountGetCommandHandler);

        CommandFuture commandFuture = basicCommandProcessor.process(
                accountGetCommand, mockCommandMetaData);

        assertEquals(accountGetCommand, commandFuture.getCommand());
        assertEquals(mockCommandMetaData, commandFuture.getCommandMetaData());
        assertFalse(commandFuture.isSuccess());
        assertEquals(mockRuntimeException, commandFuture.getCause());
    }

    @Test
    public void shouldDelegateToSubscribedCommandInterceptorToProceedCommand() throws Throwable {
        Object mockResponse = mock(Object.class);
        when(mockCommandInterceptor.intercept(eq(accountGetCommand), eq(mockCommandMetaData),
                any(CommandInterceptorChain.class))).thenReturn(mockResponse);
        basicCommandProcessor.subscribe(AccountGetCommand.class, mockAccountGetCommandHandler);
        basicCommandProcessor.addCommandInterceptor(mockCommandInterceptor);

        CommandFuture commandFuture = basicCommandProcessor.process(
                accountGetCommand, mockCommandMetaData);

        assertEquals(accountGetCommand, commandFuture.getCommand());
        assertEquals(mockCommandMetaData, commandFuture.getCommandMetaData());
        assertTrue(commandFuture.isSuccess());
        assertEquals(mockResponse, commandFuture.getResponse());
    }

    @Test
    public void shouldReturnFailureIfAddedCommandInterceptorThrowsAnException() throws Throwable {
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockCommandInterceptor.intercept(eq(accountGetCommand), eq(mockCommandMetaData),
                any(CommandInterceptorChain.class))).thenThrow(mockRuntimeException);

        basicCommandProcessor.subscribe(AccountGetCommand.class, mockAccountGetCommandHandler);
        basicCommandProcessor.addCommandInterceptor(mockCommandInterceptor);

        CommandFuture commandFuture = basicCommandProcessor.process(
                accountGetCommand, mockCommandMetaData);

        assertEquals(accountGetCommand, commandFuture.getCommand());
        assertEquals(mockCommandMetaData, commandFuture.getCommandMetaData());
        assertFalse(commandFuture.isSuccess());
        assertEquals(mockRuntimeException, commandFuture.getCause());
    }

    @Test
    public void shouldUnsubscribeCommandHandler() {
        basicCommandProcessor.subscribe(AccountGetCommand.class, mockAccountGetCommandHandler);
        basicCommandProcessor.unsubscribe(AccountGetCommand.class, mockAccountGetCommandHandler);

        CommandFuture commandFuture = basicCommandProcessor.process(
                accountGetCommand, mockCommandMetaData);

        assertEquals(accountGetCommand, commandFuture.getCommand());
        assertEquals(mockCommandMetaData, commandFuture.getCommandMetaData());
        assertFalse(commandFuture.isSuccess());
        Throwable cause = commandFuture.getCause();
        assertThat(cause, instanceOf(IllegalArgumentException.class));
        assertEquals("No command handler for command type [AccountGetCommand]", cause.getMessage());
    }

    @Test
    public void shouldRemoveCommandInterceptor() {
        basicCommandProcessor.subscribe(AccountGetCommand.class, mockAccountGetCommandHandler);
        basicCommandProcessor.addCommandInterceptor(mockCommandInterceptor);
        basicCommandProcessor.removeCommandInterceptor(mockCommandInterceptor);

        basicCommandProcessor.process(accountGetCommand, mockCommandMetaData);

        verifyZeroInteractions(mockCommandInterceptor);
    }
}

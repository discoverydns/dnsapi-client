package com.discoverydns.dnsapiclient.internal.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Invocation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NoEntityInvocationBuildInvokerTest {
    @Mock
    private Invocation mockInvocation;
    @Mock
    private Invocation.Builder mockBuilder;

    private NoEntityInvocationBuildInvoker noEntityInvocationBuildInvoker;

    @Before
    public void setup() throws Throwable {
        noEntityInvocationBuildInvoker = new NoEntityInvocationBuildInvoker();
    }

    @Test
    public void shouldBuildExpectedInvocation() {
        Method method = Method.GET;
        when(mockBuilder.build(method.toString())).thenReturn(mockInvocation);

        assertEquals(mockInvocation,
                noEntityInvocationBuildInvoker.invoke(mockBuilder, method));

    }
}

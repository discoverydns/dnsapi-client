package com.discoverydns.dnsapiclient.internal.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;

import com.discoverydns.dnsapiclient.internal.views.ZoneCreateView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WithEntityInvocationBuildInvokerTest {
    @Mock
    private Invocation mockInvocation;
    @Mock
    private Invocation.Builder mockBuilder;
    @Mock
    private ZoneCreateView mockZoneCreateView;

    private WithEntityInvocationBuildInvoker<ZoneCreateView> withEntityInvocationBuildInvoker;

    @Before
    public void setup() throws Throwable {
        withEntityInvocationBuildInvoker = new WithEntityInvocationBuildInvoker<ZoneCreateView>(mockZoneCreateView);
    }

    @Test
    public void shouldBuildExpectedInvocation() {
        Method method = Method.GET;
        when(mockBuilder.build(eq(method.toString()), any(Entity.class))).thenReturn(mockInvocation);

        assertEquals(mockInvocation,
                withEntityInvocationBuildInvoker.invoke(mockBuilder, method));
    }
}

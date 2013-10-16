package com.discoverydns.dnsapiclient.internal.command.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.net.URI;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.discoverydns.dnsapiclient.command.user.UserListCommand;
import com.discoverydns.dnsapiclient.command.user.UserListResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.UserListView;

@RunWith(MockitoJUnitRunner.class)
public class UserListCommandHandlerTest {
    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockUserListTarget;
    @Mock
    private UserListCommand mockUserListCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private UserListCommandHandler userListCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("users/"))
                .thenReturn(mockUserListTarget);

        userListCommandHandler =
                new UserListCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseUserListTarget() {
        verify(mockBaseWebTarget).path("users/");
    }

    @Test
    public void shouldAddSearchStatusAsQueryParamIfNotNull() {
        String searchStatus = "searchStatus";
        when(mockUserListCommand.getSearchStatus()).thenReturn(searchStatus);

        userListCommandHandler.getWebTarget(
                mockUserListCommand, mockCommandMetaData);

        verify(mockUserListTarget).queryParam("searchStatus", searchStatus);
    }

    @Test
    public void shouldAddSearchNameAsQueryParamIfNotNull() {
        String searchName = "searchName";
        when(mockUserListCommand.getSearchName()).thenReturn(searchName);

        userListCommandHandler.getWebTarget(
                mockUserListCommand, mockCommandMetaData);

        verify(mockUserListTarget).queryParam("searchName", searchName);
    }

    @Test
    public void shouldAddSearchUsernameAsQueryParamIfNotNull() {
        String searchUsername = "searchUsername";
        when(mockUserListCommand.getSearchUsername())
                .thenReturn(searchUsername);

        userListCommandHandler.getWebTarget(
                mockUserListCommand, mockCommandMetaData);

        verify(mockUserListTarget).queryParam("searchUsername",
                searchUsername);
    }

    @Test
    public void shouldNotAddQueryParamsOtherwise() {
        userListCommandHandler.getWebTarget(
                mockUserListCommand, mockCommandMetaData);

        verifyZeroInteractions(mockUserListTarget);
    }

    @Test
    public void shouldListTargetRelativeToProvidedCommand() {
        assertEquals(mockUserListTarget,
                userListCommandHandler.getWebTarget(
                        mockUserListCommand, mockCommandMetaData));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                userListCommandHandler.getInvocationBuilderFactory(
                        mockUserListCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                userListCommandHandler.getInvocationBuildInvoker(
                        mockUserListCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedUserListResponse() throws Exception {
        Response mockRestResponse = mock(Response.class);
        UserListView mockUserListView
                = mock(UserListView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(UserListView.class))
                .thenReturn(mockUserListView);
        URI uri = new URI("http://ddns.com/users");
        when(mockUserListView.getUri()).thenReturn(uri);

        UserListResponse commandResponse =
                userListCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(uri, commandResponse.getURI());
    }
}

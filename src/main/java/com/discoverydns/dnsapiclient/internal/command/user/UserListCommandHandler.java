package com.discoverydns.dnsapiclient.internal.command.user;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.user.UserListCommand;
import com.discoverydns.dnsapiclient.command.user.UserListResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.UserListView;

public class UserListCommandHandler extends
		BaseRestCommandHandler<UserListCommand, UserListResponse> {

	private final WebTarget userListTarget;

	public UserListCommandHandler(final WebTarget baseWebTarget) {
		super(Method.GET, Status.OK.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.userListTarget = baseWebTarget.path("users/");
	}

	@Override
	public WebTarget getWebTarget(final UserListCommand command,
			final CommandMetaData commandMetaData) {
		WebTarget resolvedUserListTarget = userListTarget;
		if (command.getSearchUsername() != null) {
			resolvedUserListTarget = resolvedUserListTarget.queryParam(
					"searchUsername", command.getSearchUsername());
		}
		if (command.getSearchStatus() != null) {
			resolvedUserListTarget = resolvedUserListTarget.queryParam(
					"searchStatus", command.getSearchStatus());
		}
		if (command.getSearchName() != null) {
			resolvedUserListTarget = resolvedUserListTarget.queryParam(
					"searchName", command.getSearchName());
		}
		return resolvedUserListTarget;
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final UserListCommand command, final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuilderFactory();
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final UserListCommand command, final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuildInvoker();
	}

	@Override
	public UserListResponse getCommandResponse(final Response restResponse,
			final CommandMetaData commandMetaData) {
		final UserListView userListView = getResponseEntity(UserListView.class,
				restResponse);
		return new UserListResponse(userListView);
	}
}
